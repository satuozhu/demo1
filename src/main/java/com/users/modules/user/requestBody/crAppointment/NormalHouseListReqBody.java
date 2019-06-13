package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 查询正常运转的诊室信息（医生-诊室-时间段）请求参数类
 */
@ApiModel(value = "NormalHouseListReqBody请求参数类", description = "查询正常运转的诊室信息（医生-诊室-时间段）请求参数类")
public class NormalHouseListReqBody extends Page implements Serializable {

    private static final long serialVersionUID = 3170113161520931274L;

    @ApiModelProperty(value = "市级区域编号,查找该市的所有诊室", allowableValues = "440300")
    private Long areaId;

    @ApiModelProperty(value = "诊室名称", allowableValues = "转圈圈医疗中心")
    private String houseName;

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

    @Override
    public String toString() {
        return "NormalHouseListReqBody{" +
                "areaId=" + areaId +
                ", houseName=" + houseName +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}