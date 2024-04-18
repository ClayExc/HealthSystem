package com.sports.dao;

import com.sports.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    //检测用户名和密码是否匹配
    AdminUser selectLogin(@Param("username") String userName, @Param("password") String userPassword);
    //检测数据库中是否存在该用户名
    int checkUsername(String username);
    int userDelete(String userId);
}