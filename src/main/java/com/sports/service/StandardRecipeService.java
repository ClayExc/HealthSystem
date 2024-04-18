package com.sports.service;


import com.alibaba.fastjson.JSONArray;
import com.sports.Vo.StandarRecipeVo2;
import com.sports.common.ServerResponse;
import com.sports.entity.SportsIntensityStandard;
import com.sports.entity.StandardRecipe;

public interface StandardRecipeService   {

    //效果 effect

    JSONArray goalList();
    //类型 name

    JSONArray interestList();

    //器材 多选 name

    JSONArray equipmentiList();
    //疲劳度等级 name

    JSONArray fatigueList();
    //禁忌疾病 name

    JSONArray diseaseList();

    //查询全部 和 根据name查询

    JSONArray selectSR(StandardRecipe standardRecipe);

    //查询全部 和 根据运动类型查询

    JSONArray selectByTypeId(StandardRecipe standardRecipe);

    //查询全部 和 根据运动效果模糊查询

    JSONArray selectByEffectId(StandardRecipe standardRecipe);
    //删除未修改

//    int deleteSR(Integer id);

    //删除修改

    void deleteSR1(StandardRecipe standardRecipe);

    //更新

    //更新第一步 先查出来进行返回
    JSONArray selectSRyId(StandarRecipeVo2 standarRecipeVo2, StandardRecipe standardRecipe);

    ServerResponse<StandardRecipe> updateInformation(StandardRecipe standardRecipe, SportsIntensityStandard sportsIntensityStandard);

}
