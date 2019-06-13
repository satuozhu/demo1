package com.users.modules.user.requestBody.crUserCertification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 添加身份验证请求参数类
 */
@ApiModel(value = "AddUserCertificationReqBody请求参数类", description = "添加身份验证请求参数类")
public class AddUserCertificationReqBody implements Serializable {

    private static final long serialVersionUID = 6777032677262793870L;

    @ApiModelProperty(value = "用户编号", required = true, allowableValues = "0")
    private Long userId;

    @ApiModelProperty(value = "身份证人象面url", required = true)
    private String idCardFront;

    @ApiModelProperty(value = "身份证国徽面url", required = true)
    private String idCardBack;

    @ApiModelProperty(value = "刷脸认证url", required = true)
    private String faceAuth;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getFaceAuth() {
        return faceAuth;
    }

    public void setFaceAuth(String faceAuth) {
        this.faceAuth = faceAuth;
    }

    @Override
    public String toString() {
        return "AddUserCertificationReqBody{" +
                "userId=" + userId +
                ", idCardFront='" + idCardFront + '\'' +
                ", idCardBack='" + idCardBack + '\'' +
                ", faceAuth='" + faceAuth + '\'' +
                '}';
    }

}