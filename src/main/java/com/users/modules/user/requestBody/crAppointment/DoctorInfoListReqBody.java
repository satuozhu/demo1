package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 根据患者编号查询我的医生请求参数类
 */
@ApiModel(value = "DoctorInfoListReqBody请求参数类", description = "根据患者编号查询我的医生请求参数类")
public class DoctorInfoListReqBody extends Page implements Serializable {

    private static final long serialVersionUID = 5261339835613956725L;

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
        return "DoctorInfoListReqBody{" +
                "userId=" + userId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}