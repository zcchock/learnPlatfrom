package com.zc.mapper;

import com.zc.entity.BlogAtc;

/**
 * Created by chock on 2017/4/3.
 */
public interface BlogAtcMapper {

    /**
     * 插入预约图书记录
     *
     * @param atcId
     * @return 插入的行数
     */
    int insertBlogAtc(int atcId);

    /**
     * 通过主键查询预约图书记录，并且携带图书实体
     *
     * @param atcId
     * @return
     */
    BlogAtc queryByKey(int atcId);

}
