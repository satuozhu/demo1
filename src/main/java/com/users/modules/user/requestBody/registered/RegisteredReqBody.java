package com.users.modules.user.requestBody.registered;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 注册(手机号、邮箱)请求参数类
 */
@ApiModel(value = "RegisteredReqBody请求参数类", description = "注册(手机号、邮箱)请求参数类")
public class RegisteredReqBody implements Serializable {

    private static final long serialVersionUID = -8597918321770693503L;

    @ApiModelProperty(value = "用户账号", required = true, allowableValues = "13049881621")
    private String userAccount;

    @ApiModelProperty(value = "类型：1手机注册2手机重置密码3手机验证码登录4手机验证码绑定；11邮箱注册12邮箱重置密码13邮箱验证码登录14邮箱验证码绑定", dataType = "Integer", required = true, allowableValues = "1")
    private Integer type;

    @ApiModelProperty(value = "验证码", required = true, allowableValues = "201314")
    private String code;

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
        return "RetrievePasswordReqBody{" +
                "userAccount='" + userAccount + '\'' +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", pwd='" + pwd + '\'' +
                ", repwd='" + repwd + '\'' +
                '}';
    }

}