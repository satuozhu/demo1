package com.common.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * cr_question提问表
 * @author 
 */
@ApiModel(value = "CrQuestion实体类", description = "提问表实体类")
public class CrQuestion implements Serializable {

    @ApiModelProperty(value = "主键,问题ID", example = "主键,问题ID")
    private Long id;

    @ApiModelProperty(value = "问题类型（0系统、1医生）", example = "问题类型（0系统、1医生）")
    private Integer type;

    @ApiModelProperty(value = "提问内容", example = "提问内容")
    private String content;

    @ApiModelProperty(value = "用户编号", example = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "回复者编号", example = "回复者编号（0系统）")
    private Long beanId;

    @ApiModelProperty(value = "回复内容", example = "回复内容")
    private String replyContent;

    @ApiModelProperty(value = "回复状态(0未回复、1已回复)", example = "回复状态(0未回复、1已回复)")
    private Boolean status;

    @ApiModelProperty(value = "创建日期", example = "创建日期")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", example = "更新时间")
    private Long updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        CrQuestion other = (CrQuestion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBeanId() == null ? other.getBeanId() == null : this.getBeanId().equals(other.getBeanId()))
            && (this.getReplyContent() == null ? other.getReplyContent() == null : this.getReplyContent().equals(other.getReplyContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBeanId() == null) ? 0 : getBeanId().hashCode());
        result = prime * result + ((getReplyContent() == null) ? 0 : getReplyContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", userId=").append(userId);
        sb.append(", beanId=").append(beanId);
        sb.append(", replyContent=").append(replyContent);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}