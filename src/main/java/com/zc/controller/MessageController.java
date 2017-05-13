package com.zc.controller;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.service.MessageService;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
 * Created by chock on 2017/5/13.
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    //日志打印
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MessageService messageService;

    @RequiresRoles("admin")
    @RequestMapping(value = "/addMsg", method = RequestMethod.POST)
    public DataResponse addMsg(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = messageService.insert(dataRequest, request);
        return dataResponse;
    }



}
