package com.zc.controller;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.isNumber;
import com.zc.service.BlogAtcService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by chock on 2017/4/29.
 */
@RestController
@RequestMapping("/atc")
public class BlogAtcController {

    //日志打印
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private isNumber num = new isNumber();

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BlogAtcService blogAtcService;

    /**
     * 获取文章列表
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataResponse userList(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.getAllAtcs(dataRequest);
        return dataResponse;
    }

    /**
     * 获取文章详情
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/getAtc", method = RequestMethod.POST)
    public DataResponse getUserById(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.getAtcById(dataRequest);
        return dataResponse;
    }

    /**
     * 获取用户的文章
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/getAtcs", method = RequestMethod.POST)
    public DataResponse getAtcsByUid(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        Map<String, String> value = (Map<String, String>) dataRequest.getData();
        DataResponse dataResponse = new DataResponse();
        if (num.isNumeric(value.get("userId"))) {
            dataResponse = blogAtcService.getActsByUid(dataRequest);
        } else if ("all".equals(value.get("userId"))) {
            dataResponse = blogAtcService.getAllAtcs(dataRequest);
        } else {
            dataResponse = blogAtcService.getActsByType(dataRequest);
        }
        return dataResponse;
    }

    /**
     * 添加文章
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/addAtc", method = RequestMethod.POST)
    public DataResponse addAtc(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.addAtc(dataRequest, request);
        return dataResponse;
    }

    /**
     * 更新文章
     *
     * @param dataRequest
     * @return
     */
    @RequiresRoles(value = {"admin", "normal"}, logical = Logical.OR)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResponse updateUser(@RequestBody(required = false) DataRequest dataRequest) {
        String clientIp = request.getRemoteAddr();
        dataRequest.setClientIp(clientIp);
        DataResponse dataResponse = blogAtcService.updateAtc(dataRequest);
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
        DataResponse dataResponse = blogAtcService.delAtc(dataRequest);
        return dataResponse;
    }
}
