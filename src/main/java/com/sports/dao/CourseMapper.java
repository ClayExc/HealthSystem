package com.sports.dao;

import com.sports.common.ServerResponse;
import com.sports.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    //校验课程是否已经存在

    int chechCourseName(String courseName);

    //查询出全部

    List<Course> selectAll();

    //按照name查询

    List<Course> selectByName( @Param("courseName") String courseName);

    //按照name查询

    List<Course> selectByType( @Param("courseType") String courseType);

    //按照id 获取视频Url

    Course getVideoUrl (Integer id);
}