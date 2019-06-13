package com.users.modules.user.requestBody.crAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 添加收货地址请求参数类
 */
@ApiModel(value = "AddAddressReqBody请求参数类", description = "添加收货地址请求参数类")
public class AddAddressReqBody implements Serializable {

    private static final long serialVersionUID = -7411143306130010824L;

    @ApiModelProperty(value = "地区编号", required = true, allowableValues = "440304")
    private Long areaId;

    @ApiModelProperty(value = "街道", required = true, allowableValues = "保税区街道")
    private String street;

    @ApiModelProperty(value = "详细地址", required = true, allowableValues = "广东省深圳市福田区保税区街道")
    private String detailAddress;

    @ApiModelProperty(value = "患者编号", required = true, allowableValues = "4")
    private Long userId;

    @ApiModelProperty(value = "联系人", required = true, allowableValues = "李四")
    private String concacts;

    @ApiModelProperty(value = "联系方式", required = true, allowableValues = "13049881614")
    private String phone;

    @ApiModelProperty(value = "默认地址（0否、1是）", required = true, allowableValues = "1")
//    private Boolean defaultAddress;
    private Integer defaultAddress;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConcacts() {
        return concacts;
    }

    public void setConcacts(String concacts) {
        this.concacts = concacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Override
    public String toString() {
        return "AddAddressReqBody{" +
                "areaId=" + areaId +
                ", street='" + street + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", userId=" + userId +
                ", concacts='" + concacts + '\'' +
                ", phone='" + phone + '\'' +
                ", defaultAddress=" + defaultAddress +
                '}';
    }

}