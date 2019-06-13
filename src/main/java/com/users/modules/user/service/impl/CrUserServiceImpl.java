package com.users.modules.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.entity.CrUserExample;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.responseBody.CrUserResBody;
import com.users.modules.user.service.CrUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 用户service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class CrUserServiceImpl implements CrUserService {

    @Autowired
    private CrUserMapper crUserDAO;

    /**
     * 根据用户ID查询用户
     *
     * @param userId
     * @return
     */
    @Cacheable
    @Override
    public Message<CrUserResBody> findCrUserByUserId(Long userId) {
        try {
            CrUser crUser = crUserDAO.selectByPrimaryKey(userId);
            if (null != crUser) {
                CrUserResBody resBody = new CrUserResBody();
                resBody.setId(crUser.getId());
                resBody.setUserName(crUser.getUserName());
                resBody.setAccount(crUser.getAccount());
                resBody.setPhone(crUser.getPhone());
                resBody.setEmail(crUser.getEmail());
                resBody.setPwd("");
                resBody.setSalt("");
                resBody.setHeadPic(crUser.getHeadPic());
                resBody.setSex(crUser.getSex());
                resBody.setBirthday(crUser.getBirthday());
                resBody.setHeight(crUser.getHeight());
                resBody.setWeight(crUser.getWeight());
                resBody.setCreateTime(crUser.getCreateTime());
                resBody.setUpdateTime(crUser.getUpdateTime());
                resBody.setTags(crUser.getTags());
                resBody.setOpenid(crUser.getOpenid());
                resBody.setFlag(crUser.getFlag());
                resBody.setPlatformType(crUser.getPlatformType());
                resBody.setLoginType(crUser.getLoginType());
                resBody.setAuthType(crUser.getAuthType());
                resBody.setBindMobile((null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
                resBody.setBindWechat((null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, resBody);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据账号查找用户信息
     *
     * @param account
     * @return
     */
    @Cacheable
    @Override
    public CrUser findCrUserByAccount(String account) {
        CrUser crUser = null;
        CrUserExample example = new CrUserExample();
        example.createCriteria().andAccountEqualTo(account);
        List<CrUser> list = crUserDAO.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            crUser = list.get(0);
        }
        return crUser;
    }

    /**
     * 根据手机号查找用户信息
     *
     * @param phone
     * @return
     */
    @Cacheable
    @Override
    public CrUser findCrUserByPhone(String phone) {
        CrUser crUser = null;
        CrUserExample example = new CrUserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<CrUser> list = crUserDAO.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            crUser = list.get(0);
        }
        return crUser;
    }

    /**
     * 根据邮箱查找用户信息
     *
     * @param email
     * @return
     */
    @Cacheable
    @Override
    public CrUser findCrUserByEmail(String email) {
        CrUser crUser = null;
        CrUserExample example = new CrUserExample();
        example.createCriteria().andEmailEqualTo(email);
        //example.setOrderByClause(" emial desc ");//email倒序查询
        List<CrUser> list = crUserDAO.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            crUser = list.get(0);
        }
        return crUser;
    }

    /**
     * 根据微信openid查询用户
     *
     * @param openid
     * @return
     */
    @Cacheable
    @Override
    public CrUser findCrUserByOpenid(String openid) {
        CrUser crUser = null;
        CrUserExample example = new CrUserExample();
        example.createCriteria().andOpenidEqualTo(openid);
        List<CrUser> list = crUserDAO.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            crUser = list.get(0);
        }
        return crUser;
    }

    /**
     * 查找全部用户信息
     * @param crUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Cacheable
    @Override
    //public MyPageInfo<CrUser> findCrUser(Paging paging) {
    public Message<MyPageInfo<CrUser>> findCrUser(CrUser crUser, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);// 分页参数
            CrUserExample example = new CrUserExample();
            CrUserExample.Criteria c = example.createCriteria();
            if (!StringUtils.isBlank(crUser.getSex()) && !"-1".equals(crUser.getSex())) {//1男，0女，null、""、-1全部
                c.andSexEqualTo(crUser.getSex());
            }
            if (null != crUser.getFlag() && crUser.getFlag() != -1) {//0:普通用户,1:医生,null、-1全部
                c.andFlagEqualTo(crUser.getFlag());
            }
            if (!StringUtils.isBlank(crUser.getPhone())) {
                c.andPhoneLike("%" + crUser.getPhone() + "%");
            }
            if (!StringUtils.isBlank(crUser.getEmail())) {
                c.andEmailLike("%" + crUser.getEmail() + "%");
            }
            example.setOrderByClause(" create_time desc ");//倒序查询
            List<CrUser> list = crUserDAO.selectByExampleWithBLOBs(example);
            MyPageInfo<CrUser> MyPageInfo = new MyPageInfo<>(list);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, MyPageInfo);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据用户ID更新用户
     *
     * @param crUserReg
     * @return
     */
    @Override
    public Message<?> updateCrUser(CrUser crUserReg) {
        try {
            Long now = System.currentTimeMillis();//13位时间戳
            CrUser crUser = new CrUser();
            crUser.setId(crUserReg.getId());
            //修改的内容
            crUser.setUserName(crUserReg.getUserName());
//            crUser.setAccount(crUserReg.getAccount());
//            crUser.setPhone(crUserReg.getPhone());
//            crUser.setEmail(crUserReg.getEmail());
//            if (!StringUtils.isBlank(crUserReg.getPwd())) {//不为空
//                //密码加密
//                crUser.setPwd(DigestUtils.md5DigestAsHex((crUserReg.getPwd() + crUserReg.getSalt()).getBytes()));
//            }
//            crUser.setSalt(crUserReg.getSalt());
            crUser.setSex(crUserReg.getSex());//性别(0:女 1:男)
            crUser.setBirthday(crUserReg.getBirthday());
            crUser.setHeight(crUserReg.getHeight());
            crUser.setWeight(crUserReg.getWeight());
            crUser.setTags(crUserReg.getTags());
            crUser.setOpenid(crUserReg.getOpenid());
            crUser.setHeadPic(crUserReg.getHeadPic());
            //crUser.setFlag(crUserReg.getFlag());//(0:普通用户,1:医生)
            //crUser.setCreateTime(now);
            crUser.setUpdateTime(now);
//        //修改的条件
//        CrUserExample example =new CrUserExample();
//        example.createCriteria().andIdEqualTo(crUserReg.getId());
//        return crUserDAO.updateByExampleSelective(crUser, example);
            int num = crUserDAO.updateByPrimaryKeySelective(crUser);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据用户ID删除用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Message<?> delCrUserByUserId(Long userId) {
        try {
//        CrUserExample example = new CrUserExample();
//        example.createCriteria().andPhoneEqualTo(userId);
//        return crUserDAO.deleteByExample(example);
            int num = crUserDAO.deleteByPrimaryKey(userId);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 【admin在使用】查找全部用户信息
     *
     * @param crUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Cacheable
    @Override
    public Message<MyPageInfo<CrUser>> findCrUserWeb(CrUser crUser, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);// 分页参数
            CrUserExample example = new CrUserExample();
            CrUserExample.Criteria c = example.createCriteria();
            if (!StringUtils.isBlank(crUser.getSex()) && !"-1".equals(crUser.getSex())) {//1男，0女，null、""、-1全部
                c.andSexEqualTo(crUser.getSex());
            }
            if (null != crUser.getFlag() && crUser.getFlag() != -1) {//0:普通用户,1:医生,null、-1全部
                c.andFlagEqualTo(crUser.getFlag());
            }
            if (!StringUtils.isBlank(crUser.getPhone())) {
                c.andPhoneLike("%" + crUser.getPhone() + "%");
            }
            if (!StringUtils.isBlank(crUser.getEmail())) {
                c.andEmailLike("%" + crUser.getEmail() + "%");
            }
            example.setOrderByClause(" create_time desc ");//倒序查询
            List<CrUser> list = crUserDAO.selectByExampleWithBLOBs(example);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 【admin在使用】根据用户ID更新用户
     *
     * @param crUserReg
     * @return
     */
    @Override
    public Message<?> updateCrUserWeb(CrUser crUserReg) {
        try {
            Long now = System.currentTimeMillis();//13位时间戳
            CrUser crUser = new CrUser();
            crUser.setId(crUserReg.getId());
            //修改的内容
            crUser.setUserName(crUserReg.getUserName());
//            crUser.setAccount(crUserReg.getAccount());
//            crUser.setPhone(crUserReg.getPhone());
//            crUser.setEmail(crUserReg.getEmail());
            if (!StringUtils.isBlank(crUserReg.getPwd())) {//不为空
                //密码加密
                crUser.setPwd(DigestUtils.md5DigestAsHex((crUserReg.getPwd() + crUserReg.getSalt()).getBytes()));
            }
            crUser.setSalt(crUserReg.getSalt());
            crUser.setSex(crUserReg.getSex());//性别(0:女 1:男)
            crUser.setBirthday(crUserReg.getBirthday());
            crUser.setHeight(crUserReg.getHeight());
            crUser.setWeight(crUserReg.getWeight());
            crUser.setTags(crUserReg.getTags());
            crUser.setOpenid(crUserReg.getOpenid());
            crUser.setHeadPic(crUserReg.getHeadPic());
            //crUser.setFlag(crUserReg.getFlag());//(0:普通用户,1:医生)
            //crUser.setCreateTime(now);
            crUser.setUpdateTime(now);
//        //修改的条件
//        CrUserExample example =new CrUserExample();
//        example.createCriteria().andIdEqualTo(crUserReg.getId());
//        return crUserDAO.updateByExampleSelective(crUser, example);
            int num = crUserDAO.updateByPrimaryKeySelective(crUser);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 【admin在使用】根据用户ID删除用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Message<?> delCrUserWebByUserId(Long userId) {
        try {
//        CrUserExample example = new CrUserExample();
//        example.createCriteria().andPhoneEqualTo(userId);
//        return crUserDAO.deleteByExample(example);
            int num = crUserDAO.deleteByPrimaryKey(userId);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}

