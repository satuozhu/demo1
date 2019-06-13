package com.users.modules.user.service.impl;

import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.CheckUtil;
import com.users.component.util.RedisUtils;
import com.users.modules.user.entity.CrUser;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.requestBody.login.BindUserAccountReqBody;
import com.users.modules.user.requestBody.login.UnbindUserAccountReqBody;
import com.users.modules.user.service.CrUserService;
import com.users.modules.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 登录service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CrUserService crUserService;
    @Autowired
    private CrUserMapper crUserDAO;

    /**
     * 找回密码(手机号、邮箱)
     *
     * @param crUser
     * @return
     */
    @Override
    public int retrievePassword(CrUser crUser) {
        crUser.setUpdateTime(System.currentTimeMillis());//13位时间戳
//        //修改的条件
//        CrUserExample example =new CrUserExample();
//        example.createCriteria().andIdEqualTo(crUserReg.getId());
//        return crUserDAO.updateByExampleSelective(crUser, example);
        return crUserDAO.updateByPrimaryKeySelective(crUser);
    }

    /**
     * 用户绑定(手机号、邮箱)
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> bindUserAccount(BindUserAccountReqBody reqBody) {
        Long userId = reqBody.getUserId();
        String userAccount = reqBody.getUserAccount();//获取验证码的手机
        int type = reqBody.getType();
        String code = reqBody.getCode();
        try {
            //校验验证码
            String redCode = null;
            if (type == 4) {//4手机绑定
                redCode = (String) redisUtils.get("verifCode:" + userAccount + "bind_phone_verifcode");
            }
            if (type == 14) {//14邮箱绑定
                redCode = (String) redisUtils.get("verifCode:" + userAccount + "bind_email_verifcode");
            }
            if (redCode == null || !code.equalsIgnoreCase(redCode)) {//equalsIgnoreCase忽略大小写
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_CODE_ERROR);
            }

            //校验账号
            CrUser crUser = new CrUser();
            CrUser cu = null;
            if (CheckUtil.isMobile(userAccount)) {//是手机号
                crUser.setPhone(userAccount);
                cu = crUserService.findCrUserByPhone(userAccount);
            } else if (CheckUtil.isEmail(userAccount)) {//是邮箱
                crUser.setEmail(userAccount);
                cu = crUserService.findCrUserByEmail(userAccount);
            } else {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_USER_ACCOUNT_ERROR);
            }
            if (cu != null) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_BIND_USER_ACCOUNT);
            }

            crUser.setId(userId);
            crUser.setUpdateTime(System.currentTimeMillis());//13位时间戳
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
     * 用户解除绑定(手机号、邮箱)
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> unbindUserAccount(UnbindUserAccountReqBody reqBody) {
        Long userId = reqBody.getUserId();
        String userAccount = reqBody.getUserAccount();//获取验证码的手机
        int type = reqBody.getType();
        String code = reqBody.getCode();
        try {
            //校验验证码
            String redCode = null;
            if (type == 4) {//4手机绑定
                redCode = (String) redisUtils.get("verifCode:" + userAccount + "bind_phone_verifcode");
            }
            if (type == 14) {//14邮箱绑定
                redCode = (String) redisUtils.get("verifCode:" + userAccount + "bind_email_verifcode");
            }
            if (redCode == null || !code.equalsIgnoreCase(redCode)) {//equalsIgnoreCase忽略大小写
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_CODE_ERROR);
            }

            //校验账号
            CrUser crUser = new CrUser();
            CrUser cu = null;
            if (CheckUtil.isMobile(userAccount)) {//是手机号
                crUser.setPhone("");
                cu = crUserService.findCrUserByPhone(userAccount);
            } else if (CheckUtil.isEmail(userAccount)) {//是邮箱
                crUser.setEmail("");
                cu = crUserService.findCrUserByEmail(userAccount);
            } else {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_USER_ACCOUNT_ERROR);
            }
            if (cu == null) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_NO_USER_ACCOUNT);
            }

            crUser.setId(userId);
            crUser.setUpdateTime(System.currentTimeMillis());//13位时间戳
            if (crUserDAO.updateByPrimaryKeySelective(crUser) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}

