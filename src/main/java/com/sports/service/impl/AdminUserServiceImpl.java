package com.sports.service.impl;

import com.sports.common.ResponseCode;
import com.sports.common.ServerResponse;
import com.sports.dao.AdminUserMapper;
import com.sports.entity.AdminUser;
import com.sports.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service("iAdminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private AdminUserMapper adminUserMapper;


    @Override
    public ServerResponse<AdminUser> login(String username, String password) {
        //校验是否存在该用户名
        int resultCount = adminUserMapper.checkUsername(username);
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
//      将password进行MD5加密 暂时先不用
//        String md5Password = MD5Util.MD5EncodeUtf8(userPassword);
        AdminUser adminUser =adminUserMapper.selectLogin(username,password);
        if (adminUser==null){
            return ServerResponse.createByErrorMessage("密码错误");
//      throw new UserException(ResponseCode.ERROR);
        }
        logger.info("用户："+adminUser.getUsername()+"登录成功");
        // 将密码置空
        adminUser.setPassword(null);
        // 登录成功后返回学生信息
        logger.info(adminUser.toString());
//        return ServerResponse.createBySuccess("登录成功", user);
        return ServerResponse.createBySuccess(ResponseCode.SUCCESS.getDesc(),adminUser);
    }

    @Override
    public int userDelete(String userId) {
        return adminUserMapper.userDelete(userId);
    }
}
