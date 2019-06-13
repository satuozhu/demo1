package com.users.modules.user.requestBody.crAppointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询正常运转的诊室信息（医生-时间段-诊室）请求参数类
 */
@ApiModel(value = "NormalHouseReqBody请求参数类", description = "查询正常运转的诊室信息（医生-时间段-诊室）请求参数类")
public class NormalHouseReqBody extends Page implements Serializable {

    private static final long serialVersionUID = -6190796969010626234L;

    @ApiModelProperty(value = "市级区域编号,查找该市的所有诊室", allowableValues = "440300")
    private Long areaId;

    @ApiModelProperty(value = "诊室名称", allowableValues = "转圈圈医疗中心")
    private String houseName;

    @ApiModelProperty(value = "预约日期", allowableValues = "2019-05-15")
    private String date;

    @ApiModelProperty(value = "日期时间段", allowableValues = "09:00-09:30")
    private String timeSlot;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "NormalHouseReqBody{" +
                "areaId=" + areaId +
                ", houseName=" + houseName +
                ", date='" + date +
                ", timeSlot='" + timeSlot +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}