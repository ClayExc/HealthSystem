package com.sports.dao;

import com.sports.entity.SportsEffect;

import java.util.List;

public interface SportsEffectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SportsEffect record);

    int insertSelective(SportsEffect record);

    SportsEffect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportsEffect record);

    int updateByPrimaryKey(SportsEffect record);

    List<SportsEffect> selectAll();

    SportsEffect selectGetName(Integer id);
}