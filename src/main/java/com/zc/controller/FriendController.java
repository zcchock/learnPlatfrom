package com.zc.controller;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.service.FriendService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/5/12.
 */
@RestController
@RequestMapping("/fri")
public class FriendController {
    //日志打印
    private static final Logger LOGGER = LoggerFactory.getLogger(FriendController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FriendService friendService;

    @RequiresRoles("admin")
    @RequestMapping(value = "/addFri", method = RequestMethod.POST)
    public DataResponse addFri(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = friendService.insert(dataRequest);
        return dataResponse;
    }
}
