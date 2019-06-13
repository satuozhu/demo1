package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 添加提问请求参数类
 */
@ApiModel(value = "AddCrQuestionReqBody请求参数类", description = "添加提问请求参数类")
public class AddCrQuestionReqBody implements Serializable {

    private static final long serialVersionUID = -9085148425152116113L;

    @ApiModelProperty(value = "问题类型（0系统、1医生）", required = true, allowableValues = "1")
    private Integer type;

    @ApiModelProperty(value = "提问内容", required = true, allowableValues = "请问身体营养不良怎么办？")
    private String content;

    @ApiModelProperty(value = "用户编号", required = true, allowableValues = "4")
    private Long userId;

    @ApiModelProperty(value = "回复者编号", required = true, allowableValues = "1")
    private Long beanId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBeanId() {
        return beanId;
    }

    public void setBeanId(Long beanId) {
        this.beanId = beanId;
    }

    @Override
    public String toString() {
        return "AddCrQuestionReqBody{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", beanId=" + beanId +
                '}';
    }

}