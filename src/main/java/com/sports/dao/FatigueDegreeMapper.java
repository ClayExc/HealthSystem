package com.sports.dao;

import com.sports.entity.FatigueDegree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FatigueDegreeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FatigueDegree record);

    int insertSelective(FatigueDegree record);

    FatigueDegree selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FatigueDegree record);

    int updateByPrimaryKey(FatigueDegree record);

    List<FatigueDegree> selectName();

    //检测数据库中是否存在该疲劳度等级名

    int checkFDname(String FDname);

    List<FatigueDegree> selectAll();

    List<FatigueDegree> selectByName(@Param("name") String name);
}