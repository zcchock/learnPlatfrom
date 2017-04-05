package com.zc.enums;

/**
 * Created by chock on 2017/4/3.
 */
public enum  BlogAtcFlagEnum {
    SUCCESS(1, "预约成功"),
    NO_NUMBER(0, "库存不足"),
    REPEAT_APPOINT(-1, "重复预约"),
    INNER_ERROR(-2, "系统异常");

    private int state;
    private String stateInfo;

    private BlogAtcFlagEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static BlogAtcFlagEnum stateOf(int index) {
        for (BlogAtcFlagEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
