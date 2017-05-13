package com.zc.service;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/5/13.
 */
public interface MessageService {

//    HttpServletRequest request

    DataResponse getMsgById(DataRequest dataRequest);

    DataResponse getMsgByAtc(DataRequest dataRequest);

    DataResponse getMsgs(DataRequest dataRequest);

    DataResponse insert(DataRequest dataRequest, HttpServletRequest request);

    DataResponse delMsg(DataRequest dataRequest);
}
