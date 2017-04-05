package com.zc.entity;

/**
 * Created by chock on 2017/4/3.
 */
public class User {

    private int userId;
    private String account;
    private String name;
    private String password;
    private String roleId;
    private String sex;
    private String email;
    private String phone;
    private String lastLoginTime;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User [" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ']';
    }
}
