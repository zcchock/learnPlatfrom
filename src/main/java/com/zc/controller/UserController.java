package com.zc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.service.UserService;

/**
 * Created by chock on 2017/4/3.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //日志打印
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;

    /**
     * 用户列表查询；用于展示数据
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataResponse userList(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = userService.getAllUsers(dataRequest);
        return dataResponse;
    }

    /**
     * 根据Id获取用户信息
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public DataResponse getUserById(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = userService.getUserById(dataRequest);
        return dataResponse;
    }

    /**
     * 获取当前的用户ID
     *
     * @param dataRequest
     * @param request
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/getId", method = RequestMethod.POST)
    public DataResponse getId(@RequestBody(required = false) DataRequest dataRequest, HttpServletRequest request) {
        DataResponse dataResponse = userService.getId(dataRequest, request);
        return dataResponse;
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResponse userLogin(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = userService.login(dataRequest);
        return dataResponse;
    }*/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) throws Exception {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null) {
            LOGGER.info("身份认证失败，账号密码不正确");
        }
        return "/index.html";
    }

    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public DataResponse admin(HttpServletRequest request) throws Exception {
        LOGGER.info("damin");
        DataResponse dataReaponse = new DataResponse();
        dataReaponse.setCode("200");
        return dataReaponse;
    }

    /**
     * 插入新用户
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public DataResponse addUser(@RequestBody(required = false) DataRequest dataRequest) {
        DataResponse dataResponse = userService.addUser(dataRequest);
        return dataResponse;
    }

    /**
     * 注册
     *
     * @param dataRequest
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public DataResponse register(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = userService.addUser(dataRequest);
        return dataResponse;
    }

    /**
     * 删除用户
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public DataResponse delUser(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = userService.delUser(dataRequest);
        return dataResponse;
    }

    /**
     * 用户更新
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResponse updateUser(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = userService.updateUser(dataRequest);
        return dataResponse;
    }

}
