package com.users.modules.user.responseBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户查看预约响应参数类
 */
@Data
@ApiModel(value = "AppointmentResBody响应参数类", description = "用户查看预约响应参数类")
public class AppointmentResBody implements Serializable {

    private static final long serialVersionUID = -260465985239748411L;

    @ApiModelProperty(value = "主键ID",example = "主键ID")
    private Long id;
    @ApiModelProperty(value = "预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)",example = "预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)")
    private String status;
    @ApiModelProperty(value = "预约详情")
    private AppointmentListResBody appointment;

}