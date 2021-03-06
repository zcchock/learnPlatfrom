package com.zc.mapper;

import com.zc.entity.BlogAtc;

import java.util.List;

/**
 * Created by chock on 2017/4/3.
 */
public interface BlogAtcMapper {

    BlogAtc queryAtc(int atcId);

    List<BlogAtc> queryAtcsByType(String atcType);

    List<BlogAtc> queryAtcsByTitle(String atcTitle);

    List<BlogAtc> queryAll();

    int insertSelective(BlogAtc blogAtc);

    int delAtcById(int atcId);

    int updateById(BlogAtc blogAtc);

    List<BlogAtc> queryActsByUid(int userId);
}
