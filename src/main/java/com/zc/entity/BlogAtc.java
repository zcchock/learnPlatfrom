package com.zc.entity;

/**
 * Created by chock on 2017/4/3.
 */
public class BlogAtc {

    private int atcId;
    private String atcTitle;
    private String atcUrl;
    private String atcType;
    private String atcTime;
    private Integer atcView;
    private String atcFlag;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BlogAtc[" +
                "atcId=" + atcId +
                ", atcTitle='" + atcTitle + '\'' +
                ", atcUrl='" + atcUrl + '\'' +
                ", atcType='" + atcType + '\'' +
                ", atcTime='" + atcTime + '\'' +
                ", atcView=" + atcView +
                ", atcFlag='" + atcFlag + '\'' +
                ", user=" + user +
                ']';
    }
}