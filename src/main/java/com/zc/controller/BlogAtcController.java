package com.zc.controller;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.service.BlogAtcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chock on 2017/4/29.
 */
@RestController
@RequestMapping("/atc")
public class BlogAtcController {

    //日志打印
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BlogAtcService blogAtcService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataResponse userList(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.getAllAtcs(dataRequest);
        return dataResponse;
    }

    @RequestMapping(value = "/getAtc", method = RequestMethod.POST)
    public DataResponse getUserById(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.getAtcById(dataRequest);
        return dataResponse;
    }

    @RequestMapping(value = "/getAtcs", method = RequestMethod.POST)
    public DataResponse getAtcsByUid(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.getActsByUid(dataRequest);
        return dataResponse;
    }

    @RequestMapping(value = "/addAtc", method = RequestMethod.POST)
    public DataResponse addUser(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.addAtc(dataRequest);
        return dataResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResponse updateUser(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.updateAtc(dataRequest);
        return dataResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public DataResponse delUser(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.delAtc(dataRequest);
        return dataResponse;
    }
}
