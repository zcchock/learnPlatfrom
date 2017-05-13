package com.zc.service;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/5/12.
 */
public interface FriendService {

    DataResponse getFriByUser(DataRequest dataRequest);

    DataResponse getFriByFri(DataRequest dataRequest);

    DataResponse getFris(DataRequest dataRequest);

    DataResponse countFriend();

    DataResponse insert(DataRequest dataRequest, HttpServletRequest request);

    DataResponse delFri(DataRequest dataRequest);
}
