package com.sports.dao;

import com.sports.entity.RunData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RunDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RunData record);

    int insertSelective(RunData record);

    RunData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RunData record);

    int updateByPrimaryKey(RunData record);

    RunData selectOneByUserId (@Param("userId") Integer userId );

    List<RunData> selectByRange (@Param("userId") Integer userId, @Param("startTime") String startTime, @Param("endTime")  String endTime);

    //根据id查询最新的数据 返回List类型

    List<RunData> selectOneByUserId2(@Param("userId") Integer userId );

    List<RunData> selectByTime(@Param("userId") Integer userId,@Param("recordTime") String recordTime );

    RunData selectRateByTime (@Param("userId") Integer userId,@Param("recordTime") String recordTime );

}