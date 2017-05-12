package com.zc.mapper;

import com.zc.entity.UserFriend;

import java.util.List;

/**
 * Created by chock on 2017/5/12.
 */
public interface FriendMapper {
    UserFriend queryByUser(int userId);

    UserFriend queryByFri(int friId);

    List<UserFriend> queryAll();

    int countFri();

    int insertSelective(UserFriend userFriend);

    int delFriById(int userId);
}
