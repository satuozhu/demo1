package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询医生经典案例请求参数类
 */
@ApiModel(value = "DoctorCaseReqBody请求参数类", description = "查询医生经典案例请求参数类")
public class DoctorCaseReqBody extends Page implements Serializable {

    private static final long serialVersionUID = 2895354742720229470L;

    @ApiModelProperty(value = "医生编号", required = true, allowableValues = "1")
    private Long doctorId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "DoctorCaseReqBody{" +
                "doctorId=" + doctorId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}