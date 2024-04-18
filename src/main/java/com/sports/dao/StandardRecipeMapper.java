package com.sports.dao;

import com.sports.entity.StandardRecipe;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface StandardRecipeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StandardRecipe standardRecipe);

    int insertSelective(StandardRecipe record);

    StandardRecipe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StandardRecipe record);

    int updateByPrimaryKey(StandardRecipe record);
    //    获取所有的标准运动处方

    ArrayList selectByAll();

    //查询所有
    List<StandardRecipe> selectAll();

    //根据name获取处方

    List<StandardRecipe> selectByName(String name);

    //根据type获取处方

    List<StandardRecipe> selectByTypeID(Integer id);
    //根据effect获取处方

    List<StandardRecipe> selectByEffectName(@Param("sportsEffectId") String sportsEffectId);
    //添加处方

    void addSR(StandardRecipe standardRecipe);

    //根据器材获取处方

    List<StandardRecipe> selectByEquipment(@Param("sportsMustEquipmentId") Integer sportsMustEquipmentId);

    //根据疾病获取处方

    List<StandardRecipe> selectByDisease(@Param("diseaseId") Integer diseaseId);
    //根据疲劳度等级获取处方

    List<StandardRecipe> selectByFatigueDegree(@Param("FDid") Integer FDid);

    // 校验 name不允许重复

    int checkSRName(String SRName);
}