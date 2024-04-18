package com.sports.dao;

import com.sports.entity.UserRecipe;

public interface UserRecipeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRecipe record);

    int insertSelective(UserRecipe record);

    UserRecipe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRecipe record);

    int updateByPrimaryKey(UserRecipe record);

    UserRecipe selectByUserId(int  userid);
}