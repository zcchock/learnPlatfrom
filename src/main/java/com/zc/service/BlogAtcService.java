package com.zc.service;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/4/29.
 */
public interface BlogAtcService {

    DataResponse getAtcById (DataRequest dataRequest);

    DataResponse getAllAtcs (DataRequest dataRequest);

    DataResponse addAtc(DataRequest dataRequest, HttpServletRequest request);

    DataResponse delAtc(DataRequest dataRequest);

    DataResponse updateAtc(DataRequest dataRequest);

    DataResponse getActsByUid(DataRequest dataRequest);

    DataResponse getActsByType(DataRequest dataRequest);

    DataResponse getActsByTitle(DataRequest dataRequest);
}
