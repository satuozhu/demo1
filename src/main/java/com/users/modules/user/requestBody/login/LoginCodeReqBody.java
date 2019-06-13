package com.users.modules.user.requestBody.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 验证码登录(手机号、邮箱)请求参数类
 */
@ApiModel(value = "LoginCodeReqBody请求参数类", description = "验证码登录(手机号、邮箱)请求参数类")
public class LoginCodeReqBody implements Serializable {

    private static final long serialVersionUID = 1212055647967424647L;

    @ApiModelProperty(value = "用户账号", required = true, allowableValues = "13049881621")
    private String userAccount;

    @ApiModelProperty(value = "类型：1手机注册2手机重置密码3手机验证码登录4手机验证码绑定；11邮箱注册12邮箱重置密码13邮箱验证码登录14邮箱验证码绑定", dataType = "Integer", required = true, allowableValues = "3")
    private Integer type;

    @ApiModelProperty(value = "验证码", required = true, allowableValues = "201314")
    private String code;

    @ApiModelProperty(value = "平台类型（1移动端、2PC端）", required = true)
    private Integer platformType;

    @ApiModelProperty(value = "登录类型（1账号、2微信、3手机、4邮箱）", required = true)
    private Integer loginType;

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

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "LoginCodeReqBody{" +
                "userAccount='" + userAccount + '\'' +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", platformType=" + platformType +
                ", loginType=" + loginType +
                '}';
    }

}