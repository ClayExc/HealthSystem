package com.sports.controller;

import com.sports.common.Const;
import com.sports.common.ServerResponse;
import com.sports.entity.SportAbility;
import com.sports.entity.Subhealthy;
import com.sports.exception.UserException;
import com.sports.service.HealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/healthy/")
public class HealthyController {
    @Autowired
    private HealthyService healthyService;

    @RequestMapping(value = "tizhi_evaluting.do", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public ServerResponse tizhi_evaluating(@RequestBody Subhealthy subhealthy,@RequestParam("disease_str") String disease_str, @RequestParam("user_height")float user_height, @RequestParam("user_weight")float user_weight, @RequestParam("user_optimal_rate1")int user_optimal_rate1, @RequestParam("user_optimal_rate2") int user_optimal_rate2, @RequestParam("user_chest") float user_chest, @RequestParam("user_waist") float user_waist,@RequestParam("user_hipline")  float user_hipline,@RequestParam("uid")  int uid) {
      ServerResponse response = healthyService.tizhi_evaluting(subhealthy,disease_str,user_height,user_weight,user_optimal_rate1,user_optimal_rate2,user_chest,user_waist,user_hipline,uid);
      return response ;
    }

    @RequestMapping(value = "sportability_evaluting.do", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public ServerResponse sportability_evaluting(@RequestBody SportAbility sportAbility) {
        ServerResponse response = healthyService.sportability_evaluting(sportAbility);
        return response ;
    }

    @RequestMapping(value = "tizhi_comprehensive.do", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public ServerResponse tizhi_comprehensive(int userid) {
        healthyService.tizhi_comprehensive(userid);

        return null ;
    }
}
