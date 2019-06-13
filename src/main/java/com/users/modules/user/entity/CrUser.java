package com.users.modules.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * cr_user用户表实体类
 * @author
 */
@ApiModel(value = "CrUser实体类", description = "用户表实体类")
public class CrUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 2564765805975250784L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "email邮箱")
    private String email;

    @ApiModelProperty(value = "密码密文")
    private String pwd;

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

    @ApiModelProperty(value = "用户类型(0:普通用户,1:医生)")
    private Integer flag;

    @ApiModelProperty(value = "平台类型（1移动端、2PC端）")
    private Integer platformType;

    @ApiModelProperty(value = "登录类型（1账号、2微信、3手机、4邮箱）")
    private Integer loginType;

    @ApiModelProperty(value = "认证类型(0未认证、1已认证)")
    private Integer authType;

    @ApiModelProperty(value = "头像地址")
    private String headPic;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
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
        this.tags = tags == null ? null : tags.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
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
        this.headPic = headPic == null ? null : headPic.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        com.users.modules.user.entity.CrUser other = (com.users.modules.user.entity.CrUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
                && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
                && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
                && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
                && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
                && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
                && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
                && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
                && (this.getPlatformType() == null ? other.getPlatformType() == null : this.getPlatformType().equals(other.getPlatformType()))
                && (this.getLoginType() == null ? other.getLoginType() == null : this.getLoginType().equals(other.getLoginType()))
                && (this.getAuthType() == null ? other.getAuthType() == null : this.getAuthType().equals(other.getAuthType()))
                && (this.getHeadPic() == null ? other.getHeadPic() == null : this.getHeadPic().equals(other.getHeadPic()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getPlatformType() == null) ? 0 : getPlatformType().hashCode());
        result = prime * result + ((getLoginType() == null) ? 0 : getLoginType().hashCode());
        result = prime * result + ((getAuthType() == null) ? 0 : getAuthType().hashCode());
        result = prime * result + ((getHeadPic() == null) ? 0 : getHeadPic().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", account=").append(account);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", pwd=").append(pwd);
        sb.append(", salt=").append(salt);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", tags=").append(tags);
        sb.append(", openid=").append(openid);
        sb.append(", flag=").append(flag);
        sb.append(", platformType=").append(platformType);
        sb.append(", loginType=").append(loginType);
        sb.append(", authType=").append(authType);
        sb.append(", headPic=").append(headPic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    private Collection<? extends GrantedAuthority> auths;

    public void setAuths(Collection<? extends GrantedAuthority> auths) {
        this.auths = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.auths;
    }

    @Override
    public String getPassword() {
        return this.getPwd();
    }

    @Override
    public String getUsername() {
//        return this.getUserName();
        return this.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}