package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 回复提问请求参数类
 */
@ApiModel(value = "ReplyCrQuestionReqBody请求参数类", description = "回复提问请求参数类")
public class ReplyCrQuestionReqBody implements Serializable {

    private static final long serialVersionUID = -5632396663570988818L;

    @ApiModelProperty(value = "主键,问题ID", required = true, allowableValues = "1")
    private Long id;

    @ApiModelProperty(value = "回复内容", required = true, allowableValues = "多吃蔬菜水果，多锻炼！")
    private String replyContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    @Override
    public String toString() {
        return "ReplyCrQuestionReqBody{" +
                "id=" + id +
                ", replyContent='" + replyContent + '\'' +
                '}';
    }

}