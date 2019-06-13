package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户查看历史预约列表请求参数类
 */
@ApiModel(value = "AppointmentHistoryListReqBody请求参数类", description = "用户查看历史预约列表请求参数类")
public class AppointmentHistoryListReqBody extends Page implements Serializable {

    private static final long serialVersionUID = -333852181921265053L;

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
        return "AppointmentListReqBody{" +
                "userId=" + userId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}