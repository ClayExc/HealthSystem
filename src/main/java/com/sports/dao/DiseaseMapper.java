package com.sports.dao;

import com.sports.entity.Disease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiseaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Disease record);

    int insertSelective(Disease record);

    Disease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Disease record);

    int updateByPrimaryKey(Disease record);

    List<Disease> selectName();

    Disease selectGetName(Integer id);

    //查询

    List<Disease> selectAll();

    List<Disease> selectByName(@Param("name") String name);

    // 校验 name不允许重复

    int checkDiseaseName(String DiseaseName);
}