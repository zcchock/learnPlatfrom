package com.zc.shiro.model;

import java.io.Serializable;

/**
 * 用于验证时记录用户信息的model
 */
@SuppressWarnings("serial")
public class UserCertificate implements Serializable {
    private int userId; //编号
    private String account;//账号（唯一）
    private String name; //用户名
    private String password; //密码
    private String sex; //性别
    private String email; //邮箱
    private String phone; //号码
    private String salt; //加密密码的盐
    private String roleId; //拥有的角色列表
    private String lastLoginTime; //最后登录时间

    private Boolean locked = Boolean.FALSE;

    public UserCertificate() {
    }

    public UserCertificate(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCertificate userCertificate = (UserCertificate) o;

        if (userId != null ? !userId.equals(userCertificate.userId) : userCertificate.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }*/

    @Override
    public String toString() {
        return "UserCertificate{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salt='" + salt + '\'' +
                ", roleId='" + roleId + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", locked=" + locked +
                '}';
    }
}
