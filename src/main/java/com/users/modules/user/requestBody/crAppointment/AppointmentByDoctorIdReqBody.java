package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 根据医生ID查询预约信息列表请求参数类
 */
@ApiModel(value = "AppointmentByDoctorIdReqBody请求参数类", description = "根据医生ID查询预约信息列表请求参数类")
public class AppointmentByDoctorIdReqBody extends Page implements Serializable {

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
        return "AppointmentByDoctorIdReqBody{" +
                "doctorId=" + doctorId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}