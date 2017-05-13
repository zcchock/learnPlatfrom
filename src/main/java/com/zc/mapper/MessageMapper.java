package com.zc.mapper;

import com.zc.entity.Message;

import java.util.List;

/**
 * Created by chock on 2017/5/13.
 */
public interface MessageMapper {

    Message queryById(int msgId);

    List<Message> queryByActId(int atcId);

    List<Message> queryAll();

    int insertSelective(Message message);

    int delById(int msgId);

}
