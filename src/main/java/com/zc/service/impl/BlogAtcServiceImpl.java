package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.DateUtils;
import com.zc.api.Global;
import com.zc.entity.BlogAtc;
import com.zc.mapper.BlogAtcMapper;
import com.zc.mapper.CommonImpl;
import com.zc.service.BlogAtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chock on 2017/4/29.
 */
@Service("blogAtcService")
@Transactional
public class BlogAtcServiceImpl implements BlogAtcService {

    private Class implClass = BlogAtcServiceImpl.class;     //Logger日志的Class
    @Autowired
    private BlogAtcMapper blogAtcMapper;

    private CommonImpl commonImpl = new CommonImpl();
    private DateUtils dateUtils = new DateUtils();

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
        DataResponse response = new DataResponse();
        Integer atcId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "atcId", Integer.class, implClass);
        try {
            int sucFlag = blogAtcMapper.delAtcById(atcId);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "删除文章成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "删除文章失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public DataResponse updateAtc(DataRequest dataRequest) {
        return null;
    }

    public DataResponse getActsByUid(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer userId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "userId", Integer.class, implClass);
        try {
            List<BlogAtc> atcs = blogAtcMapper.queryActsByUid(userId);
            if (atcs != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, atcs, "成功查询用户博文");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, atcs, "查询用户博文失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
