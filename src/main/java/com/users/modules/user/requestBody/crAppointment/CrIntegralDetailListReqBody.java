package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询最近30天的积分明细请求参数类
 */
@ApiModel(value = "CrUserIntegralListReqBody请求参数类", description = "分页查询最近30天的积分明细请求参数类")
public class CrIntegralDetailListReqBody extends Page implements Serializable {

    private static final long serialVersionUID = 1584133308157779237L;

    @ApiModelProperty(value = "用户编号", required = true, allowableValues = "4")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CrIntegralDetailListReqBody{" +
                "userId=" + userId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }
}