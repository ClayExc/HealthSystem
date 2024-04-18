package com.sports.controller;


import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.entity.FatigueDegree;
import com.sports.exception.FatigueDegreeException;
import com.sports.service.impl.FatigueDegreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/fatigueDegree/")
@CrossOrigin(origins="*",maxAge=3600)
public class FatigueDegreeController {

    @Autowired
    FatigueDegreeServiceImpl fatigueDegreeService;

    /**
     * 疲劳度等级的添加
     */
    @RequestMapping(value = "addFatigueDegree.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = FatigueDegreeException.class)
    public ServerResponse addFatigueDegree(FatigueDegree fatigueDegree){

        return fatigueDegreeService.addFatigueDegree(fatigueDegree);
    }

    /**
     * 疲劳度等级的查询
     */

    @RequestMapping(value = "selectFatigueDegree.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = FatigueDegreeException.class)
    @ResponseBody
    public JSONArray selectFatigueDegree(FatigueDegree fatigueDegree){
        JSONArray jsonArray=fatigueDegreeService.selectFatigueDegree(fatigueDegree);
        return jsonArray;
    }

    /**
     * 疲劳度等级的删除
     */
    @RequestMapping(value = "deleteFatigueDegree.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = FatigueDegreeException.class)
    @ResponseBody
    public String   deleteFatigueDegree(FatigueDegree fatigueDegree){
        return fatigueDegreeService.deleteFatigueDegree(fatigueDegree);

    }

    /**
     * 疲劳度等级的修改
     * 先查 后改
     */

    @RequestMapping(value = "selectById.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = FatigueDegreeException.class)
    @ResponseBody
    public FatigueDegree selectById(FatigueDegree fatigueDegree)throws Exception{
        FatigueDegree fatigueDegree1=fatigueDegreeService.selectById(fatigueDegree);
        return fatigueDegree1;
    }

    @RequestMapping(value = "updateFatigueDegree.do",method = RequestMethod.POST)
    @Transactional(rollbackFor = FatigueDegreeException.class)
    public ServerResponse updateFatigueDegree(FatigueDegree fatigueDegree, HttpSession session)throws Exception{
        ServerResponse<FatigueDegree> response =fatigueDegreeService.updateFatigueDegree(fatigueDegree);
        return response;
    }
}
