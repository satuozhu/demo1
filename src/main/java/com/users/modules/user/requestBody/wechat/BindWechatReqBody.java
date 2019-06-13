package com.users.modules.user.requestBody.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户绑定、更换微信请求参数类
 */
@ApiModel(value = "BindWechatReqBody请求参数类", description = "用户绑定、更换微信请求参数类")
public class BindWechatReqBody implements Serializable {

    private static final long serialVersionUID = -148152148238823581L;

    @ApiModelProperty(value = "用户ID", required = true, dataType = "Long")
    private Long userId;

    @ApiModelProperty(value = "微信openId", required = true, allowableValues = "ovx821kaTbdYMFbYl5BqPrtHObm4")
    private String openId;

    @ApiModelProperty(value = "微信昵称", required = true, allowableValues = "夕亦残潋若丶瑾残醉瞳风℡")
    private String nickname;

    @ApiModelProperty(value = "微信头像", required = true, allowableValues = "http://thirdwx.qlogo.cn/mmopen/vi_32/780duUEiavmOQ01zIJ2AAecsNC2HFANr8Mv79IHTWlR7x5KKWlul2zJDag95RNicduQNAh9IpSrffVkrR9OARib1Q/132")
    private String headImgUrl;

    @ApiModelProperty(value = "微信unionid", required = true, allowableValues = "o9Mft53WIcPusckSYCOuUtJtb3D4")
    private String unionid;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "BindWechatReqBody{" +
                "userId=" + userId +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }

}