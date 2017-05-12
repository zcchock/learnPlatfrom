package com.zc.api;

/**
 * Created by chock on 2017/5/12.
 */
public class loginData {
    private String status = "error";    // status: success or error
    private String code = "200";
    private String message;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public loginData(String status, String code, String message, String data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
