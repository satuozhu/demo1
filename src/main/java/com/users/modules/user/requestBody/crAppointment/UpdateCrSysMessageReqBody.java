package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 更新消息状态为已读请求参数类
 */
@ApiModel(value = "UpdateCrSysMessageReqBody请求参数类", description = "更新消息状态为已读请求参数类")
public class UpdateCrSysMessageReqBody implements Serializable {

    private static final long serialVersionUID = -8963303863890632718L;

    @ApiModelProperty(value = "用户名称", required = true, allowableValues = "0")
    private Long messageId;

    @ApiModelProperty(value = "用户编号", required = true, allowableValues = "4")
    private Long userId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UpdateCrSysMessageReqBody{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                '}';
    }

}