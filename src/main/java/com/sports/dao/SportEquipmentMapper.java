package com.sports.dao;

import com.alibaba.fastjson.JSONArray;
import com.sports.entity.SportEquipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SportEquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SportEquipment record);

    int insertSelective(SportEquipment record);

    SportEquipment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportEquipment record);

    int updateByPrimaryKeyWithBLOBs(SportEquipment record);

    int updateByPrimaryKey(SportEquipment record);

    List<SportEquipment> selectAll();

    List<SportEquipment> selectByName(@Param("name") String name);

    SportEquipment selectGetName(Integer id);

    void addSE(SportEquipment sportEquipment);


    List<SportEquipment> deleteByPrimaryKey1(Integer id);

    SportEquipment selectImageById(Integer id);

    // 校验 name不允许重复

    int checkEquipmentName(String EquipmentName);

}