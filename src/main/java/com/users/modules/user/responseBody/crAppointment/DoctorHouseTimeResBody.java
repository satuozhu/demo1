package com.users.modules.user.responseBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）响应参数类
 */
@Data
@ApiModel(value = "DoctorHouseTimeResBody响应参数类", description = "查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）响应参数类")
public class DoctorHouseTimeResBody implements Serializable {

    private static final long serialVersionUID = -5316346707165279227L;

    @ApiModelProperty(value = "主键ID", example = "主键ID")
    private Long id;
    @ApiModelProperty(value = "医生编号", example = "医生编号")
    private Long doctorId;
//    @ApiModelProperty(value = "预约日期", example = "预约日期")
//    private String date;
    @ApiModelProperty(value = "预约日期", example = "预约日期")
    private String timeDate;
    @ApiModelProperty(value = "日期时间段", example = "日期时间段")
    private String timeSlot;
    @ApiModelProperty(value = "诊室编号", example = "诊室编号")
    private Long houseId;
    @ApiModelProperty(value = "是否已被预约（0=false否 1=true是）", example = "是否已被预约（0=false否 1=true是）")
    private Boolean status;
    @ApiModelProperty(value = "是否启用预约（0=false否 1=true是）", example = "是否启用预约（0=false否 1=true是）")
    private Boolean enable;

}