package com.users.modules.user.requestBody.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 微信登录授权请求参数类
 */
@ApiModel(value = "LoginWechatAuthReqBody请求参数类", description = "微信登录授权请求参数类")
public class LoginWechatAuthReqBody implements Serializable {

    private static final long serialVersionUID = 6481082539336733995L;

    @ApiModelProperty(value = "微信openId", required = true, allowableValues = "ovx821kaTbdYMFbYl5BqPrtHObm4")
    private String openId;

    @ApiModelProperty(value = "微信昵称", required = true, allowableValues = "夕亦残潋若丶瑾残醉瞳风℡")
    private String nickname;

    @ApiModelProperty(value = "微信头像", required = true, allowableValues = "http://thirdwx.qlogo.cn/mmopen/vi_32/780duUEiavmOQ01zIJ2AAecsNC2HFANr8Mv79IHTWlR7x5KKWlul2zJDag95RNicduQNAh9IpSrffVkrR9OARib1Q/132")
    private String headImgUrl;

    @ApiModelProperty(value = "微信unionid", required = true, allowableValues = "o9Mft53WIcPusckSYCOuUtJtb3D4")
    private String unionid;

    @ApiModelProperty(value = "平台类型（1移动端、2PC端）", required = true)
    private Integer platformType;

    @ApiModelProperty(value = "登录类型（1账号、2微信、3手机、4邮箱）", required = true)
    private Integer loginType;

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

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "LoginWechatAuthReqBody{" +
                "openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", platformType=" + platformType +
                ", loginType=" + loginType +
                '}';
    }

}