package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.DateUtils;
import com.zc.api.Global;
import com.zc.entity.BlogAtc;
import com.zc.entity.UserFriend;
import com.zc.mapper.BlogAtcMapper;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.FriendMapper;
import com.zc.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chock on 2017/5/12.
 */
@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

    private Class implClass = FriendServiceImpl.class;     //Logger日志的Class
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private BlogAtcMapper blogAtcMapper;

    private CommonImpl commonImpl = new CommonImpl();
    private DateUtils dateUtils = new DateUtils();

    public DataResponse getFriByUser(DataRequest dataRequest) {
        return null;
    }

    public DataResponse getFriByFri(DataRequest dataRequest) {
        return null;
    }

    public DataResponse getFris(DataRequest dataRequest) {
        return null;
    }

    public DataResponse countFriend() {
        return null;
    }

    public DataResponse insert(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        try {
            Integer atcId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "atcId", Integer.class, implClass);
            //通过文章Id查询作者Id
            BlogAtc blogAtc = blogAtcMapper.queryAtc(atcId);
            //添加好友实体
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(1);
            userFriend.setFriId(blogAtc.getUserId());
            int sucFlag = friendMapper.insertSelective(userFriend);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "添加成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public DataResponse delFri(DataRequest dataRequest) {
        return null;
    }
}
