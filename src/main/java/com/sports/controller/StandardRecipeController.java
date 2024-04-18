package com.sports.controller;

import com.alibaba.fastjson.JSONArray;
import com.sports.Vo.StandarRecipeVo2;
import com.sports.common.Const;
import com.sports.common.ServerResponse;
import com.sports.entity.SportsIntensityStandard;
import com.sports.entity.StandardRecipe;
import com.sports.exception.StandardRecipeException;
import com.sports.service.StandardRecipeService;
import com.sports.service.impl.StandardRecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/standardRecipe/")
@CrossOrigin(origins="*",maxAge=3600)
public class StandardRecipeController {

    @Autowired
    StandardRecipeServiceImpl standardRecipeService;

    /**
     * 处方的添加
     * @param standardRecipe
     */
    @RequestMapping(value = "addSR.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    public ServerResponse addSR(StandardRecipe standardRecipe, SportsIntensityStandard sportsIntensityStandard){
        return standardRecipeService.addSR(standardRecipe,sportsIntensityStandard);
    }

    /**
     * 处方的查询 根据name
     */
    @RequestMapping(value = "selectSR.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray selectSR(StandardRecipe  standardRecipe){
        JSONArray jsonArray=standardRecipeService.selectSR(standardRecipe);
        return jsonArray;
    }

    /**
     * 处方的查询 根据type
     */
    @RequestMapping(value = "selectSRByType.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray selectSRByType(StandardRecipe  standardRecipe){
        JSONArray jsonArray=standardRecipeService.selectByTypeId(standardRecipe);
        return jsonArray;
    }

    /**
     * 处方的查询 根据type
     */
    @RequestMapping(value = "selectSRByEffect.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray selectSRByEffect(StandardRecipe  standardRecipe){
        JSONArray jsonArray=standardRecipeService.selectByEffectId(standardRecipe);
        return jsonArray;
    }

    /**
     * 处方的删除 根据id删除
     */

    @RequestMapping(value = "deleteSR.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    public void addSR(StandardRecipe standardRecipe){
        standardRecipeService.deleteSR1(standardRecipe);
    }
    /**
     * 处方的修改 step1
     */
    @RequestMapping(value = "selectSRById.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray selectSRById(StandarRecipeVo2 standarRecipeVo2, StandardRecipe standardRecipe){
        JSONArray jsonArray=standardRecipeService.selectSRyId(standarRecipeVo2,standardRecipe);
        return jsonArray;
    }

    /**
     * 处方的修改 step2
     */

    @RequestMapping(value = "updateSR.do",method = RequestMethod.POST)
    @Transactional(rollbackFor = StandardRecipeException.class)
    public ServerResponse updateSR(HttpSession session, StandardRecipe standardRecipe,SportsIntensityStandard sportsIntensityStandard){
        ServerResponse<StandardRecipe> response =standardRecipeService.updateInformation(standardRecipe,sportsIntensityStandard);
        return response;
    }

    /**
     * 运动效果的回显
     */

    // 运动效果 回显给前端 前端给我id

    @RequestMapping(value = "goalList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray goalListReturn(){
        JSONArray jsonArray = standardRecipeService.goalList();
        return jsonArray;
    }

    //类型

    @RequestMapping(value = "interestList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray interestListReturn(){
        JSONArray jsonArray = standardRecipeService.interestList();
        return jsonArray;
    }

    //器材
    @RequestMapping(value = "equipmentList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray equipmentListReturn(){
        JSONArray jsonArray = standardRecipeService.equipmentiList();
        return jsonArray;
    }
    //疲劳度等级

    @RequestMapping(value = "fatigueList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray fatigueListReturn(){
        JSONArray jsonArray = standardRecipeService.fatigueList();
        return jsonArray;
    }
    //禁忌疾病等级

    @RequestMapping(value = "diseaseList.do",method = RequestMethod.GET)
    @Transactional(rollbackFor = StandardRecipeException.class)
    @ResponseBody
    public JSONArray diseaseListReturn(){
        JSONArray jsonArray = standardRecipeService.diseaseList();
        return jsonArray;
    }
}
