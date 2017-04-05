package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.Global;
import com.zc.api.service.CommonImpl;
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
    @Autowired
    private CommonImpl commonImpl;

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
        response.setStatus("1");
        List<User> list = userMapper.queryAll(0, 4);
        response.setData(list);
        response.setMessage("成功查询用户列表");
        return response;
    }

    public DataResponse checkLogin(DataRequest dataRequest) {
        DataResponse dataResponse = new DataResponse();
        User user = userMapper.queryUserByName((String) dataRequest.getData());

        if ("password".equals(user.getPassword())) {
            dataResponse.setStatus("1");
            dataResponse.setMessage("允许登录");
            dataResponse.setData(1);
        }
        return dataResponse;
    }

    public DataResponse login(DataRequest dataRequest) {

            DataResponse response = new DataResponse();
            String   inputUser = (String) commonImpl.mapJsonToObj(dataRequest, response, "inputUser", String.class, implClass);
            String   inputPassword = (String) commonImpl.mapJsonToObj(dataRequest, response, "inputPassword", String.class, implClass);
            try{
               //具体方法
            }catch (Exception e){

            }
            return commonImpl.responseDeal(response, Global.SUCCESS,"登陆后的页面路径     如/index", "登陆成功");

    }

}
