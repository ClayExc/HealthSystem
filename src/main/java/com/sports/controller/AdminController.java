package com.sports.controller;

import com.alibaba.fastjson.JSONObject;
import com.sports.common.Const;
import com.sports.common.ServerResponse;
import com.sports.entity.User;
import com.sports.exception.UserException;
import com.sports.service.AdminUserService;
import com.sports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
@CrossOrigin(origins="*",maxAge=3600)
public class AdminController {

    @Autowired
    AdminUserService adminUserService;

    @Autowired
    private UserService userService;
    /**
     * 管理员登录
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse login(String username, String password, HttpSession session) {
        ServerResponse response = adminUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
            //以秒为单位，即在没有活动30分钟后，session将失效
            session.setMaxInactiveInterval(Const.SessionExtime.SESSION_EX_TIME);
        }
        return response;
    }
    /**
     * 登出
     *
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }
    /**
     * 用户管理——删除用户
     */
    @RequestMapping(value = "deleteUser.do", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = UserException.class)
    public Map<String,Object> deleteUser(String  userId) {
        Map<String, Object> map=new HashMap<String, Object>();
        adminUserService.userDelete(userId);
        map.put("message", "删除成功");
        return map;
    }
}
