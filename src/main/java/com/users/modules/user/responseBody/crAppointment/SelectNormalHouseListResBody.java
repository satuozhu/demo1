package com.users.modules.user.responseBody.crAppointment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询正常运转的诊室（医生-诊室-时间段）响应参数类
 */
@Data
@ApiModel(value = "SelectNormalHouseListResBody响应参数类", description = "查询正常运转的诊室（医生-诊室-时间段）响应参数类")
public class SelectNormalHouseListResBody implements Serializable {

    private static final long serialVersionUID = 2362563029368631706L;

    @ApiModelProperty(value = "诊室编号", example = "诊室编号")
    private Long houseId;
    @ApiModelProperty(value = "公网ip", example = "公网ip")
    private String pubServerIp;
    @ApiModelProperty(value = "局域网ip", example = "局域网ip")
    private String lanServerIp;
    @ApiModelProperty(value = "区域编号", example = "区域编号")
    private Long areaId;
    @ApiModelProperty(value = "区域深度路径值，偏于递归查询", example = "区域深度路径值，偏于递归查询")
    private String areaPath;
    @ApiModelProperty(value = "服务mac", example = "服务mac")
    private String serverMac;
    @ApiModelProperty(value = "诊室名称", example = "诊室名称")
    private String name;
    @ApiModelProperty(value = "地址", example = "地址")
    private String location;
    @ApiModelProperty(value = "经度", example = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度", example = "纬度")
    private BigDecimal latitude;
    @ApiModelProperty(value = "人数", example = "人数")
    private Integer number;
    @ApiModelProperty(value = "状态", example = "状态")
    private Integer status;

}