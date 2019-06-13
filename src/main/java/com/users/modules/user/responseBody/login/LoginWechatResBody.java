package com.users.modules.user.responseBody.login;

import com.users.modules.user.entity.CrUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信登录授权响应参数类
 */
@Data
@ApiModel(value = "LoginWechatResBody响应参数类", description = "微信登录授权响应参数类")
public class LoginWechatResBody implements Serializable {

    private static final long serialVersionUID = 3893940647722827937L;

    @ApiModelProperty(value = "用户登录token", example = "用户登录token")
    private String token;

    @ApiModelProperty(value = "是否绑定手机号（0否、1是）", example = "是否绑定手机号（0否、1是）")
    private Integer bindMobile;

    @ApiModelProperty(value = "是否绑定微信（0否、1是）", example = "是否绑定微信（0否、1是）")
    private Integer bindWechat;

    @ApiModelProperty(value = "积分", example = "积分")
    private Integer totalIntegral;

    @ApiModelProperty(value = "收藏", example = "收藏")
    private Integer totalCollect;

    @ApiModelProperty(value = "关注", example = "关注")
    private Integer totalFollow;

    @ApiModelProperty(value = "用户个人信息详情")
    private CrUser user;

    @ApiModelProperty(value = "是否第一次登录微信（0否、1是）", example = "是否第一次登录微信（0否、1是）")
    private Integer firstLoginWechat;

}