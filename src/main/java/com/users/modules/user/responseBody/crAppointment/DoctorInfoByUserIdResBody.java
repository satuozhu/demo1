package com.users.modules.user.responseBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 根据患者编号查询我的医生响应参数类
 */
@Data
@ApiModel(value = "DoctorInfoByUserIdResBody响应参数类", description = "根据患者编号查询我的医生响应参数类")
public class DoctorInfoByUserIdResBody implements Serializable {

    private static final long serialVersionUID = -1856382437315938362L;

    @ApiModelProperty(value = "医生信息编号", example = "医生信息编号")
    private Long id;
    @ApiModelProperty(value = "医生编号", example = "医生编号")
    private Long doctorId;
    @ApiModelProperty(value = "所属医院", example = "所属医院")
    private String hospital;
    @ApiModelProperty(value = "所属科", example = "所属科")
    private String section;
    @ApiModelProperty(value = "医生级别", example = "医生级别")
    private String level;
    @ApiModelProperty(value = "擅长", example = "擅长")
    private String forte;
    @ApiModelProperty(value = "个人介绍", example = "个人介绍")
    private String introduce;
    @ApiModelProperty(value = "咨询费", example = "咨询费")
    private BigDecimal consultPrice;
    @ApiModelProperty(value = "星级", example = "星级")
    private Integer starClass;
    @ApiModelProperty(value = "就诊人数", example = "就诊人数")
    private Integer patientNum;
    @ApiModelProperty(value = "好评率", example = "好评率")
    private String praiseRate;
//    @ApiModelProperty(value = "创建时间", example = "创建时间")
//    private Long createTime;
//    @ApiModelProperty(value = "更新时间", example = "更新时间")
//    private Long updateTime;
    @ApiModelProperty(value = "医生名称", example = "医生名称")
    private String doctorName;
    @ApiModelProperty(value = "头像", example = "头像")
    private String headPic;
//    @ApiModelProperty(value = "标签", example = "标签")
//    private List<String> tag;

}