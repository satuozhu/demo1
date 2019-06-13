package com.users.modules.user.responseBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 根据患者编号查询我的处方响应参数类
 */
@Data
@ApiModel(value = "PrescriptionByUserIdResBody响应参数类", description = "根据患者编号查询我的处方响应参数类")
public class PrescriptionByUserIdResBody implements Serializable {

    private static final long serialVersionUID = 7687095727394140723L;

    @ApiModelProperty(value = "主键ID", example = "主键ID")
    private Long id;
    @ApiModelProperty(value = "病因", example = "病因")
    private String cause;
    @ApiModelProperty(value = "诊断结果", example = "诊断结果")
    private String diagnosis;
    @ApiModelProperty(value = "组成成分", example = "组成成分")
    private String composed;
    @ApiModelProperty(value = "使用方式", example = "使用方式")
    private String usageMode;
    @ApiModelProperty(value = "功效", example = "功效")
    private String efficacy;
    @ApiModelProperty(value = "预约单编号", example = "预约单编号")
    private Long appointmentId;
    @ApiModelProperty(value = "医生编号", example = "医生编号")
    private Long doctorId;
    @ApiModelProperty(value = "患者编号", example = "患者编号")
    private Long userId;
    @ApiModelProperty(value = "是否平台配送 0 是 1 否", example = "是否平台配送 0 是 1 否")
    private Integer isDistribution;
    @ApiModelProperty(value = "价格(选择平台配送时)", example = "价格(选择平台配送时)")
    private BigDecimal price;
    @ApiModelProperty(value = "链接路径", example = "链接路径")
    private String linkUrl;
    @ApiModelProperty(value = "创建日期", example = "创建日期")
    private Long createDate;
    @ApiModelProperty(value = "更新日期", example = "更新日期")
    private Long updateDate;
    @ApiModelProperty(value = "所属医院", example = "所属医院")
    private String hospital;
    @ApiModelProperty(value = "所属科", example = "所属科")
    private String section;
    @ApiModelProperty(value = "医生级别", example = "医生级别")
    private String level;
    @ApiModelProperty(value = "医生名称", example = "医生名称")
    private String doctorName;

}