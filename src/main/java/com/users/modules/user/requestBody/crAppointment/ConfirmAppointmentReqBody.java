package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 确认预约请求参数类
 */
@ApiModel(value = "ConfirmAppointmentReqBody请求参数类", description = "确认预约请求参数类")
public class ConfirmAppointmentReqBody implements Serializable {

    private static final long serialVersionUID = 1461679476642343375L;

    @ApiModelProperty(value = "患者编号", required = true, allowableValues = "4")
    private Long userId;

    @ApiModelProperty(value = "医生编号", required = true, allowableValues = "1")
    private Long doctorId;

    @ApiModelProperty(value = "诊室编号", required = true, allowableValues = "1")
    private Long houseId;

    @ApiModelProperty(value = "预约时间编号", required = true, allowableValues = "2")
    private Long appointmentTimeId;

    //dataType = "BigDecimal", allowableValues = "50" 没什么用
    @ApiModelProperty(value = "咨询费(挂号费)", required = true, dataType = "BigDecimal", allowableValues = "50")
    private BigDecimal consultPrice;

    @ApiModelProperty(value = "预约日期", required = true, allowableValues = "2019-04-17")
    private String appointmentDate;

    @ApiModelProperty(value = "预约时间", required = true, allowableValues = "09:00-09:30")
    private String timeSlot;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getAppointmentTimeId() {
        return appointmentTimeId;
    }

    public void setAppointmentTimeId(Long appointmentTimeId) {
        this.appointmentTimeId = appointmentTimeId;
    }

    public BigDecimal getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(BigDecimal consultPrice) {
        this.consultPrice = consultPrice;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "ConfirmAppointmentReqBody{" +
                "userId=" + userId +
                ", doctorId=" + doctorId +
                ", houseId=" + houseId +
                ", appointmentTimeId=" + appointmentTimeId +
                ", consultPrice=" + consultPrice +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }

}