package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 添加积分记录请求参数类
 */
@ApiModel(value = "AddCrIntegralDetaiReqBody请求参数类", description = "添加积分记录请求参数类")
public class AddCrIntegralDetailReqBody implements Serializable {

    private static final long serialVersionUID = 2624662831750862444L;

    @ApiModelProperty(value = "用户编号", required = true, allowableValues = "4")
    private Long userId;

    @ApiModelProperty(value = "用户名称", required = true, allowableValues = "李四")
    private String userName;

    @ApiModelProperty(value = "来源对象，比如评论了“牛二”", allowableValues = "评论了“牛二”")
    private String sourceObject;

    @ApiModelProperty(value = "积分类型", required = true, allowableValues = "1001")
    private String type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(String sourceObject) {
        this.sourceObject = sourceObject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AddCrIntegralDetailReqBody{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sourceObject='" + sourceObject + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}