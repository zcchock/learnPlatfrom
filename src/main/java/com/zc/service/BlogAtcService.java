package com.zc.service;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;

/**
 * Created by chock on 2017/4/29.
 */
public interface BlogAtcService {

    DataResponse getAtcById (DataRequest dataRequest);

    DataResponse getAllAtcs (DataRequest dataRequest);

    DataResponse addAtc(DataRequest dataRequest);

    DataResponse delAtc(DataRequest dataRequest);

    DataResponse updateAtc(DataRequest dataRequest);
}
