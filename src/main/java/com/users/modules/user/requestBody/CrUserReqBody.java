package com.users.modules.user.requestBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * 用户请求参数类
 */
@ApiModel(value = "CrUserReqBody请求参数类", description = "用户请求参数类")
public class CrUserReqBody implements Serializable {

    private static final long serialVersionUID = 3229128707770870393L;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "类型：1手机注册2手机重置密码3手机验证码登录4手机验证码绑定；11邮箱注册12邮箱重置密码13邮箱验证码登录14邮箱验证码绑定")
    private Integer type;

    @ApiModelProperty(value = "密码密文")
    private String pwd;

    @ApiModelProperty(value = "重复密码")
    private String repwd;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "性别(0:女 1:男)")
    private String sex;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "身高cm")
    private String height;

    @ApiModelProperty(value = "体重kg")
    private String weight;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "微信用户对应用的唯一标识")
    private String openid;

    @ApiModelProperty(value = "头像地址")
    private String headPic;

    @ApiModelProperty(value = "0:普通用户,1:医生")
    private Integer flag;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRepwd() {
        return repwd;
    }

    public void setRepwd(String repwd) {
        this.repwd = repwd;
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

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CrUserReqBody{" +
                "userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", pwd='" + pwd + '\'' +
                ", repwd='" + repwd + '\'' +
                ", salt='" + salt + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tags='" + tags + '\'' +
                ", openid='" + openid + '\'' +
                ", headPic='" + headPic + '\'' +
                ", flag=" + flag +
                '}';
    }

}