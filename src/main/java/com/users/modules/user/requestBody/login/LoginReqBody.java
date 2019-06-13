package com.users.modules.user.requestBody.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 密码登录(账号、手机号、邮箱)请求参数类
 */
@ApiModel(value = "LoginReqBody请求参数类", description = "密码登录(账号、手机号、邮箱)请求参数类")
public class LoginReqBody implements Serializable {

    private static final long serialVersionUID = -7717331113890096155L;

//    @ApiModelProperty(value = "用户ID", required = true, dataType = "Long")
//    private Long id;

    @ApiModelProperty(value = "用户账号", required = true, allowableValues = "13049881621")
    private String userAccount;

    @ApiModelProperty(value = "用户密码", required = true, allowableValues = "123456")
    private String pwd;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
        return "LoginReqBody{" +
                "userAccount='" + userAccount + '\'' +
                ", pwd='" + pwd + '\'' +
                ", platformType=" + platformType +
                ", loginType=" + loginType +
                '}';
    }

}