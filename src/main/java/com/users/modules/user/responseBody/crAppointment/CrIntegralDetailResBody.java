package com.users.modules.user.responseBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询最近30天的积分明细响应参数类
 */
@Data
@ApiModel(value = "CrIntegralDetailResBody响应参数类", description = "分页查询最近30天的积分明细响应参数类")
public class CrIntegralDetailResBody implements Serializable {

    private static final long serialVersionUID = 953342549578629279L;

    @ApiModelProperty(value = "积分主键ID", example = "积分主键ID")
    private Long id;
    @ApiModelProperty(value = "用户编号", example = "用户编号")
    private Long userId;
    @ApiModelProperty(value = "字典类型编号", example = "字典类型编号")
    private String dictType;
    @ApiModelProperty(value = "积分来源(其他、签到、分享、留言、评论、被点赞、预约、认证)，来自字典表cr_sys_dict", example = "积分来源(其他、签到、分享、留言、评论、被点赞、预约、认证)，来自字典表cr_sys_dict")
    private String dictName;
    @ApiModelProperty(value = "积分数量", example = "积分数量")
    private Integer score;
    @ApiModelProperty(value = "创建时间", example = "创建时间")
    private Long createTime;
//    @ApiModelProperty(value = "积分详情", example = "积分详情")
//    private String detail;

}