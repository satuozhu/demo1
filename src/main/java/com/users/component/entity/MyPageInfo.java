package com.users.component.entity;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

//import com.github.pagehelper.PageInfo;

/**
 * 重写com.github.pagehelper.PageInfo分页类（原来的有bug）
 */
@ApiModel(value = "MyPageInfo<T>响应参数类", description = "分页对象响应参数类")
public class MyPageInfo<T> implements Serializable {

    private static final long serialVersionUID = -40085015940860651L;

    @ApiModelProperty(value = "当前页", example = "当前页", required = true)
    private int pageNum;
    @ApiModelProperty(value = "每页条数", example = "每页条数", required = true)
    private int pageSize;
    @ApiModelProperty(value = "每页条数", example = "每页条数", required = true)
    private int size;
    @ApiModelProperty(value = "开始行数", example = "开始行数", required = true)
    private int startRow;
    @ApiModelProperty(value = "结束行数", example = "结束行数", required = true)
    private int endRow;
    @ApiModelProperty(value = "总条数", example = "总条数", required = true)
    private long total;
    @ApiModelProperty(value = "总页数", example = "总页数", required = true)
    private int pages;
    //@ApiModelProperty(value = "内容", example = "内容", required = true)//会把list下的提示覆盖
    @ApiModelProperty(value = "内容", required = true)
    private List<T> list;
    @ApiModelProperty(value = "上一页", example = "上一页", required = true)
    private int prePage;
    @ApiModelProperty(value = "下一页", example = "下一页", required = true)
    private int nextPage;
    @ApiModelProperty(value = "是否是首页", example = "是否是首页", required = true)
    private boolean isFirstPage;
    @ApiModelProperty(value = "是否是尾页", example = "是否是尾页", required = true)
    private boolean isLastPage;
    @ApiModelProperty(value = "是否有上一页", example = "是否有上一页", required = true)
    private boolean hasPreviousPage;
    @ApiModelProperty(value = "是否有下一页", example = "是否有下一页", required = true)
    private boolean hasNextPage;
    @ApiModelProperty(value = "导航页码数", example = "导航页码数", required = true)
    private int navigatePages;
    @ApiModelProperty(value = "所有导航页号", example = "所有导航页号", required = true)
    private int[] navigatepageNums;
    @ApiModelProperty(value = "导航页首页", example = "导航页首页", required = true)
    private int navigateFirstPage;
    @ApiModelProperty(value = "导航页尾页", example = "导航页尾页", required = true)
    private int navigateLastPage;
    @ApiModelProperty(value = "首页", example = "首页", required = true)
    private int firstPage;
    @ApiModelProperty(value = "尾页", example = "尾页", required = true)
    private int lastPage;

    public MyPageInfo() {
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
    }

    public MyPageInfo(List<T> list) {
        this(list, 8);//【bug-01】导航页码数，此处默认navigatePages=8，见【bug-02】
    }

    public MyPageInfo(List<T> list, int navigatePages) {
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            this.total = page.getTotal();
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = this.pageSize > 0 ? 1 : 0;
            this.list = list;
            this.size = list.size();
            this.total = (long) list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }

        if (list instanceof Collection) {
            //【bug-02】
            if (this.pages == 0) {
                this.navigatePages = navigatePages;
            } else {
                this.navigatePages = this.pages;
            }
            this.calcNavigatepageNums();
            this.calcPage();
            this.judgePageBoudary();
        }

    }

    private void calcNavigatepageNums() {
        int i;
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];

            for (i = 0; i < this.pages; ++i) {
                this.navigatepageNums[i] = i + 1;
            }
        } else {
            this.navigatepageNums = new int[this.navigatePages];
            i = this.pageNum - this.navigatePages / 2;
            int endNum = this.pageNum + this.navigatePages / 2;
//            int i;//【bug-03】
            if (i < 1) {
                i = 1;

                for (i = 0; i < this.navigatePages; ++i) {
                    this.navigatepageNums[i] = i++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;

                for (i = this.navigatePages - 1; i >= 0; --i) {
                    this.navigatepageNums[i] = endNum--;
                }
            } else {
                for (i = 0; i < this.navigatePages; ++i) {
                    this.navigatepageNums[i] = i++;
                }
            }
        }

    }

    private void calcPage() {
        if (this.navigatepageNums != null && this.navigatepageNums.length > 0) {
            this.navigateFirstPage = this.navigatepageNums[0];
            this.navigateLastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }

            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }

    }

    private void judgePageBoudary() {
        this.isFirstPage = this.pageNum == 1;
        this.isLastPage = this.pageNum == this.pages || this.pages == 0;
        this.hasPreviousPage = this.pageNum > 1;
        this.hasNextPage = this.pageNum < this.pages;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public int getFirstPage() {
        return this.navigateFirstPage;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setFirstPage(int firstPage) {
        this.navigateFirstPage = firstPage;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public int getLastPage() {
        return this.navigateLastPage;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setLastPage(int lastPage) {
        this.navigateLastPage = lastPage;
    }

    public boolean isIsFirstPage() {
        return this.isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return this.isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return this.navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepageNums() {
        return this.navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNavigateFirstPage() {
        return this.navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return this.navigateLastPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("MyPageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", startRow=").append(this.startRow);
        sb.append(", endRow=").append(this.endRow);
        sb.append(", total=").append(this.total);
        sb.append(", pages=").append(this.pages);
        sb.append(", list=").append(this.list);
        sb.append(", prePage=").append(this.prePage);
        sb.append(", nextPage=").append(this.nextPage);
        sb.append(", isFirstPage=").append(this.isFirstPage);
        sb.append(", isLastPage=").append(this.isLastPage);
        sb.append(", hasPreviousPage=").append(this.hasPreviousPage);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", navigatePages=").append(this.navigatePages);
        sb.append(", navigateFirstPage=").append(this.navigateFirstPage);
        sb.append(", navigateLastPage=").append(this.navigateLastPage);
        sb.append(", navigatepageNums=");
        if (this.navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');

            for (int i = 0; i < this.navigatepageNums.length; ++i) {
                sb.append(i == 0 ? "" : ", ").append(this.navigatepageNums[i]);
            }

            sb.append(']');
        }

        sb.append('}');
        return sb.toString();
    }

}