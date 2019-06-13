package com.users.modules.user.service;

import com.users.modules.user.entity.CrUser;
import com.users.modules.user.requestBody.registered.RegisteredAccountReqBody;

/**
 * 注册service接口
 */
public interface RegisteredService {

    /**
     * 注册(手机号、邮箱)
     * @param userAccount
     * @param pwd
     * @return
     */
    CrUser registered(String userAccount, String pwd);

    /**
     * 注册(账号)
     *
     * @param reqBody
     * @return
     */
    CrUser registeredAccount(RegisteredAccountReqBody reqBody);

}

