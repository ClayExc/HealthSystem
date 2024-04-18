package com.sports.service;

import com.sports.common.ServerResponse;
import com.sports.entity.RecipeStrengthGroup;

/**
 * @Author: wukaizhen
 * @Date: 2023/11/12 15:09
 * 好好学习，天天向上
 */
public interface RecipeStrengthGroupService {

    //查询全部
    ServerResponse selectAll(RecipeStrengthGroup recipeStrengthGroup);

    //按照name 效果 训练部位 器材 条件查询
    ServerResponse selectByConditions(RecipeStrengthGroup recipeStrengthGroup);

    //按照效果查询

    //按照训练部位查询

    //按照器材查询

}
