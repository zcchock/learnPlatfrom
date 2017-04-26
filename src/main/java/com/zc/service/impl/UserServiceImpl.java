package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.DateUtils;
import com.zc.api.Global;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.UserMapper;
import com.zc.entity.User;
import com.zc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by chock on 2017/4/3.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private Class implClass = UserServiceImpl.class;     //Logger日志的Class
    @Autowired
    private UserMapper userMapper;

    private CommonImpl commonImpl = new CommonImpl();
    private DateUtils dateUtils = new DateUtils();

    public DataResponse getUserById(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        response.setStatus("1");
        User user = userMapper.queryUser((Integer) dataRequest.getData());
        response.setData(user);
        response.setMessage("成功查询用户信息");
        return response;
    }

    public DataResponse getAllUsers(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        List<User> list = userMapper.queryAll();
        return commonImpl.responseDeal(response, Global.SUCCESS, list, "查询用户列表成功");
    }

    public DataResponse login(DataRequest dataRequest) {

        DataResponse response = new DataResponse();
        String inputUser = (String) commonImpl.mapJsonToObj(dataRequest, response, "inputUser", String.class, implClass);
        String inputPassword = (String) commonImpl.mapJsonToObj(dataRequest, response, "inputPassword", String.class, implClass);
        try {
            //具体方法
            User user = userMapper.queryUserByName(inputUser);
            System.out.println(user);
            if (inputPassword.equals(user.getPassword())) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, "/showUser", "登陆成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, "/index", "密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response.getData());
        System.out.println(response);
        return response;
    }

    public DataResponse countNum() {
        DataResponse response = new DataResponse();
        try {
            //具体方法
            int userNum = userMapper.countUser();
            System.out.println(userNum);
            if (userNum != 0) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, "/showUser", "登陆成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, "/index", "密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response.getData());
        return response;
    }

    public DataResponse addUser(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        User inputUser = (User) commonImpl.mapJsonToObj(dataRequest, response, "user", User.class, implClass);
        inputUser.setUserId(userMapper.countUser() + 1);
        inputUser.setRoleId("1");
        inputUser.setLastLoginTime(dateUtils.formatDateTimeN(new Date()));
        System.out.println(inputUser);
        int sucFlag = userMapper.insertSelective(inputUser);
        System.out.println(sucFlag);
        if (sucFlag == 1) {
            response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "添加成功");
        } else {
            response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "添加失败");
        }
        return response;
    }

}
