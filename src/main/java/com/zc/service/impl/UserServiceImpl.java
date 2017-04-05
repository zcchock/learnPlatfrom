package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.Global;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.UserMapper;
import com.zc.entity.User;
import com.zc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public DataResponse login(DataRequest dataRequest) {

            DataResponse response = new DataResponse();
            String inputUser = (String) commonImpl.mapJsonToObj(dataRequest, response, "inputUser", String.class, implClass);
            String inputPassword = (String) commonImpl.mapJsonToObj(dataRequest, response, "inputPassword", String.class, implClass);
            try{
               //具体方法
               User user = userMapper.queryUserByName(inputUser);
               if (inputPassword.equals(user.getPassword())) {
                   response.setStatus(Global.STATUS_YES);
                   response.setMessage("允许登录");
                   response.setData(Global.SUCCESS);
               }
            }catch (Exception e){
                System.out.println();
            }
            return commonImpl.responseDeal(response, Global.SUCCESS, "/main", "登陆成功");

    }

}
