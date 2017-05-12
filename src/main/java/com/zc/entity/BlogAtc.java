package com.zc.entity;

/**
 * Created by chock on 2017/4/3.
 */
public class BlogAtc {

    private int atcId;
    private int userId;
    private String atcTitle;
    private String atcUrl;
    private String atcType;
    private String atcTime;
    private Integer atcView;
    private String atcFlag;
    private String atcBackup;



    private User user;

    public int getAtcId() {
        return atcId;
    }

    public void setAtcId(int atcId) {
        this.atcId = atcId;
    }

    public String getAtcTitle() {
        return atcTitle;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAtcTitle(String atcTitle) {
        this.atcTitle = atcTitle;
    }

    public String getAtcUrl() {
        return atcUrl;
    }

    public void setAtcUrl(String atcUrl) {
        this.atcUrl = atcUrl;
    }

    public String getAtcType() {
        return atcType;
    }

    public void setAtcType(String atcType) {
        this.atcType = atcType;
    }

    public String getAtcTime() {
        return atcTime;
    }

    public void setAtcTime(String atcTime) {
        this.atcTime = atcTime;
    }

    public Integer getAtcView() {
        return atcView;
    }

    public void setAtcView(Integer atcView) {
        this.atcView = atcView;
    }

    public String getAtcFlag() {
        return atcFlag;
    }

    public void setAtcFlag(String atcFlag) {
        this.atcFlag = atcFlag;
    }

    public String getAtcBackup() {
        return atcBackup;
    }

    public void setAtcBackup(String atcBackup) {
        this.atcBackup = atcBackup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BlogAtc{" +
                "atcId=" + atcId +
                ", userId=" + userId +
                ", atcTitle='" + atcTitle + '\'' +
                ", atcUrl='" + atcUrl + '\'' +
                ", atcType='" + atcType + '\'' +
                ", atcTime='" + atcTime + '\'' +
                ", atcView=" + atcView +
                ", atcFlag='" + atcFlag + '\'' +
                ", atcBackup='" + atcBackup + '\'' +
                ", user=" + user +
                '}';
    }
}
