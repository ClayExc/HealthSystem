package com.sports.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sports.common.ServerResponse;
import com.sports.entity.SportsEffect;
import com.sports.entity.User;

public interface UserService {
    /**
     *
     * @param userName
     * @param userPassword
     * @return d
     */
    ServerResponse<User> login(String userName, String userPassword);
    ServerResponse<String> register(User user);
    ServerResponse<User> updateInformation(User user);
//    ServerResponse<SportsEffect>  streeList(SportsEffect sportsEffect);

    //目的
    JSONArray goalList();
    //兴趣
    JSONArray interestList();
    //器材
    JSONArray equipmentiList();

}
