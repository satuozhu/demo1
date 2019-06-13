package com.users.modules.user.service.impl;

import com.common.entity.user.CrIntegral;
import com.users.component.util.CharacterUtils;
import com.users.component.util.CheckUtil;
import com.users.component.util.IdGenerator;
import com.users.modules.user.entity.CrUser;
import com.users.modules.mapper.primary.user.CrIntegralMapper;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.requestBody.registered.RegisteredAccountReqBody;
import com.users.modules.user.service.RegisteredService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 注册service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class RegisteredServiceImpl implements RegisteredService {

    @Autowired
    private CrUserMapper crUserDAO;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CharacterUtils characterUtils;
    @Autowired
    private CrIntegralMapper crIntegralMapper;

    /**
     * 注册(手机号、邮箱)
     * @param userAccount
     * @param pwd
     * @return
     */
    @Override
    public CrUser registered(String userAccount, String pwd) {
        Long now = System.currentTimeMillis();//13位时间戳
        CrUser crUser = new CrUser();
        //主键生成
        //IdGenerator idWorker = new IdGenerator();
        Long id = idGenerator.nextId();
        crUser.setId(id);
        crUser.setUserName(userAccount);
        crUser.setAccount(characterUtils.RandomChaeacter(10));
        if (CheckUtil.isMobile(userAccount)) {//是手机号
            crUser.setPhone(userAccount);
        }
        if (CheckUtil.isEmail(userAccount)) {//是邮箱
            crUser.setEmail(userAccount);
        }
        String salt = characterUtils.RandomChaeacter(4);
        //密码加密
        crUser.setPwd(DigestUtils.md5DigestAsHex((pwd + salt).getBytes()));
        //盐值：随机生成4位字符
        crUser.setSalt(salt);
//        crUser.setSex(crUserReqBody.getSex());//性别(0:女 1:男)
//        crUser.setBirthday(crUserReqBody.getBirthday());
//        crUser.setHeight("");
//        crUser.setWeight("");
//        crUser.setTags(crUserReqBody.getTags());
        crUser.setCreateTime(now);
        crUser.setUpdateTime(now);
//        crUser.setOpenid(crUserReqBody.getOpenid());
//        crUser.setHeadPic(crUserReqBody.getHeadPic());
        crUser.setFlag(0);//(0:普通用户,1:医生)
        int isNum = crUserDAO.insertSelective(crUser);
        if (isNum > 0) {
            //注册成功，添加用户积分记录
            CrIntegral crIntegral = new CrIntegral();
            crIntegral.setUserId(id);
            crIntegral.setTotleScore(0);
            crIntegralMapper.insertSelective(crIntegral);
            return crUser;
        }
        return null;
    }

    /**
     * 注册(账号)
     *
     * @param reqBody
     * @return
     */
    @Override
    public CrUser registeredAccount(RegisteredAccountReqBody reqBody) {
        Long now = System.currentTimeMillis();//13位时间戳
        CrUser crUser = new CrUser();
        //主键生成
        //IdGenerator idWorker = new IdGenerator();
        Long id = idGenerator.nextId();
        crUser.setId(id);
        crUser.setUserName(reqBody.getUserAccount());
        crUser.setAccount(reqBody.getUserAccount());
//        crUser.setPhone("");
//        crUser.setEmail("");
        String salt = characterUtils.RandomChaeacter(4);
        //密码加密
        crUser.setPwd(DigestUtils.md5DigestAsHex((reqBody.getPwd() + salt).getBytes()));
        //盐值：随机生成4位字符
        crUser.setSalt(salt);
//        crUser.setSex("1");//性别(0:女 1:男)
//        crUser.setBirthday("");
//        crUser.setHeight("");
//        crUser.setWeight("");
//        crUser.setTags("");
        crUser.setCreateTime(now);
        crUser.setUpdateTime(now);
//        crUser.setOpenid("");
//        crUser.setHeadPic("");
        crUser.setFlag(0);//(0:普通用户,1:医生)
        int isNum = crUserDAO.insertSelective(crUser);
        if (isNum > 0) {
            //注册成功，添加用户积分记录
            CrIntegral crIntegral = new CrIntegral();
            crIntegral.setUserId(id);
            crIntegral.setTotleScore(0);
            crIntegralMapper.insertSelective(crIntegral);
            return crUser;
        }
        return null;
    }

}

