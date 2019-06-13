package com.users.modules.user.responseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/31
 * Time: 10:34
 * Description: No Description
 */
@ApiModel(value = "收藏--网文相应参数类", description = "收藏--网文相应参数类")
public class CrArticleRespBody implements Serializable {

    private static final long serialVersionUID = 6804578135411393355L;

    @ApiModelProperty(value = "网文表主键ID", example = "网文表主键ID")
    private Long id;

    @ApiModelProperty(value = "网文作者ID", example = "网文作者ID")
    private String articleAuthorId;
    @ApiModelProperty("帖子访问路径")
    private String articlePermalink;
    @ApiModelProperty(value = "网文首图地址", example = "网文首图地址")
    private String articleImg1Url;

    @ApiModelProperty(value = "网文标题", example = "网文标题")
    private String articleTitle;

    @ApiModelProperty(value = "网文阅读数", example = "网文阅读数")
    private Integer articleViewCount;

    @ApiModelProperty(value = "网文点赞数", example = "网文点赞数")
    private Integer articleGoodCnt;

    @ApiModelProperty(value = "网文评论数", example = "网文评论数")
    private Integer articleCommentCount;

    @ApiModelProperty(value = "网文状态", example = "网文状态")
    private Integer articleStatus;

    @ApiModelProperty(value = "网文收藏", example = "网文收藏")
    private Integer articleCollectcnt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(String articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public String getArticleImg1Url() {
        return articleImg1Url;
    }

    public void setArticleImg1Url(String articleImg1Url) {
        this.articleImg1Url = articleImg1Url;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleGoodCnt() {
        return articleGoodCnt;
    }

    public void setArticleGoodCnt(Integer articleGoodCnt) {
        this.articleGoodCnt = articleGoodCnt;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleCollectcnt() {
        return articleCollectcnt;
    }

    public void setArticleCollectcnt(Integer articleCollectcnt) {
        this.articleCollectcnt = articleCollectcnt;
    }

    public String getArticlePermalink() {
        return articlePermalink;
    }

    public void setArticlePermalink(String articlePermalink) {
        this.articlePermalink = articlePermalink;
    }

    @Override
    public String toString() {
        return "CrArticleRespBody{" +
                "id=" + id +
                ", articleAuthorId='" + articleAuthorId + '\'' +
                ", articleImg1Url='" + articleImg1Url + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleViewCount=" + articleViewCount +
                ", articleGoodCnt=" + articleGoodCnt +
                ", articleCommentCount=" + articleCommentCount +
                ", articleStatus=" + articleStatus +
                ", articleCollectcnt=" + articleCollectcnt +
                ", articlePermalink=" + articlePermalink +
                '}';
    }
}
