package com.sports.dao;

import com.sports.entity.RecipeStrengthGroup;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Size;
import java.util.List;

public interface RecipeStrengthGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecipeStrengthGroup record);

    int insertSelective(RecipeStrengthGroup record);

    RecipeStrengthGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecipeStrengthGroup record);

    int updateByPrimaryKey(RecipeStrengthGroup record);

    //查询全部
    List<RecipeStrengthGroup> selectAll();

    //按照name查询 模糊查询
    List<RecipeStrengthGroup> selectByName(@Param("recipeName") String recipeName);

    //按照效果查询
    List<RecipeStrengthGroup> selectByEffect(@Param("recipeEffect")String recipeEffect);

    //按照训练部位查询
    List<RecipeStrengthGroup> selectByMuscles(@Param("musclesTrained")String musclesTrained);

    //按照器材查询
    List<RecipeStrengthGroup> selectByEquipment(@Param("equipment") String equipment);

    //按照name和效果查询
    List<RecipeStrengthGroup> selectByNameAndEffect(@Param("recipeName")String recipeName,@Param("recipeEffect")String recipeEffect);

    //按照name和训练部位查询
    List<RecipeStrengthGroup> selectByNameAndMuscles(@Param("recipeName")String recipeName,@Param("musclesTrained")String musclesTrained);

    //按照name和器材查询
    List<RecipeStrengthGroup> selectByNameAndEquipment(@Param("recipeName")String recipeName,@Param("equipment") String equipment);

    //按照效果和训练部位查询
    List<RecipeStrengthGroup> selectByEffectAndMuscles(@Param("recipeEffect")String recipeEffect,@Param("musclesTrained")String musclesTrained);

    //按照效果和器材查询
    List<RecipeStrengthGroup> selectByEffectAndEquipment(@Param("recipeEffect")String recipeEffect,@Param("equipment") String equipment);

    //按照训练部位和器材查询
    List<RecipeStrengthGroup> selectMusclesAndEquipment(@Param("musclesTrained")String musclesTrained,@Param("equipment") String equipment);

    //按照name 效果 和训练部位 查询
    List<RecipeStrengthGroup> selectByNameAndEffectAndMuscles(
            @Param("recipeName")String recipeName, @Param("recipeEffect")String recipeEffect,
            @Param("musclesTrained")String musclesTrained);

    //按照name 效果和器材 查询
    List<RecipeStrengthGroup> selectByNameAndEffectAndEquipment(
            @Param("recipeName")String recipeName, @Param("recipeEffect")String recipeEffect,
            @Param("equipment") String equipment);

    //按照name 训练部位 和器材 查询
    List<RecipeStrengthGroup> selectByNameAndMusclesAndEquipment(
            @Param("recipeName")String recipeName, @Param("musclesTrained")String musclesTrained,
            @Param("equipment") String equipment);

    //按照效果 训练部位 和器材 查询
    List<RecipeStrengthGroup> selectByEffectAndMusclesAndEquipment(
            @Param("recipeEffect")String recipeEffect, @Param("musclesTrained")String musclesTrained,
            @Param("equipment") String equipment);

    //按照name 效果 训练部位 器材 查询
    List<RecipeStrengthGroup> selectByNameAndEffectAndMusclesAndEquipment(
            @Param("recipeName")String recipeName,@Param("recipeEffect")String recipeEffect,
            @Param("musclesTrained")String musclesTrained, @Param("equipment") String equipment);
}