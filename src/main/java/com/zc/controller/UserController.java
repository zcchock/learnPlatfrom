package com.zc.controller;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/4/3.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public DataResponse Userlist(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        DataResponse dataResponse = userService.getUserById(dataRequest);
        return dataResponse;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResponse userLogin(DataRequest dataRequest) {
        DataResponse dataResponse = userService.login(dataRequest);
        return  dataResponse;
    }

}
