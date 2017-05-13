package com.zc.entity;

/**
 * Created by chock on 2017/4/28.
 */
public class Message {
    private int msgId;
    private int atcId;
    private int userId;
    private String msgTime;
    private String msgContent;
    private Integer msgOwn;
    private String msgType;
    private String msgReturnFlag;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getAtcId() {
        return atcId;
    }

    public void setAtcId(int atcId) {
        this.atcId = atcId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getMsgOwn() {
        return msgOwn;
    }

    public void setMsgOwn(Integer msgOwn) {
        this.msgOwn = msgOwn;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgReturnFlag() {
        return msgReturnFlag;
    }

    public void setMsgReturnFlag(String msgReturnFlag) {
        this.msgReturnFlag = msgReturnFlag;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId=" + msgId +
                ", atcId='" + atcId + '\'' +
                ", userId='" + userId + '\'' +
                ", msgTime='" + msgTime + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgOwn=" + msgOwn +
                ", msgType='" + msgType + '\'' +
                ", msgReturnFlag='" + msgReturnFlag + '\'' +
                '}';
    }
}
