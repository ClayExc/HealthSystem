package com.sports.service.impl;

import com.sports.common.ServerResponse;
import com.sports.dao.RecipeGroupMapper;
import com.sports.entity.RecipeGroup;
import com.sports.exception.RecipeGroupException;
import com.sports.service.RecipeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：wukaizhen
 * @Date:2023/10/28 10:41
 * 好好学习，天天向上
 */

@Service("RecipeGroupService")
public class RecipeGroupServiceImpl implements RecipeGroupService {

    @Autowired
    RecipeGroupMapper recipeGroupMapper;
    /**
     * 查询 如果传入name 按照name查询 没有name 查询全部
     * 修改接口 完成name 和 年龄段的任意切换查询
     * @param recipeGroup
     * @return
     */
    @Override
    @Transactional(rollbackFor = RecipeGroupException.class)
    public ServerResponse selectRecipeByName(RecipeGroup recipeGroup) {
        List<RecipeGroup> recipeGroupList = new ArrayList<>();
        //获取name
        String recipeName = recipeGroup.getRecipeName();
        //获取年龄段
        String ageGroup = recipeGroup.getAgeGroup();
        if (recipeName == null&& ageGroup == null){
            //查询全部
            recipeGroupList = recipeGroupMapper.selectAll();
        }else if (recipeName != null&& ageGroup == null){
            //按照name查询
            recipeGroupList = recipeGroupMapper.selectByName(recipeName);
        }else if (recipeName == null&& ageGroup != null){
            //按照年龄段查询
            recipeGroupList = recipeGroupMapper.selectByAge(ageGroup);
        }else {
            //按照name 和 年龄段查询
            recipeGroupList = recipeGroupMapper.selectByNameAndAge(recipeName,ageGroup);
        }
        return ServerResponse.SUCCESS(recipeGroupList);

    }

    /**
     * 按照效果查询
     * @param recipeGroup
     * @return
     */
    @Override
    @Transactional(rollbackFor = RecipeGroupException.class)
    public ServerResponse selectRecipeByEffect(RecipeGroup recipeGroup) {
        List<RecipeGroup> recipeGroupList = new ArrayList<>();
        //获取效果
        String effect = recipeGroup.getEffect();
        if (effect == null){
            //查询全部
            recipeGroupList = recipeGroupMapper.selectAll();
        }else {
            //按照效果查询
            recipeGroupList = recipeGroupMapper.selectByEffect(effect);
        }
        return ServerResponse.SUCCESS(recipeGroupList);
    }

    @Override
    @Transactional(rollbackFor = RecipeGroupException.class)
    public ServerResponse selectRecipeByAge(RecipeGroup recipeGroup) {
        List<RecipeGroup> recipeGroupList = new ArrayList<>();
        //获取年龄段
        String ageGroup = recipeGroup.getAgeGroup();
        if (ageGroup == null){
            //查询全部
            recipeGroupList = recipeGroupMapper.selectAll();
        }else {
            //按照年龄段查询
            recipeGroupList = recipeGroupMapper.selectByAge(ageGroup);
        }
        return ServerResponse.SUCCESS(recipeGroupList);
    }

    /**
     * 多条件 动态查询
     * @param recipeGroup
     * @return
     */
    @Override
    @Transactional(rollbackFor = RecipeGroupException.class)
    public ServerResponse selectRecipe(RecipeGroup recipeGroup) {
        List<RecipeGroup> recipeGroupList = new ArrayList<>();
        String recipeName = recipeGroup.getRecipeName();
        String recipeEffect = recipeGroup.getEffect();
        String ageGroup = recipeGroup.getAgeGroup();
        if (ageGroup == null&&recipeEffect == null&&recipeName == null){

            //查询全部
            recipeGroupList = recipeGroupMapper.selectAll();
        }else {
            //按照条件查询
            recipeGroupList = recipeGroupMapper.selectRecipe(recipeName,recipeEffect,ageGroup);
        }

        return ServerResponse.SUCCESS(recipeGroupList);
    }

    /**
     * todo 删除
     */
}
