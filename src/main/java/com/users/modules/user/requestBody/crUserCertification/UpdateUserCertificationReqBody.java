package com.users.modules.user.requestBody.crUserCertification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 修改身份验证请求参数类
 */
@ApiModel(value = "UpdateUserCertificationReqBody请求参数类", description = "修改身份验证请求参数类")
public class UpdateUserCertificationReqBody implements Serializable {

    private static final long serialVersionUID = 3181511198828751396L;

    @ApiModelProperty(value = "主键编号", required = true, allowableValues = "0")
    private Long id;

    @ApiModelProperty(value = "身份证人象面url", required = true)
    private String idCardFront;

    @ApiModelProperty(value = "身份证国徽面url", required = true)
    private String idCardBack;

    @ApiModelProperty(value = "刷脸认证url", required = true)
    private String faceAuth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "UpdateUserCertificationReqBody{" +
                "id=" + id +
                ", idCardFront='" + idCardFront + '\'' +
                ", idCardBack='" + idCardBack + '\'' +
                ", faceAuth='" + faceAuth + '\'' +
                '}';
    }

}