package com.zc.mapper;

import com.zc.entity.Role;

import java.util.List;

/**
 * Created by chock on 2017/5/13.
 */
public interface RoleMapper {

    Role queryById(int roleId);

    String queryNameById(int roleId);

    List<Role> queryAll();

}
