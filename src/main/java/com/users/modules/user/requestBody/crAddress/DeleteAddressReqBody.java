package com.users.modules.user.requestBody.crAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 删除收货地址请求参数类
 */
@ApiModel(value = "DeleteAddressReqBody请求参数类", description = "删除收货地址请求参数类")
public class DeleteAddressReqBody implements Serializable {

    private static final long serialVersionUID = -4023225425799411497L;

    @ApiModelProperty(value = "地址记录的ID", required = true, allowableValues = "0")
    private Long addressId;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "DeleteAddressReqBody{" +
                "addressId=" + addressId +
                '}';
    }

}