package com.common.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * cr_appointment_again预约(复诊)表实体类
 * @author
 */
@ApiModel(value = "CrAppointmentAgain实体类", description = "预约(复诊)表实体类")
public class CrAppointmentAgain implements Serializable {

    private static final long serialVersionUID = 2426675849575518778L;

    @ApiModelProperty(value = "主键ID", example = "主键ID")
    private Long id;

    @ApiModelProperty(value = "患者编号", example = "患者编号")
    private Long userId;

    @ApiModelProperty(value = "医生编号", example = "医生编号")
    private Long doctorId;

    @ApiModelProperty(value = "诊室编号", example = "诊室编号")
    private Long houseId;

    @ApiModelProperty(value = "预约时间编号", example = "预约时间编号")
    private Long appointmentTimeId;

    @ApiModelProperty(value = "预约原因", example = "预约原因")
    private String cause;

    @ApiModelProperty(value = "咨询费(挂号费)", example = "咨询费(挂号费)")
    private BigDecimal consultPrice;

    @ApiModelProperty(value = "预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)", example = "预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)")
    private String status;

    @ApiModelProperty(value = "物流编号", example = "物流编号")
    private Long expressId;

    @ApiModelProperty(value = "总费用(含处方平台配送费)", example = "总费用(含处方平台配送费)")
    private BigDecimal sumPrice;

    @ApiModelProperty(value = "评分", example = "评分")
    private Integer score;

    @ApiModelProperty(value = "物流状态 0 进行中 1 已完成 2 配送中", example = "物流状态 0 进行中 1 已完成 2 配送中")
    private Integer exStatus;

    @ApiModelProperty(value = "预约编号", example = "预约编号")
    private Long appointmentId;

    @ApiModelProperty(value = "预约日期", example = "预约日期")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", example = "更新时间")
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public BigDecimal getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(BigDecimal consultPrice) {
        this.consultPrice = consultPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getExStatus() {
        return exStatus;
    }

    public void setExStatus(Integer exStatus) {
        this.exStatus = exStatus;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CrAppointmentAgain other = (CrAppointmentAgain) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()))
            && (this.getHouseId() == null ? other.getHouseId() == null : this.getHouseId().equals(other.getHouseId()))
            && (this.getAppointmentTimeId() == null ? other.getAppointmentTimeId() == null : this.getAppointmentTimeId().equals(other.getAppointmentTimeId()))
            && (this.getCause() == null ? other.getCause() == null : this.getCause().equals(other.getCause()))
            && (this.getConsultPrice() == null ? other.getConsultPrice() == null : this.getConsultPrice().equals(other.getConsultPrice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExpressId() == null ? other.getExpressId() == null : this.getExpressId().equals(other.getExpressId()))
            && (this.getSumPrice() == null ? other.getSumPrice() == null : this.getSumPrice().equals(other.getSumPrice()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getExStatus() == null ? other.getExStatus() == null : this.getExStatus().equals(other.getExStatus()))
            && (this.getAppointmentId() == null ? other.getAppointmentId() == null : this.getAppointmentId().equals(other.getAppointmentId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
        result = prime * result + ((getHouseId() == null) ? 0 : getHouseId().hashCode());
        result = prime * result + ((getAppointmentTimeId() == null) ? 0 : getAppointmentTimeId().hashCode());
        result = prime * result + ((getCause() == null) ? 0 : getCause().hashCode());
        result = prime * result + ((getConsultPrice() == null) ? 0 : getConsultPrice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExpressId() == null) ? 0 : getExpressId().hashCode());
        result = prime * result + ((getSumPrice() == null) ? 0 : getSumPrice().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getExStatus() == null) ? 0 : getExStatus().hashCode());
        result = prime * result + ((getAppointmentId() == null) ? 0 : getAppointmentId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", doctorId=").append(doctorId);
        sb.append(", houseId=").append(houseId);
        sb.append(", appointmentTimeId=").append(appointmentTimeId);
        sb.append(", cause=").append(cause);
        sb.append(", consultPrice=").append(consultPrice);
        sb.append(", status=").append(status);
        sb.append(", expressId=").append(expressId);
        sb.append(", sumPrice=").append(sumPrice);
        sb.append(", score=").append(score);
        sb.append(", exStatus=").append(exStatus);
        sb.append(", appointmentId=").append(appointmentId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}