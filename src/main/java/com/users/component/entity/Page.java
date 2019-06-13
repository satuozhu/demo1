package com.users.component.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页信息请求参数类
 */
@ApiModel(value = "Page请求参数类", description = "分页信息请求参数类")
public class Page implements Serializable {

    private static final long serialVersionUID = -8139576011371926928L;

    @ApiModelProperty(value = "当前页（默认1）", required = true, allowableValues = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "一页几行（默认5）", required = true, allowableValues = "5")
    private Integer pageSize;

    public Page() {
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Page)) return false;
        final Page other = (Page) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$pageNum = this.getPageNum();
        final Object other$pageNum = other.getPageNum();
        if (this$pageNum == null ? other$pageNum != null : !this$pageNum.equals(other$pageNum)) return false;
        final Object this$pageSize = this.getPageSize();
        final Object other$pageSize = other.getPageSize();
        if (this$pageSize == null ? other$pageSize != null : !this$pageSize.equals(other$pageSize)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Page;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $pageNum = this.getPageNum();
        result = result * PRIME + ($pageNum == null ? 43 : $pageNum.hashCode());
        final Object $pageSize = this.getPageSize();
        result = result * PRIME + ($pageSize == null ? 43 : $pageSize.hashCode());
        return result;
    }

    public String toString() {
        return "Page(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ")";
    }

}