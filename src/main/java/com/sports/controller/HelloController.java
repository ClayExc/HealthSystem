package com.sports.controller;

import com.sports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
        @Autowired
        private UserService userService;


//            @RequestMapping("/login")
//            @ResponseBody
//            public String doLogin (User user, HttpSession session ){
//                System.out.println(user.getUserAccount()+"-----");
//                User u = userService.findUserByAccountAndPwd(user);
//                System.out.println(user.getUserAccount()+"-----");
//                if (u != null) {
//                    session.setAttribute("User", u);
//                    return "true";
//                } else {//用户不存在，则进行注册
//                    return "false";
//
//                }
//
//            }
}
