package com.sports.dao;

import com.sports.entity.AgeGroup;

import java.util.List;

public interface AgeGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgeGroup record);

    int insertSelective(AgeGroup record);

    AgeGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgeGroup record);

    int updateByPrimaryKey(AgeGroup record);

    //获取年龄段

    List<AgeGroup> selectAll();

    //获取年龄段name

    AgeGroup getNameById(Integer id);

}