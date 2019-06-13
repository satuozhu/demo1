package com.users.modules.user.responseBody.crAppointment;

import com.common.entity.user.CrAppointmentTime;
import com.common.entity.user.CrHouse;
import com.common.entity.user.CrUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 根据医生ID查询预约信息响应参数类
 */
@Data
@ApiModel(value = "AppointmentByDoctorIdResBody响应参数类", description = "根据医生ID查询预约信息响应参数类")
public class AppointmentByDoctorIdResBody implements Serializable {

    private static final long serialVersionUID = -4785927974073940978L;

    @ApiModelProperty(value = "主键ID",example = "主键ID")
    private Long id;
    @ApiModelProperty(value = "患者编号",example = "患者编号")
    private Long userId;
    @ApiModelProperty(value = "医生编号",example = "医生编号")
    private Long doctorId;
    @ApiModelProperty(value = "诊室编号",example = "诊室编号")
    private Long houseId;
    @ApiModelProperty(value = "预约时间编号",example = "预约时间编号")
    private Long appointmentTimeId;
    @ApiModelProperty(value = "预约原因",example = "预约原因")
    private String cause;
    @ApiModelProperty(value = "咨询费(挂号费)",example = "咨询费(挂号费")
    private BigDecimal consultPrice;
    @ApiModelProperty(value = "预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)",example = "预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)")
    private String status;
//    @ApiModelProperty(value = "处方编号",example = "处方编号")
//    private Long prescriptionId;
    @ApiModelProperty(value = "物流编号",example = "物流编号")
    private Long expressId;
    @ApiModelProperty(value = "总费用(含处方平台配送费)",example = "总费用(含处方平台配送费)")
    private BigDecimal sumPrice;
    @ApiModelProperty(value = "评分",example = "评分")
    private Integer score;
    @ApiModelProperty(value = "物流状态 0 进行中 1 已完成 2 配送中",example = "物流状态 0 进行中 1 已完成 2 配送中")
    private Integer exStatus;
    @ApiModelProperty(value = "复诊状态(0=false否、1=true是)",example = "复诊状态(0=false否、1=true是)")
    private Boolean againStatus;
    @ApiModelProperty(value = "预约编号", example = "预约编号")
    private Long appointmentId;
    @ApiModelProperty(value = "创建时间",example = "创建时间")
    private Long createTime;
    @ApiModelProperty(value = "更新时间", example = "更新时间")
    private Long updateTime;
    @ApiModelProperty(value = "预约日期", example = "预约日期")
    private String timeDate;
    @ApiModelProperty(value = "日期时间段", example = "日期时间段")
    private String timeSlot;
    @ApiModelProperty(value ="患者信息",example = "患者信息")
    private CrUser user;
    @ApiModelProperty(value = "预约时间段", example = "预约时间段")
    private CrAppointmentTime appointmentTime;
    @ApiModelProperty(value = "诊室信息",example = "诊室信息")
    private CrHouse house;

}