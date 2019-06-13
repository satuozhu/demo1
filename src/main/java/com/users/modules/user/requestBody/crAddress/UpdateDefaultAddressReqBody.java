package com.users.modules.user.requestBody.crAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 修改为默认地址请求参数类
 */
@ApiModel(value = "UpdateDefaultAddressReqBody请求参数类", description = "修改为默认地址请求参数类")
public class UpdateDefaultAddressReqBody implements Serializable {

    private static final long serialVersionUID = -8250590336441826736L;

    @ApiModelProperty(value = "主键ID", required = true, allowableValues = "0")
    private Long id;

    @ApiModelProperty(value = "患者编号", required = true, allowableValues = "4")
    private Long userId;

    @ApiModelProperty(value = "默认地址（0否、1是）", required = true, allowableValues = "0")
//    private Boolean defaultAddress;
    private Integer defaultAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Override
    public String toString() {
        return "UpdateDefaultAddressReqBody{" +
                "id=" + id +
                ", userId=" + userId +
                ", defaultAddress=" + defaultAddress +
                '}';
    }

}