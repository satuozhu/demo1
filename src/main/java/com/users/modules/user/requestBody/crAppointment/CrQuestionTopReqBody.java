package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 查询最新的3条提问请求参数类
 */
@ApiModel(value = "CrQuestionTopReqBody请求参数类", description = "查询最新的3条提问请求参数类")
public class CrQuestionTopReqBody implements Serializable {

    private static final long serialVersionUID = -3409801305885794981L;

    @ApiModelProperty(value = "提问内容", required = true, allowableValues = "请问身体营养不良怎么办？")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CrQuestionTopReqBody{" +
                "content='" + content + '\'' +
                '}';
    }

}