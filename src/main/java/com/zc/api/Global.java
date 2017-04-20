package com.zc.api;

/**
 * Created by chock on 2017/4/5.
 */
public class Global {
    
    /**
     * 会话参数
     */
    public static final String SYS_SESSION_USR_ID = "usrId";
    public static final String SYS_SESSION_USR_NAME = "usrName";
    public static final String SYS_SESSION_USR_TYPE = "usrType";

    public static final String SYS_SESSION_BNK_CD = "bnkCd";
    
    public static final String LOGIN_TYPE = "userLogin";



    /**
     * 成功标志
     */
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    //用户信息表密码状态-Y 正常
    public static final String TBL_USER_INFO_USR_TYPE_YES = "Y";
    //用户信息表密码状态-N 锁定
    public static final String TBL_USER_INFO_USR_TYPE_NO = "N";
    //用户信息表是否首次密码
    public static final String TBL_USER_INFO_USR_TYPE_FIRST = "Z";

    public static final String TBL_USER_INFO_USR_PWD = "123456";

    //取消状态
    public static final String STATUS_NO = "99";
    //成功状态
    public static final String STATUS_YES = "00";
    //初始值
    public static final String STATUS_INISIAL = "09";
    //通过
    public static final String TRAN_STATUS00 = "00";
    //交易成功
    public static final String TRAN_STATUS11 = "11";


}
