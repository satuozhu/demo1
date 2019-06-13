package com.common.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * cr_sys_message消息表
 * @author 
 */
@ApiModel(value = "CrSysMessage实体类", description = "消息表实体类")
public class CrSysMessage implements Serializable {

    private static final long serialVersionUID = 487325743470978931L;

    @ApiModelProperty(value = "主键，消息ID", example = "主键，消息ID")
    private Long id;

    @ApiModelProperty(value = "消息类型（0系统通知、1留言、2评论、3预约）", example = "消息类型（0系统通知、1留言、2评论、3预约）")
    private Integer msgType;

    @ApiModelProperty(value = "标题", example = "标题")
    private String msgTitle;

    @ApiModelProperty(value = "诊室时间段主键ID", example = "诊室时间段主键ID")
    private String msgContent;

    @ApiModelProperty(value = "消息状态(0未读、1已读)", example = "消息状态(0未读、1已读)")
    private Boolean msgStatus;

    @ApiModelProperty(value = "用户编号(-1所有用户)", example = "用户编号(-1所有用户)")
    private Long userId;

    @ApiModelProperty(value = "通知实体编号", example = "通知实体编号")
    private Long beanId;

    @ApiModelProperty(value = "创建日期", example = "创建日期")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", example = "更新时间")
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Boolean getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Boolean msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBeanId() {
        return beanId;
    }

    public void setBeanId(Long beanId) {
        this.beanId = beanId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CrSysMessage other = (CrSysMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
                && (this.getMsgTitle() == null ? other.getMsgTitle() == null : this.getMsgTitle().equals(other.getMsgTitle()))
                && (this.getMsgContent() == null ? other.getMsgContent() == null : this.getMsgContent().equals(other.getMsgContent()))
                && (this.getMsgStatus() == null ? other.getMsgStatus() == null : this.getMsgStatus().equals(other.getMsgStatus()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getBeanId() == null ? other.getBeanId() == null : this.getBeanId().equals(other.getBeanId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getMsgTitle() == null) ? 0 : getMsgTitle().hashCode());
        result = prime * result + ((getMsgContent() == null) ? 0 : getMsgContent().hashCode());
        result = prime * result + ((getMsgStatus() == null) ? 0 : getMsgStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBeanId() == null) ? 0 : getBeanId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", msgType=").append(msgType);
        sb.append(", msgTitle=").append(msgTitle);
        sb.append(", msgContent=").append(msgContent);
        sb.append(", msgStatus=").append(msgStatus);
        sb.append(", userId=").append(userId);
        sb.append(", beanId=").append(beanId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}