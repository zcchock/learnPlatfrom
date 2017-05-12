package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.DateUtils;
import com.zc.api.Global;
import com.zc.entity.UserFriend;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.FriendMapper;
import com.zc.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chock on 2017/5/12.
 */
public class FriendServiceImpl implements FriendService {

    private Class implClass = FriendServiceImpl.class;     //Logger日志的Class
    @Autowired
    private FriendMapper friendMapper;

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
            Integer friendId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "friend", Integer.class, implClass);
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(1);
            userFriend.setFriId(friendId);
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
