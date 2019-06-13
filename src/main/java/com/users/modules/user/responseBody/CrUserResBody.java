package com.users.modules.user.responseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户个人信息响应参数类
 */
@ApiModel(value = "CrUserResBody响应参数类", description = "用户个人信息响应参数类")
public class CrUserResBody implements Serializable {

    private static final long serialVersionUID = -2977649283594819044L;

    @ApiModelProperty(value = "用户主键ID", example = "用户主键ID")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户账号", example = "用户账号")
    private String account;

    @ApiModelProperty(value = "手机号", example = "手机号")
    private String phone;

    @ApiModelProperty(value = "email邮箱", example = "email邮箱")
    private String email;

    @ApiModelProperty(value = "密码密文", example = "密码密文")
    private String pwd;

    @ApiModelProperty(value = "盐值", example = "盐值")
    private String salt;

    @ApiModelProperty(value = "性别(0:女 1:男)", example = "性别(0:女 1:男)")
    private String sex;

    @ApiModelProperty(value = "生日", example = "生日")
    private String birthday;

    @ApiModelProperty(value = "身高cm", example = "身高cm")
    private String height;

    @ApiModelProperty(value = "体重kg", example = "体重kg")
    private String weight;

    @ApiModelProperty(value = "创建时间", example = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", example = "更新时间")
    private Long updateTime;

    @ApiModelProperty(value = "标签", example = "标签")
    private String tags;

    @ApiModelProperty(value = "微信用户对应用的唯一标识", example = "微信用户对应用的唯一标识")
    private String openid;

    @ApiModelProperty(value = "用户类型(0:普通用户,1:医生)", example = "用户类型(0:普通用户,1:医生)")
    private Integer flag;

    @ApiModelProperty(value = "平台类型（1移动端、2PC端）", example = "平台类型（1移动端、2PC端）")
    private Integer platformType;

    @ApiModelProperty(value = "登录类型（1账号、2微信、3手机、4邮箱）", example = "登录类型（1账号、2微信、3手机、4邮箱）")
    private Integer loginType;

    @ApiModelProperty(value = "认证类型(0未认证、1已认证)", example = "认证类型(0未认证、1已认证)")
    private Integer authType;

    @ApiModelProperty(value = "头像地址", example = "头像地址")
    private String headPic;

    @ApiModelProperty(value = "是否绑定手机号（0否、1是）", example = "是否绑定手机号（0否、1是）")
    private Integer bindMobile;

    @ApiModelProperty(value = "是否绑定微信（0否、1是）", example = "是否绑定微信（0否、1是）")
    private Integer bindWechat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(Integer bindMobile) {
        this.bindMobile = bindMobile;
    }

    public Integer getBindWechat() {
        return bindWechat;
    }

    public void setBindWechat(Integer bindWechat) {
        this.bindWechat = bindWechat;
    }

    @Override
    public String toString() {
        return "CrUserResBody{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tags='" + tags + '\'' +
                ", openid='" + openid + '\'' +
                ", flag=" + flag +
                ", platformType=" + platformType +
                ", loginType=" + loginType +
                ", authType=" + authType +
                ", headPic='" + headPic + '\'' +
                ", bindMobile=" + bindMobile +
                ", bindWechat=" + bindWechat +
                '}';
    }

}
