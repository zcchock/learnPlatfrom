package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.DateUtils;
import com.zc.api.Global;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.UserMapper;
import com.zc.entity.User;
import com.zc.service.UserService;
import org.springframework.beans.BeanUtils;
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

    /**
     * 通过ID查询用户
     *
     * @param dataRequest
     * @return
     */
    public DataResponse getUserById(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer userId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "userId", Integer.class, implClass);
        try {
            User user = userMapper.queryUser(userId);
            if (user != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, user, "成功查询用户信息");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, user, "查询用户信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 获取全部用户（用户列表）
     *
     * @param dataRequest
     * @return
     */
    public DataResponse getAllUsers(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        List<User> list = userMapper.queryAll();
        return commonImpl.responseDeal(response, Global.SUCCESS, list, "查询用户列表成功");
    }

    /**
     * 登录
     *
     * @param dataRequest
     * @return
     */
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
        return response;
    }

    /**
     * 用户计数（初步用于ID确定，后期可能不要）
     *
     * @return
     */
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

    /**
     * 插入用户
     *
     * @param dataRequest
     * @return
     */
    public DataResponse addUser(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        try {
            User inputUser = (User) commonImpl.mapJsonToObj(dataRequest, response, "user", User.class, implClass);
//            inputUser.setUserId(userMapper.countUser() + 1);
            inputUser.setRoleId("1");
            inputUser.setLastLoginTime(dateUtils.formatDateTimeN(new Date()));
            int sucFlag = userMapper.insertSelective(inputUser);
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

    /**
     * 删除用户
     *
     * @param dataRequest
     * @return
     */
    public DataResponse delUser(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer userId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "userId", Integer.class, implClass);
        try {
            int sucFlag = userMapper.delUserById(userId);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "删除用户成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "删除用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 更新用户信息
     *
     * @param dataRequest
     * @return
     */
    public DataResponse updateUser(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        User newUser = (User) commonImpl.mapJsonToObj(dataRequest, response, "user", User.class, implClass);
        //        Integer userId = newUser.getUserId();
        //        User oldUser = userMapper.queryUser(userId);
        try {
            int sucFlag = userMapper.updateById(newUser);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "用户更新成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "用户更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
