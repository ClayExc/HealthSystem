package com.sports.controller;


import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.entity.Disease;
import com.sports.exception.DiseaseException;
import com.sports.service.impl.DiseaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/disease/")
@CrossOrigin(origins="*",maxAge=3600)
public class DiseaseController {

    @Autowired
    DiseaseServiceImpl diseaseService;
    /**
     * 疾病的添加
     */
    @RequestMapping(value = "addDisease.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = DiseaseException.class)
    public ServerResponse addDisease(Disease disease){
       return diseaseService.addDisease(disease);
    }

    /**
     * 疾病的查询
     */

    @RequestMapping(value = "selectDisease.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = DiseaseException.class)
    @ResponseBody
    public JSONArray selectDisease(Disease disease){
        JSONArray jsonArray=diseaseService.selectDisease(disease);
        return jsonArray;
    }

    /**
     * 疾病的删除
     */
    @RequestMapping(value = "deleteDisease.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = DiseaseException.class)
    @ResponseBody
    public String   deleteDisease(Disease disease){
        return diseaseService.deleteDisease(disease);

    }

    /**
     * 疾病的修改
     * 先查 后改
     */

    @RequestMapping(value = "selectById.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = DiseaseException.class)
    @ResponseBody
    public Disease selectById(Disease disease)throws Exception{
        Disease disease1=diseaseService.selectById(disease);
        return disease1;
    }

    @RequestMapping(value = "updateDisease.do",method = RequestMethod.POST)
    @Transactional(rollbackFor = DiseaseException.class)
    public ServerResponse updateDisease(Disease disease, HttpSession session)throws Exception{
        ServerResponse<Disease> response =diseaseService.updateDisease(disease);
        return response;
    }
}
