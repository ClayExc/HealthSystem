package com.sports.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sports.common.Const;
import com.sports.common.ResponseCode;
import com.sports.common.ServerResponse;
import com.sports.entity.SportsEffect;
import com.sports.entity.User;
import com.sports.exception.UserException;
import com.sports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user/")
@CrossOrigin(origins="*",maxAge=3600)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse register(User user){
        if (user == null){
            throw new UserException(ResponseCode.PARAM_IS_INVALID);
        }
        System.out.println(user.toString());
        return userService.register(user);
    }

    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse login(String userName,String userPassword,HttpSession session) {
        ServerResponse response = userService.login(userName, userPassword);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
            //以秒为单位，即在没有活动30分钟后，session将失效
            session.setMaxInactiveInterval(Const.SessionExtime.SESSION_EX_TIME);
        }
        return response;
    }

    /**
     * 用户退出
     * @param session
     * @return
     */

    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 更新用户信息，参数user是更新之后的用户对象信息
     */
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @Transactional(rollbackFor = UserException.class)
    public ServerResponse update_information(HttpSession session,User user){
        //判断用户登录状态
        User currentUser=(User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setUserId(currentUser.getUserId());

        ServerResponse<User> response =userService.updateInformation(user);
        if (response.isSuccess()){
            response.getData().setUserName(currentUser.getUserName());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    /**
     * 返回给前台下拉框数据
     */
    @RequestMapping(value = "goalList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = UserException.class)
    @ResponseBody
    public JSONArray goalListReturn(){
        JSONArray jsonArray = userService.goalList();
        return jsonArray;
    }
    @RequestMapping(value = "interestList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = UserException.class)
    @ResponseBody
    public JSONArray interestListReturn(){
        JSONArray jsonArray = userService.interestList();
        return jsonArray;
    }
    @RequestMapping(value = "equipmentList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = UserException.class)
    @ResponseBody
    public JSONArray equipmentListReturn(){
        JSONArray jsonArray = userService.equipmentiList();
        return jsonArray;
    }
}
