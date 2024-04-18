package com.sports.dao;

import com.sports.entity.SportAbility;

public interface SportAbilityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SportAbility record);

    int insertSelective(SportAbility record);

    SportAbility selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportAbility record);

    int updateByPrimaryKey(SportAbility record);
    //    通过userid查询是否存在该用户的运动能力评测
    SportAbility selectByUser_id(int user_id);
}