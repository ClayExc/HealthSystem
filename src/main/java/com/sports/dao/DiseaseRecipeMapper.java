package com.sports.dao;

import com.sports.entity.DiseaseRecipe;

public interface DiseaseRecipeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiseaseRecipe record);

    int insertSelective(DiseaseRecipe record);

    DiseaseRecipe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiseaseRecipe record);

    int updateByPrimaryKey(DiseaseRecipe record);
}