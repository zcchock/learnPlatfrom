package com.zc.mapper;

import com.zc.entity.User;

import java.util.List;

/**
 * Created by chock on 2017/4/3.
 */
public interface UserMapper {

    User queryUser(int userId);

    User queryUserByName(String account);

    List<User> queryAll();

    int countUser();

//    int insertUser(User user);

    int insertSelective(User user);

    int delUserById(int userId);

    int updateById(User user);
}
