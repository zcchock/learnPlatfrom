package com.zc.api;

import java.io.Serializable;

/**
 * Created by chock on 2017/4/3.
 */
public class DataRequest implements Serializable {

    private String clientIp;
    private Object data;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
