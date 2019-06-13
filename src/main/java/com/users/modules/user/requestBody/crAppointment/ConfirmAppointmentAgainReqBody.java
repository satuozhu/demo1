package com.users.modules.user.requestBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 确认复诊请求参数类
 */
@ApiModel(value = "ConfirmAppointmentAgainReqBody请求参数类", description = "确认复诊请求参数类")
public class ConfirmAppointmentAgainReqBody implements Serializable {

    private static final long serialVersionUID = 779018820096405016L;

    @ApiModelProperty(value = "患者编号", required = true, allowableValues = "4")
    private Long userId;

    @ApiModelProperty(value = "患者名称", required = true, allowableValues = "李四")
    private String userName;

    @ApiModelProperty(value = "医生编号", required = true, allowableValues = "1")
    private Long doctorId;

    @ApiModelProperty(value = "医生名称", required = true, allowableValues = "吴雪医生")
    private String doctorName;

    @ApiModelProperty(value = "诊室编号", required = true, allowableValues = "1")
    private Long houseId;

    @ApiModelProperty(value = "诊室名称", required = true, allowableValues = "华宝一号诊室")
    private String houseName;

    @ApiModelProperty(value = "预约时间编号", required = true, allowableValues = "2")
    private Long appointmentTimeId;

    //dataType = "BigDecimal", allowableValues = "50" 没什么用
    @ApiModelProperty(value = "咨询费(挂号费)", required = true, dataType = "BigDecimal", allowableValues = "50")
    private BigDecimal consultPrice;

    @ApiModelProperty(value = "复诊日期", required = true, allowableValues = "2019-06-02")
    private String appointmentDate;

    @ApiModelProperty(value = "预约时间", required = true, allowableValues = "08:30-09:00")
    private String timeSlot;

    @ApiModelProperty(value = "预约编号", required = true, allowableValues = "0")
    private Long appointmentId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String toString() {
        return "ConfirmAppointmentAgainReqBody{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                ", appointmentTimeId=" + appointmentTimeId +
                ", consultPrice=" + consultPrice +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", appointmentId=" + appointmentId +
                '}';
    }

}