package com.sports.controller;

import com.sports.common.ServerResponse;
import com.sports.entity.RecipeGroup;
import com.sports.service.RecipeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：wukaizhen
 * @Date:2023/10/28 10:41
 * 好好学习，天天向上
 */
@RestController
@RequestMapping("/recipegroup/")
@CrossOrigin(origins="*",maxAge=3600)
public class RecipeGroupController {

    @Autowired
    RecipeGroupService recipeGroupService;

    /**
     * 根据name查询
     * 修改接口 完成name 和 年龄段的任意切换查询
     * @param recipeGroup
     * @return
     */
    @RequestMapping(value = "selectRecipeByName.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectRecipeByName(RecipeGroup recipeGroup){
        return recipeGroupService.selectRecipeByName(recipeGroup);
    }

    /**
     * 根据效果查询
     * @param recipeGroup
     * @return
     */
    @RequestMapping(value = "selectRecipeByEffect.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectRecipeByEffect(RecipeGroup recipeGroup){
        return recipeGroupService.selectRecipeByEffect(recipeGroup);
    }

    /**
     * 根据年龄段查询
     * @param recipeGroup
     * @return
     */
    @RequestMapping(value = "selectRecipeByAge.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectRecipeByAge(RecipeGroup recipeGroup){
        return recipeGroupService.selectRecipeByAge(recipeGroup);
    }

    /**
     * 多条件查询
     * @param recipeGroup
     * @return
     */
    @RequestMapping(value = "selectRecipe.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectRecipe(RecipeGroup recipeGroup){
        return recipeGroupService.selectRecipe(recipeGroup);
    }


}
