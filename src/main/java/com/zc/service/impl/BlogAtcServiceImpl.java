package com.zc.service.impl;

import com.zc.api.*;
import com.zc.entity.BlogAtc;
import com.zc.entity.User;
import com.zc.mapper.BlogAtcMapper;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.UserMapper;
import com.zc.service.BlogAtcService;
import com.zc.shiro.model.UserCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by chock on 2017/4/29.
 */
@Service("blogAtcService")
@Transactional
public class BlogAtcServiceImpl implements BlogAtcService {

    private Class implClass = BlogAtcServiceImpl.class;     //Logger日志的Class
    @Autowired
    private BlogAtcMapper blogAtcMapper;
    @Autowired
    private UserMapper userMapper;

    private CommonImpl commonImpl = new CommonImpl();
    private DateUtils dateUtils = new DateUtils();
    private FileMethod fileMethod = new FileMethod();
    private String urlHead = "E:/School/FinalDesign/actFiles/";

    /**
     * 根据ID获取文章信息
     *
     * @param dataRequest
     * @return
     */
    public DataResponse getAtcById(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer atcId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "atcId", Integer.class, implClass);
        try {
            BlogAtc blogAtc = blogAtcMapper.queryAtc(atcId);
            blogAtc.setAtcView(blogAtc.getAtcView() + 1);
            blogAtcMapper.updateById(blogAtc); //更新浏览次数
            User author = userMapper.queryUser(blogAtc.getUserId());
            if (blogAtc != null) {
                blogAtc.setAtcUrl(fileMethod.ReadTxtFile(blogAtc.getAtcUrl()));
                blogAtc.setAtcBackup(author.getAccount());
                response = commonImpl.responseDeal(response, Global.SUCCESS, blogAtc, "成功读取文章");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, blogAtc, "读取文章失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public DataResponse getAllAtcs(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        try {
            List<BlogAtc> atcs = blogAtcMapper.queryAll();
            if (atcs != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, atcs, "成功查询用户博文");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, atcs, "查询用户博文失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 添加文章
     *
     * @param dataRequest
     * @return
     */
    public DataResponse addAtc(DataRequest dataRequest, HttpServletRequest request) {
        DataResponse response = new DataResponse();
        String account = (String) request.getAttribute("userAcc");
        try {
            BlogAtc blogAtc = (BlogAtc) commonImpl.mapJsonToObj(dataRequest, response, "blogAtc", BlogAtc.class, implClass);
            String url = urlHead + blogAtc.getAtcTitle() + ".txt";
            fileMethod.TextToFile(url, blogAtc.getAtcUrl());
            blogAtc.setAtcUrl(url);

            User user = userMapper.queryUserByName(account);

            blogAtc.setUserId(user.getUserId());
            blogAtc.setAtcTime(dateUtils.formatDateTimeN(new Date()));
            blogAtc.setAtcFlag("1");
            int sucFlag = blogAtcMapper.insertSelective(blogAtc);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, blogAtc, "成功添加文章");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, blogAtc, "添加文章失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 删除文章
     *
     * @param dataRequest
     * @return
     */
    public DataResponse delAtc(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer atcId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "atcId", Integer.class, implClass);
        try {
            int sucFlag = blogAtcMapper.delAtcById(atcId);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "删除文章成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "删除文章失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public DataResponse updateAtc(DataRequest dataRequest) {
        return null;
    }

    /**
     * 根据用户ID获取其文章
     *
     * @param dataRequest
     * @return
     */
    public DataResponse getActsByUid(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer userId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "userId", Integer.class, implClass);
        try {
            List<BlogAtc> atcs = blogAtcMapper.queryActsByUid(userId);
            if (atcs != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, atcs, "成功查询用户博文");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, atcs, "查询用户博文失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 通过文章类型来获取文章列表
     *
     * @param dataRequest
     * @return
     */
    @Override
    public DataResponse getActsByType(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        String type = (String) commonImpl.mapJsonToObj(dataRequest, response, "userId", String.class, implClass);
        try {
            List<BlogAtc> atcs = blogAtcMapper.queryAtcsByType(type);
            if (atcs != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, atcs, "成功查询用户博文");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, atcs, "查询用户博文失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public DataResponse getActsByTitle(DataRequest dataRequest) {
        return null;
    }

}
