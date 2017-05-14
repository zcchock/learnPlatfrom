package com.zc.service;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/4/4.
 */
public interface UserService {

    DataResponse getUserById (DataRequest dataRequest);

    DataResponse getAllUsers (DataRequest dataRequest);

    DataResponse login (DataRequest dataRequest);

    DataResponse countNum ();

    DataResponse addUser(DataRequest dataRequest);

    DataResponse delUser(DataRequest dataRequest);

    DataResponse updateUser(DataRequest dataRequest);

    DataResponse getId(DataRequest dataRequest, HttpServletRequest request);

}
