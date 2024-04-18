package com.sports.controller;

import com.sports.common.ServerResponse;
import com.sports.entity.RecipeStrengthGroup;
import com.sports.service.RecipeStrengthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wukaizhen
 * @Date: 2023/11/12 15:10
 * 好好学习，天天向上
 */

@RestController
@RequestMapping("/recipeStrength/")
@CrossOrigin(origins="*",maxAge=3600)
public class RecipeStrengthGroupController {

    @Autowired
    RecipeStrengthGroupService recipeStrengthGroupService;

    /**
     * 查询全部
     * @param recipeStrengthGroup
     * @return
     */
    @RequestMapping(value = "selectRecipeStrengthAll",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectRecipeStrengthAll(RecipeStrengthGroup recipeStrengthGroup){
        return recipeStrengthGroupService.selectAll(recipeStrengthGroup);
    }


    /**
     * 多条件查询
     * @param recipeStrengthGroup
     * @return
     */
    @RequestMapping(value = "selectByConditions",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectByConditions(RecipeStrengthGroup recipeStrengthGroup){
        return recipeStrengthGroupService.selectByConditions(recipeStrengthGroup);
    }

}
