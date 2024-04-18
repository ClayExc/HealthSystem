package com.sports.dao;

import com.sports.entity.FitnessVenues;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FitnessVenuesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FitnessVenues record);

    int insertSelective(FitnessVenues record);

    FitnessVenues selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FitnessVenues record);

    int updateByPrimaryKey(FitnessVenues record);


    //校验 name 不允许重复

    int checkFitnessName(String FitnessName);

    //查询出所有

    List<FitnessVenues> selectAll();

    //按照name查询

    List<FitnessVenues> selectByName(@Param("fitnessName") String fitnessName);

    //按照type查询

    List<FitnessVenues> selectByType(String type);

    //按照typeValue查询

    List<FitnessVenues> selectByTypeValue(String typeValue);

    //根据id获取图片image

    FitnessVenues getImageUrl(Integer id);
}