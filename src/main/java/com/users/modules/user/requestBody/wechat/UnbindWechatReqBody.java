package com.users.modules.user.requestBody.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户解除绑定微信请求参数类
 */
@ApiModel(value = "UnbindWechatReqBody请求参数类", description = "用户解除绑定微信请求参数类")
public class UnbindWechatReqBody implements Serializable {

    private static final long serialVersionUID = 911594529745390755L;

    @ApiModelProperty(value = "用户ID", required = true, dataType = "Long")
    private Long userId;

    @ApiModelProperty(value = "微信openId", required = true, allowableValues = "ovx821kaTbdYMFbYl5BqPrtHObm4")
    private String openId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "UnbindWechatReqBody{" +
                "userId=" + userId +
                ", openId='" + openId + '\'' +
                '}';
    }

}