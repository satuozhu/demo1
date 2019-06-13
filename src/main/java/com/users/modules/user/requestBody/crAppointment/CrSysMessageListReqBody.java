package com.users.modules.user.requestBody.crAppointment;

import com.users.component.entity.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 查询推送的消息请求参数类
 */
@ApiModel(value = "CrSysMessageListReqBody请求参数类", description = "查询推送的消息请求参数类")
public class CrSysMessageListReqBody extends Page implements Serializable {

    private static final long serialVersionUID = -1125683880081860848L;

    @ApiModelProperty(value = "用户编号", required = true, allowableValues = "4")
    private Long userId;

//    @ApiModelProperty(value = "消息类型（0系统通知、1留言、2评论、3预约）", allowableValues = "0")
//    private Integer msgType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CrSysMessageListReqBody{" +
                "userId=" + userId +
                ", pageNum=" + this.getPageNum() +
                ", pageSize=" + this.getPageSize() +
                '}';
    }

}