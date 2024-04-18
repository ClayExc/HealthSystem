package com.sports.service.impl;

import com.sports.common.ServerResponse;
import com.sports.dao.RecipeStrengthGroupMapper;
import com.sports.entity.RecipeStrengthGroup;
import com.sports.exception.RecipeStrengthGroupException;
import com.sports.service.RecipeStrengthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wukaizhen
 * @Date: 2023/11/12 15:09
 * 好好学习，天天向上
 */

@Service("RecipeStrengthGroupService")
public class RecipeStrengthGroupServiceImpl implements RecipeStrengthGroupService {

    @Autowired
    RecipeStrengthGroupMapper recipeStrengthGroupMapper;


    /**
     *查询全部接口
     * @param recipeStrengthGroup
     * @return
     */
    @Override
    @Transactional(rollbackFor = RecipeStrengthGroupException.class)
    public ServerResponse selectAll(RecipeStrengthGroup recipeStrengthGroup) {
        List<RecipeStrengthGroup> recipeStrengthGroupList = new ArrayList<>();
        recipeStrengthGroupList = recipeStrengthGroupMapper.selectAll();
        return ServerResponse.SUCCESS(recipeStrengthGroupList);
    }

    /**
     * 按照条件进行多条件查询
     * @param recipeStrengthGroup 传入实体类
     * @return 返回list数据
     */
    @Override
    public ServerResponse selectByConditions(RecipeStrengthGroup recipeStrengthGroup) {
        List<RecipeStrengthGroup> recipeStrengthGroupList = new ArrayList<>();
        //获取name
        String recipeName = recipeStrengthGroup.getRecipeName();
        //获取效果
        String effect = recipeStrengthGroup.getEffect();
        //获取训练部位
        String musclesTrained = recipeStrengthGroup.getMusclesTrained();
        //获取器材
        String equipment = recipeStrengthGroup.getEquipment();
        if (recipeName == null && effect == null && musclesTrained == null && equipment == null){
            //如果全部为空 则查询出全部
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectAll();
        }else if (recipeName != null && effect == null && musclesTrained == null && equipment == null){
            //如果name不为空 其他为空 则按照name 查询 模糊查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByName(recipeName);
        }else if (recipeName == null && effect != null && musclesTrained == null && equipment == null){
            //如果 effect 不为空， 其他为空 则按照effect 查询 模糊查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByEffect(effect);
        }else if (recipeName == null && effect == null && musclesTrained != null && equipment == null){
            //如果 训练部位 不为空， 其他为空 则按照 训练部位 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByMuscles(musclesTrained);
        }else if (recipeName == null && effect == null && musclesTrained == null && equipment != null){
            //如果 器材 不为空， 其他为空 则按照 器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByEquipment(equipment);
        }else if (recipeName != null && effect != null && musclesTrained == null && equipment == null) {
            //如果 name和效果 不为空， 其他为空 则按照 name和效果 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndEffect(recipeName, effect);
        }else if (recipeName != null && effect == null && musclesTrained != null && equipment == null) {
            //如果 name和训练部位 不为空， 其他为空 则按照 name和训练部位 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndMuscles(recipeName, musclesTrained);
        }else if (recipeName != null && effect == null && musclesTrained == null && equipment != null) {
            //如果 name和器材 不为空， 其他为空 则按照 name和器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndEquipment(recipeName, equipment);
        }else if (recipeName == null && effect != null && musclesTrained != null && equipment == null) {
            //如果 效果和训练部位 不为空， 其他为空 则按照 效果和训练部位 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByEffectAndMuscles(effect, musclesTrained);
        }else if (recipeName == null && effect != null && musclesTrained == null && equipment != null) {
            //如果 效果和器材 不为空， 其他为空 则按照 效果和器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByEffectAndEquipment(effect, equipment);
        }else if (recipeName == null && effect == null && musclesTrained != null && equipment != null) {
            //如果 训练部位和器材 不为空， 其他为空 则按照 训练部位和器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectMusclesAndEquipment(musclesTrained, equipment);
        }else if (recipeName != null && effect != null && musclesTrained != null && equipment == null) {
            //如果 name和效果和训练部位 不为空， 其他为空 则按照 name和效果和训练部位 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndEffectAndMuscles(recipeName, effect,musclesTrained);
        }else if (recipeName != null && effect != null && musclesTrained == null && equipment != null) {
            //如果 name和效果和器材 不为空， 其他为空 则按照 name和效果和器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndEffectAndEquipment(recipeName, effect,equipment);
        }else if (recipeName != null && effect == null && musclesTrained != null && equipment != null) {
            //如果 name和训练部位和器材 不为空， 其他为空 则按照 name和训练部位和器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndMusclesAndEquipment(recipeName, musclesTrained,equipment);
        }else if (recipeName == null && effect != null && musclesTrained != null && equipment != null) {
            //如果 效果和训练部位和器材 不为空， 其他为空 则按照 效果和训练部位和器材 查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByEffectAndMusclesAndEquipment(effect, musclesTrained,equipment);
        }else{
            //如果 都 不为空， 则按照 name 效果 训练部位和器材 四个条件查询
            recipeStrengthGroupList = recipeStrengthGroupMapper.selectByNameAndEffectAndMusclesAndEquipment(recipeName,effect, musclesTrained,equipment);
        }
        return ServerResponse.SUCCESS(recipeStrengthGroupList);
    }
}
