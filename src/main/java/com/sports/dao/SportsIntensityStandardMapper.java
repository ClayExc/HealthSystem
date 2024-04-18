package com.sports.dao;

import com.sports.entity.SportsIntensityStandard;

import java.util.List;

public interface SportsIntensityStandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SportsIntensityStandard sportsIntensityStandard);

    int insertSelective(SportsIntensityStandard record);

    SportsIntensityStandard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportsIntensityStandard record);

    int updateByPrimaryKey(SportsIntensityStandard record);

    List<SportsIntensityStandard> selectHeartMax();

    SportsIntensityStandard selectMaxId();

    void  insert2(SportsIntensityStandard sportsIntensityStandard);



}