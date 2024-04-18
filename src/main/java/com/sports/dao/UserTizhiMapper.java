package com.sports.dao;

import com.sports.entity.UserTizhi;

public interface UserTizhiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTizhi record);

    int insertSelective(UserTizhi record);

    UserTizhi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTizhi record);

    int updateByPrimaryKey(UserTizhi record);

    UserTizhi selectByPrimaryUserid(int userid);
}