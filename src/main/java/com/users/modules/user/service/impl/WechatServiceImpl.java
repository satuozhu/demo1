package com.users.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.entity.user.CrIntegral;
import com.common.entity.user.CrIntegralExample;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.CharacterUtils;
import com.users.component.util.IdGenerator;
import com.users.component.util.JwtTokenUtils;
import com.users.modules.mapper.primary.user.CrIntegralMapper;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.entity.CrUserExample;
import com.users.modules.user.requestBody.login.LoginWechatAuthReqBody;
import com.users.modules.user.requestBody.wechat.BindWechatReqBody;
import com.users.modules.user.requestBody.wechat.UnbindWechatReqBody;
import com.users.modules.user.responseBody.login.LoginResBody;
import com.users.modules.user.responseBody.login.LoginWechatResBody;
import com.users.modules.user.service.CrUserService;
import com.users.modules.user.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 微信service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class WechatServiceImpl implements WechatService {

    @Autowired
    private CrUserMapper crUserDAO;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CharacterUtils characterUtils;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private CrUserService crUserService;
    @Autowired
    private CrIntegralMapper crIntegralMapper;

    /**
     * 微信登录授权
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<LoginWechatResBody> loginWechatAuth(LoginWechatAuthReqBody reqBody) {
//        if (null == loginAuthReqBody) {
//            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR);
//        }
        String openid = reqBody.getOpenId();
        //String unionid = reqBody.getUnionid();
        String userName = reqBody.getNickname();
        String headPic = reqBody.getHeadImgUrl();
        try {
            JSONObject json = new JSONObject();

            //根据openId查询，有则更新，没有则新增
            CrUser crUser = crUserService.findCrUserByOpenid(openid);
            System.out.println("crUser = " + crUser);
            if (null != crUser) {
                if (StringUtils.isBlank(crUser.getUserName())) {
                    crUser.setUserName(userName);
                }
                if (StringUtils.isBlank(crUser.getHeadPic())) {
                    crUser.setHeadPic(headPic);
                }
                crUser.setPlatformType(reqBody.getPlatformType());//平台类型（1移动端、2PC端）
                crUser.setLoginType(reqBody.getLoginType());//登录类型（1账号、2微信、3手机、4邮箱）
                crUser.setUpdateTime(System.currentTimeMillis());//13位时间戳
                if (crUserDAO.updateByPrimaryKeySelective(crUser) > 0) {
                    LoginWechatResBody resBody = new LoginWechatResBody();
                    resBody.setToken(jwtTokenUtils.generateToken(crUser));
                    crUser.setPwd("");
                    crUser.setSalt("");
                    resBody.setUser(crUser);
                    resBody.setBindMobile((null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
                    resBody.setBindWechat((null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
                    CrIntegralExample example = new CrIntegralExample();
                    example.createCriteria().andUserIdEqualTo(crUser.getId());
                    List<CrIntegral> list = crIntegralMapper.selectByExample(example);
                    resBody.setTotalIntegral(list.size() > 0 ? list.get(0).getTotleScore() : 0);
                    resBody.setTotalCollect(0);
                    resBody.setTotalFollow(0);
                    resBody.setFirstLoginWechat(0);

//                    json.put("token", jwtTokenUtils.generateToken(crUser));
//                    crUser.setPwd("");
//                    crUser.setSalt("");
//                    json.put("user", crUser);
//                    json.put("bindMobile", (null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
//                    json.put("bindWechat", (null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
//                    CrIntegralExample example = new CrIntegralExample();
//                    example.createCriteria().andUserIdEqualTo(crUser.getId());
//                    List<CrIntegral> list = crIntegralMapper.selectByExample(example);
//                    json.put("totalIntegral", list.size() > 0 ? list.get(0).getTotleScore() : 0);
//                    json.put("totalCollect", 0);
//                    json.put("totalFollow", 0);
//                    json.put("firstLoginWechat", 0);

//                    json.put("openid", crUser.getOpenid());
//                    json.put("userName", crUser.getUserName());
//                    json.put("account", crUser.getAccount());
//                    json.put("phone", crUser.getPhone());
//                    json.put("headPic", crUser.getHeadPic());
//                    json.put("sex", crUser.getSex());
//                    json.put("birthday", crUser.getBirthday());
//                    json.put("height", crUser.getHeight());
//                    json.put("weight", crUser.getWeight());
//                    json.put("flag", crUser.getFlag());
                    return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, resBody);
                }
            } else {//没有则新增
                crUser = new CrUser();
                Long now = System.currentTimeMillis();//13位时间戳
                //主键生成
                //IdGenerator idWorker = new IdGenerator();
                Long id = idGenerator.nextId();
                System.out.println("id = " + id);
                crUser.setId(id);
                crUser.setUserName(userName);
                crUser.setAccount(characterUtils.RandomChaeacter(10));
                crUser.setPhone("");
                crUser.setEmail("");
//                String pwd = characterUtils.RandomChaeacter(16);
                String pwd = "123456";
                String salt = characterUtils.RandomChaeacter(4);
                //密码加密
                crUser.setPwd(DigestUtils.md5DigestAsHex((pwd + salt).getBytes()));
                //盐值：随机生成4位字符
                crUser.setSalt(salt);
                crUser.setSex("1");//性别(0:女 1:男)
                crUser.setBirthday("");
                crUser.setHeight("");
                crUser.setWeight("");
                crUser.setTags("");
                crUser.setCreateTime(now);
                crUser.setUpdateTime(now);
                crUser.setOpenid(openid);
                crUser.setHeadPic(headPic);
                crUser.setFlag(0);//(0:普通用户,1:医生);
                crUser.setPlatformType(reqBody.getPlatformType());//平台类型（1移动端、2PC端）
                crUser.setLoginType(reqBody.getLoginType());//登录类型（1账号、2微信、3手机、4邮箱）
                if (crUserDAO.insertSelective(crUser) > 0) {
                    //注册成功，添加用户积分记录
                    CrIntegral crIntegral = new CrIntegral();
                    crIntegral.setUserId(id);
                    crIntegral.setTotleScore(0);
                    crIntegralMapper.insertSelective(crIntegral);

                    LoginWechatResBody resBody = new LoginWechatResBody();
                    resBody.setToken(jwtTokenUtils.generateToken(crUser));
                    crUser.setPwd("");
                    crUser.setSalt("");
                    resBody.setUser(crUser);
                    resBody.setBindMobile((null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
                    resBody.setBindWechat((null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
                    CrIntegralExample example = new CrIntegralExample();
                    example.createCriteria().andUserIdEqualTo(crUser.getId());
                    List<CrIntegral> list = crIntegralMapper.selectByExample(example);
                    resBody.setTotalIntegral(list.size() > 0 ? list.get(0).getTotleScore() : 0);
                    resBody.setTotalCollect(0);
                    resBody.setTotalFollow(0);
                    resBody.setFirstLoginWechat(1);

//                    json.put("token", jwtTokenUtils.generateToken(crUser));
//                    crUser.setPwd("");
//                    crUser.setSalt("");
//                    json.put("user", crUser);
//                    json.put("bindMobile", (null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
//                    json.put("bindWechat", (null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
//                    CrIntegralExample example = new CrIntegralExample();
//                    example.createCriteria().andUserIdEqualTo(crUser.getId());
//                    List<CrIntegral> list = crIntegralMapper.selectByExample(example);
//                    json.put("totalIntegral", list.size() > 0 ? list.get(0).getTotleScore() : 0);
//                    json.put("totalCollect", 0);
//                    json.put("totalFollow", 0);
//                    json.put("firstLoginWechat", 1);

//                    json.put("openid", crUser.getOpenid());
//                    json.put("userName", crUser.getUserName());
//                    json.put("account", crUser.getAccount());
//                    json.put("phone", crUser.getPhone());
//                    json.put("headPic", crUser.getHeadPic());
//                    json.put("sex", crUser.getSex());
//                    json.put("birthday", crUser.getBirthday());
//                    json.put("height", crUser.getHeight());
//                    json.put("weight", crUser.getWeight());
//                    json.put("flag", crUser.getFlag());
                    return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, resBody);
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 用户绑定、更换微信
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> bindWechat(BindWechatReqBody reqBody) {
        try {
            //校验微信openId
            CrUser cu = crUserService.findCrUserByOpenid(reqBody.getOpenId());
            if (cu != null) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_BIND_USER_ACCOUNT);
            }

            //查询用户信息
            CrUser crUser = crUserDAO.selectByPrimaryKey(reqBody.getUserId());
            crUser.setOpenid(reqBody.getOpenId());
            if (StringUtils.isBlank(crUser.getUserName())) {
                crUser.setUserName(reqBody.getNickname());
            }
            if (StringUtils.isBlank(crUser.getHeadPic())) {
                crUser.setHeadPic(reqBody.getHeadImgUrl());
            }
            crUser.setUpdateTime(System.currentTimeMillis());//13位时间戳
            //修改的条件
//            CrUserExample example = new CrUserExample();
//            example.createCriteria().andIdEqualTo(userId);
//            if (crUserDAO.updateByExampleSelective(crUser, example) > 0) {
            if (crUserDAO.updateByPrimaryKeySelective(crUser) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 用户解除绑定微信
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> unbindWechat(UnbindWechatReqBody reqBody) {
        try {
            CrUser crUser = new CrUser();
            crUser.setOpenid("");
            crUser.setUpdateTime(System.currentTimeMillis());//13位时间戳
            //修改的条件
            CrUserExample example = new CrUserExample();
            example.createCriteria().andIdEqualTo(reqBody.getUserId()).andOpenidEqualTo(reqBody.getOpenId());
            if (crUserDAO.updateByExampleSelective(crUser, example) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}
