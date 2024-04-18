package com.sports.controller;


import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.entity.Course;
import com.sports.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/course/")
@CrossOrigin(origins="*",maxAge=3600)
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    /**
     * 获取年龄段 进行回显
     * @return
     */
    @RequestMapping(value = "ageGroupList.do",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray ageGroupList(){
        JSONArray jsonArray = courseService.ageGroupList();
        return jsonArray;
    }

    /**
     * 课程的添加
     * @param file
     * @param course
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addCourse.do", method = RequestMethod.POST)
    public ServerResponse addCourse(@RequestParam(value = "file") MultipartFile file, Course course)throws Exception{
        return  courseService.addCourse(file, course);
    }

    /**
     * 课程的查询 根据name
     * @param course
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectCourseByName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectCourseByName(Course course)throws Exception{
        return courseService.selectByName(course);

    }

    @RequestMapping(value = "selectCourseByType.do",method =RequestMethod.POST )
    @ResponseBody
    public ServerResponse selectCourseByType(Course course) throws Exception{
        return courseService.selectByType(course);
    }
    /**
     *  根据id 获取视频url
     * @param course
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getVideoUrl.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getVideoUrl(Course course)throws Exception{
        return courseService.getVideoUrl(course);
    }

    /**
     * 课程的删除
     * @param course
     * @return
     */
    @RequestMapping(value = "deleteCourseById.do",method = RequestMethod.POST)
    @ResponseBody
    public String deleteCourse (Course course){
        return courseService.deleteCourse(course);
    }
}
