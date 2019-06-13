package com.users.modules.user.service;

import com.users.component.entity.Message;
import com.users.modules.user.requestBody.wechat.BindWechatReqBody;
import com.users.modules.user.requestBody.login.LoginWechatAuthReqBody;
import com.users.modules.user.requestBody.wechat.UnbindWechatReqBody;

/**
 * 微信service接口
 */
public interface WechatService {

    /**
     * 微信登录授权
     *
     * @param reqBody
     * @return
     */
    Message<?> loginWechatAuth(LoginWechatAuthReqBody reqBody);

    /**
     * 用户绑定、更换微信
     * @param reqBody
     * @return
     */
    Message<?> bindWechat(BindWechatReqBody reqBody);

    /**
     * 用户解除绑定微信
     * @param reqBody
     * @return
     */
    Message<?> unbindWechat(UnbindWechatReqBody reqBody);

}