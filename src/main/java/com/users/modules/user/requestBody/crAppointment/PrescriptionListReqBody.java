package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 根据患者编号查询我的处方请求参数类
 */
@ApiModel(value = "PrescriptionListReqBody请求参数类", description = "根据患者编号查询我的处方请求参数类")
public class PrescriptionListReqBody extends Page implements Serializable {

    private static final long serialVersionUID = 4388193480004742499L;

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
        return "PrescriptionListReqBody{" +
                "userId=" + userId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}