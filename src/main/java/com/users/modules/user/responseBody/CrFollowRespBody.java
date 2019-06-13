package com.users.modules.user.responseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/30
 * Time: 17:06
 * Description: No Description
 */
/**
 * 关注--医生信息响应参数类
 */
@ApiModel(value = "关注--医生信息响应参数类", description = "关注--医生信息响应参数类")
public class CrFollowRespBody implements Serializable {

    private static final long serialVersionUID = -5547810982500119210L;

    @ApiModelProperty(value = "关注表主键ID", example = "关注表主键ID")
    private Long id;

    @ApiModelProperty(value = "关注者ID", example = "关注者ID")
    private Long followerId;

    @ApiModelProperty(value = "关注者实体ID", example = "关注者实体ID")
    private Long followingId;

    @ApiModelProperty(value = "被关注者名称", example = "被关注者名称")
    private String userName;

    @ApiModelProperty(value = "医生id", example = "医生id")
    private Long doctorId;

    @ApiModelProperty(value = "医生级别", example = "医生级别")
    private String level;

    @ApiModelProperty(value = "医生个人介绍", example = "医生个人介绍")
    private String introduce;

    private List<CrArticleRespBody> crArticleRespBody;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<CrArticleRespBody> getCrArticleRespBody() {
        return crArticleRespBody;
    }

    public void setCrArticleRespBody(List<CrArticleRespBody> crArticleRespBody) {
        this.crArticleRespBody = crArticleRespBody;
    }

    @Override
    public String toString() {
        return "CrFollowRespBody{" +
                "id=" + id +
                ", followerId=" + followerId +
                ", followingId=" + followingId +
                ", userName='" + userName + '\'' +
                ", doctorId=" + doctorId +
                ", level='" + level + '\'' +
                ", introduce='" + introduce + '\'' +
                ", crArticleRespBody=" + crArticleRespBody +
                '}';
    }
}
