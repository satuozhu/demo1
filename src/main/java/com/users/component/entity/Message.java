package com.users.component.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 全局返回对象响应参数类
 */
@ApiModel(value = "Message<T>响应参数类", description = "全局返回对象响应参数类")
public class Message<T> implements Serializable {

    private static final long serialVersionUID = 2236403452909816924L;

    @ApiModelProperty(value = "响应状态码(基础的：200成功、407失败、500异常)", example = "响应状态码(基础的：200成功、407失败、500异常)", required = true)
    private Integer code;//响应状态码
    @ApiModelProperty(value = "类型", example = "类型", required = true)
    private String type;//类型
    @ApiModelProperty(value = "提示信息(基础的：操作成功、操作失败、程序异常)", example = "提示信息(基础的：操作成功、操作失败、程序异常)", required = true)
    private String msg;//提示信息
    @ApiModelProperty(value = "异常信息", example = "异常信息", required = true)
    private String exception;//异常信息
    @ApiModelProperty(value = "13位时间戳", example = "13位时间戳", required = true)
    private Long timestamp;//时间戳
    @ApiModelProperty(value = "响应内容(分页时查询结构为MyPageInfo<展示类>)", example = "响应内容(分页时查询结构为MyPageInfo<展示类>)", required = true)
    private T data;//响应内容

    public Message() {
    }

    public Message(Integer code, String msg) {
        super();
        this.code = code;
        this.type = "";
        this.msg = msg;
        this.exception = "";
        this.timestamp = System.currentTimeMillis();
        this.data = (T) new ResultNullDTO();
    }

    public Message(String msg, String exception) {
        super();
        this.code = STATUS.CODE_FAILURE;
        this.type = "";
        this.msg = msg;
        this.exception = exception;
        this.timestamp = System.currentTimeMillis();
        this.data = (T) new ResultNullDTO();
    }

    public Message(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.type = "";
        this.msg = msg;
        this.exception = "";
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
