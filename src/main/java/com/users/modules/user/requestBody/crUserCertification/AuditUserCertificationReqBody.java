package com.users.modules.user.requestBody.crUserCertification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 身份验证审核请求参数类
 */
@ApiModel(value = "AuditUserCertificationReqBody请求参数类", description = "身份验证审核请求参数类")
public class AuditUserCertificationReqBody implements Serializable {

    private static final long serialVersionUID = -8214120376124009645L;

    @ApiModelProperty(value = "主键编号", required = true, allowableValues = "0")
    private Long id;

    @ApiModelProperty(value = "认证状态：0未认证、1认证中、2认证通过、3认证失败", required = true, allowableValues = "0")
    private Integer authStatus;

    @ApiModelProperty(value = "认证失败原因，authStatus=3时，必须填写")
    private String failReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    @Override
    public String toString() {
        return "AuditUserCertificationReqBody{" +
                "id=" + id +
                ", authStatus=" + authStatus +
                ", failReason='" + failReason + '\'' +
                '}';
    }

}