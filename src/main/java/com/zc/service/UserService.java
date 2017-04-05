package com.zc.service;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;

/**
 * Created by chock on 2017/4/4.
 */
public interface UserService {

    DataResponse getUserById (DataRequest dataRequest);

    DataResponse getAllUsers (DataRequest dataRequest);

    DataResponse checkLogin (DataRequest dataRequest);


    DataResponse login (DataRequest dataRequest);
}