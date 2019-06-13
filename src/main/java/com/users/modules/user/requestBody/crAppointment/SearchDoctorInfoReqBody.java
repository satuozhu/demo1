package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 搜索医生列表请求参数类
 */
@ApiModel(value = "SearchDoctorInfoReqBody请求参数类", description = "搜索医生列表请求参数类")
public class SearchDoctorInfoReqBody extends Page implements Serializable {

    private static final long serialVersionUID = 5588647214012793764L;

//    @ApiModelProperty(value = "小屋编号", allowableValues = "1")
//    private Long houseId;

    @ApiModelProperty(value = "医生名称", allowableValues = "医生")
    private String doctorName;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "SearchDoctorInfoReqBody{" +
                "doctorName=" + doctorName +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                "}";
    }
}