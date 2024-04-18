package com.sports.dao;

import com.sports.entity.SportTypes;

import java.util.List;

public interface SportTypesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SportTypes record);

    int insertSelective(SportTypes record);

    SportTypes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportTypes record);

    int updateByPrimaryKey(SportTypes record);
    List<SportTypes> selectAll();
}