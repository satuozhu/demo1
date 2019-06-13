package com.users.modules.user.service;

import com.users.component.entity.Message;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.requestBody.login.BindUserAccountReqBody;
import com.users.modules.user.requestBody.login.UnbindUserAccountReqBody;

/**
 * 登录service接口
 */
public interface LoginService {

    /**
     * 找回密码(手机号、邮箱)
     *
     * @param crUser
     * @return
     */
    int retrievePassword(CrUser crUser);

    /**
     * 用户绑定(手机号、邮箱)
     *
     * @param reqBody
     * @return
     */
    Message<?> bindUserAccount(BindUserAccountReqBody reqBody);

    /**
     * 用户解除绑定(手机号、邮箱)
     *
     * @param reqBody
     * @return
     */
    Message<?> unbindUserAccount(UnbindUserAccountReqBody reqBody);

}

