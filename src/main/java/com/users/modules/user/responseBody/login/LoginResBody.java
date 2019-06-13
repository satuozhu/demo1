package com.users.modules.user.responseBody.login;

import com.users.modules.user.entity.CrUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 密码登录(账号、手机号、邮箱)响应参数类
 */
@Data
@ApiModel(value = "LoginResBody响应参数类", description = "密码登录(账号、手机号、邮箱)响应参数类")
public class LoginResBody implements Serializable {

    private static final long serialVersionUID = -1365521538868671672L;

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

}