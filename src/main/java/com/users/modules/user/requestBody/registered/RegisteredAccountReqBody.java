package com.users.modules.user.requestBody.registered;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 注册(账号)请求参数类
 */
@ApiModel(value = "RegisteredAccountReqBody请求参数类", description = "注册(账号)请求参数类")
public class RegisteredAccountReqBody implements Serializable {

    private static final long serialVersionUID = -7815022690401980023L;

    @ApiModelProperty(value = "用户账号(数字和字母组合)", required = true, allowableValues = "lyl_01")
    private String userAccount;

    @ApiModelProperty(value = "用户密码", required = true, allowableValues = "123456")
    private String pwd;

    @ApiModelProperty(value = "重复密码", required = true, allowableValues = "123456")
    private String repwd;

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

    public String getRepwd() {
        return repwd;
    }

    public void setRepwd(String repwd) {
        this.repwd = repwd;
    }

    @Override
    public String toString() {
        return "RegisteredAccountReqBody{" +
                "userAccount='" + userAccount + '\'' +
                ", pwd='" + pwd + '\'' +
                ", repwd='" + repwd + '\'' +
                '}';
    }

}