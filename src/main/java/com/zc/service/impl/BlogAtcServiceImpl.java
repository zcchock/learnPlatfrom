package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.service.BlogAtcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chock on 2017/4/29.
 */
@Service("blogAtcService")
@Transactional
public class BlogAtcServiceImpl implements BlogAtcService {
    public DataResponse getAtcById(DataRequest dataRequest) {
        return null;
    }

    public DataResponse getAllAtcs(DataRequest dataRequest) {
        return null;
    }

    public DataResponse addAtc(DataRequest dataRequest) {
        return null;
    }

    public DataResponse delAtc(DataRequest dataRequest) {
        return null;
    }

    public DataResponse updateAtc(DataRequest dataRequest) {
        return null;
    }
}
