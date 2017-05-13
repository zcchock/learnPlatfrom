package com.zc.service.impl;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.api.DateUtils;
import com.zc.api.Global;
import com.zc.entity.Message;
import com.zc.mapper.CommonImpl;
import com.zc.mapper.MessageMapper;
import com.zc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by chock on 2017/5/13.
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService{

    private Class implClass = MessageServiceImpl.class;     //Logger日志的Class
    @Autowired
    private MessageMapper messageMapper;

    private CommonImpl commonImpl = new CommonImpl();
    private DateUtils dateUtils = new DateUtils();

    @Override
    public DataResponse getMsgById(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer msgId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "msgId", Integer.class, implClass);
        try {
            Message message = messageMapper.queryById(msgId);
            if (message != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, message, "成功查询评论");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, message, "查询评论失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public DataResponse getMsgByAtc(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer atcId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "atcId", Integer.class, implClass);
        try {
            List<Message> msg = messageMapper.queryByActId(atcId);
            if (msg != null) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, msg, "成功查询评论列表");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, msg, "查询评论列表失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public DataResponse getMsgs(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        List<Message> list = messageMapper.queryAll();
        return commonImpl.responseDeal(response, Global.SUCCESS, list, "查询全部评论成功");
    }

    @Override
    public DataResponse insert(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        try {
            Message msg = (Message) commonImpl.mapJsonToObj(dataRequest, response, "message", Message.class, implClass);
            msg.setMsgTime(dateUtils.formatDateTimeN(new Date()));
            int sucFlag = messageMapper.insertSelective(msg);
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

    @Override
    public DataResponse delMsg(DataRequest dataRequest) {
        DataResponse response = new DataResponse();
        Integer atcId = (Integer) commonImpl.mapJsonToObj(dataRequest, response, "atcId", Integer.class, implClass);
        try {
            int sucFlag = messageMapper.delById(atcId);
            if (sucFlag == 1) {
                response = commonImpl.responseDeal(response, Global.SUCCESS, sucFlag, "删除成功");
            } else {
                response = commonImpl.responseDeal(response, Global.ERROR, sucFlag, "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
