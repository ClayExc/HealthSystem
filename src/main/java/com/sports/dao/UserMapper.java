package com.sports.dao;

import com.sports.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //检测用户名和密码是否匹配
    User selectLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);
    //检测数据库中是否存在该用户名
    int checkUsername(String userName);
}