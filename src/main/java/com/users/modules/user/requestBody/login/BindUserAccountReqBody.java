package com.users.modules.user.requestBody.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户绑定、更换(手机号、邮箱)请求参数类
 */
@ApiModel(value = "BindUserAccountReqBody请求参数类", description = "用户绑定、更换(手机号、邮箱)请求参数类")
public class BindUserAccountReqBody implements Serializable {

    private static final long serialVersionUID = -7792257298556113157L;

    @ApiModelProperty(value = "用户ID", required = true, dataType = "Long")
    private Long userId;

    @ApiModelProperty(value = "用户账号", required = true, allowableValues = "13049881621")
    private String userAccount;

//    @ApiModelProperty(value = "用户新账号", required = true, allowableValues = "13049881621")
//    private String userAccountNew;

    @ApiModelProperty(value = "类型：1手机注册2手机重置密码3手机验证码登录4手机验证码绑定；11邮箱注册12邮箱重置密码13邮箱验证码登录14邮箱验证码绑定", dataType = "Integer", required = true, allowableValues = "4")
    private Integer type;

    @ApiModelProperty(value = "验证码", required = true, allowableValues = "201314")
    private String code;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BindUserAccountReqBody{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", type=" + type +
                ", code='" + code + '\'' +
                '}';
    }

}