package com.sports.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sports.Vo.CourseVo;
import com.sports.common.OSSConfig;
import com.sports.common.ServerResponse;
import com.sports.dao.AgeGroupMapper;
import com.sports.dao.CourseMapper;
import com.sports.dao.DiseaseMapper;
import com.sports.dao.SportEquipmentMapper;
import com.sports.entity.AgeGroup;
import com.sports.entity.Course;
import com.sports.entity.Disease;
import com.sports.entity.SportEquipment;
import com.sports.exception.CourseException;
import com.sports.service.CourseService;
import com.sports.util.OSSBootUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("CourseService")

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private AgeGroupMapper ageGroupMapper;

    @Autowired
    private OSSConfig ossConfig;

    @Autowired
    private DiseaseMapper diseaseMapper;

    @Autowired
    private SportEquipmentMapper sportEquipmentMapper;

    /**
     * 获取年龄段 前端进行回显
     * @return
     */
    @Override
    @Transactional(rollbackFor = CourseException.class)
    public JSONArray ageGroupList() {
        List<AgeGroup> ageGroupList = new ArrayList<>();
        ageGroupList = ageGroupMapper.selectAll();
        return JSONArray.parseArray(JSON.toJSONString(ageGroupList));
    }

    /**
     * 课程的添加
     * @param file
     * @param course
     * @return
     */
    @Override
    @Transactional(rollbackFor = CourseException.class)
    public ServerResponse<String> addCourse(MultipartFile file, Course course) {
        //判断课程名称是否已经存在
        int resultCount = courseMapper.chechCourseName(course.getName());
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("该课程已存在");
        }else {
            //上传文件 到oss 返回链接存入数据库
            String ossFileUrlBoot = null;
            ossFileUrlBoot = OSSBootUtil.upload(ossConfig, file, "sport-course-manage/CourseVideo");
            course.setVideoUrl(ossFileUrlBoot);
            //插入到数据库中
            resultCount = courseMapper.insertSelective(course);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("添加失败");
            }else {
                return ServerResponse.createBySuccessMessage("添加成功");
            }
        }
    }

    /**
     * 课程的查询 按照name
     * @param course
     * @return
     */
    @Override
    @Transactional(rollbackFor = CourseException.class)
    public ServerResponse selectByName(Course course) {
        List<Course> courseList = new ArrayList<>();
        //获取课程name
        String courseName = course.getName();
        List<CourseVo> courseVoList = new ArrayList<>();
        if (courseName == null ){
            //如果不传入name 则查询出全部
            courseList = courseMapper.selectAll();
            for (Course course1: courseList) {
                CourseVo courseVo = new CourseVo();
                List<String> diseaseList = new ArrayList<>();
                List<String> equipmentList = new ArrayList<>();
                List<String> suitableAgeList = new ArrayList<>();
                courseVo.setId(course1.getId());
                courseVo.setType(course1.getType());
                courseVo.setName(course1.getName());
                courseVo.setTime(course1.getTime());
                //禁忌疾病 多选 id转换
                String diseaseId = course1.getDiseaseId();
                List<String> diseaseIdList = new ArrayList<>();
                String[] strArrayDisease = diseaseId.split(",");
                diseaseIdList = Arrays.asList(strArrayDisease);
                for (int i = 0; i < diseaseIdList.size(); i++) {
                    Integer diseaseId2 = Integer.valueOf(diseaseIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(diseaseId2);
                    diseaseList.add(disease.getDiseaseName());
                }
                courseVo.setDiseaseList(diseaseList);
                //所需器材 多选 id转换
                String equipmentId = course1.getEquipmentId();
                List<String> equipmentIdList = new ArrayList<>();
                String[] strArrayEquipment = equipmentId.split(",");
                equipmentIdList = Arrays.asList(strArrayEquipment);
                for (int i = 0; i < equipmentIdList.size() ; i++) {
                    Integer equipmentId2 = Integer.valueOf(equipmentIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(equipmentId2);
                    equipmentList.add(sportEquipment.getEquipmentName());
                }
                courseVo.setEquipmentList(equipmentList);
                courseVo.setGenderPreference(course1.getGenderPreference());
                //适合的年龄范围 多选 id转换
                String ageId = course1.getSuitableAgeId();
                List<String> ageIdList = new ArrayList<>();
                String [] strArrayAge = ageId.split(",");
                ageIdList = Arrays.asList(strArrayAge);
                for (int i = 0; i < ageIdList.size() ; i++) {
                    Integer ageId2 = Integer.valueOf(ageIdList.get(i));
                    AgeGroup ageGroup = new AgeGroup();
                    ageGroup = ageGroupMapper.getNameById(ageId2);
                    suitableAgeList.add(ageGroup.getGroupName());
                }
                courseVo.setSuitableAgeList(suitableAgeList);
                courseVo.setNotice(course1.getNotice());
                courseVo.setCourseDescription(course1.getCourseDescription());
                courseVo.setPracticeNumber(course1.getPracticeNumber());
                courseVo.setVideoUrl(course1.getVideoUrl());
                courseVoList.add(courseVo);
            }
        }else {
            //否则 按照name查询
            courseList = courseMapper.selectByName(courseName);
            for (Course course1: courseList) {
                CourseVo courseVo = new CourseVo();
                List<String> diseaseList = new ArrayList<>();
                List<String> suitableAgeList = new ArrayList<>();
                List<String> equipmentList = new ArrayList<>();
                courseVo.setId(course1.getId());
                courseVo.setType(course1.getType());
                courseVo.setTime(course1.getTime());
                courseVo.setName(course1.getName());
                //禁忌疾病 多选 id转换
                String diseaseId = course1.getDiseaseId();
                List<String> diseaseIdList = new ArrayList<>();
                String[] strArrayDisease = diseaseId.split(",");
                diseaseIdList = Arrays.asList(strArrayDisease);
                for (int i = 0; i < diseaseIdList.size(); i++) {
                    Integer diseaseId2 = Integer.valueOf(diseaseIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(diseaseId2);
                    diseaseList.add(disease.getDiseaseName());
                }
                courseVo.setDiseaseList(diseaseList);
                //所需器材 多选 id转换
                String equipmentId = course1.getEquipmentId();
                List<String> equipmentIdList = new ArrayList<>();
                String[] strArrayEquipment = equipmentId.split(",");
                equipmentIdList = Arrays.asList(strArrayEquipment);
                for (int i = 0; i < equipmentIdList.size() ; i++) {
                    Integer equipmentId2 = Integer.valueOf(equipmentIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(equipmentId2);
                    equipmentList.add(sportEquipment.getEquipmentName());
                }
                courseVo.setEquipmentList(equipmentList);
                courseVo.setGenderPreference(course1.getGenderPreference());
                //适合的年龄范围 多选 id转换
                String ageId = course1.getSuitableAgeId();
                List<String> ageIdList = new ArrayList<>();
                String [] strArrayAge = ageId.split(",");
                ageIdList = Arrays.asList(strArrayAge);
                for (int i = 0; i < ageIdList.size() ; i++) {
                    Integer ageId2 = Integer.valueOf(ageIdList.get(i));
                    AgeGroup ageGroup = new AgeGroup();
                    ageGroup = ageGroupMapper.getNameById(ageId2);
                    suitableAgeList.add(ageGroup.getGroupName());
                }
                courseVo.setSuitableAgeList(suitableAgeList);
                courseVo.setPracticeNumber(course1.getPracticeNumber());
                courseVo.setNotice(course1.getNotice());
                courseVo.setCourseDescription(course1.getCourseDescription());
                courseVo.setVideoUrl(course1.getVideoUrl());
                courseVoList.add(courseVo);
            }
        }
        return ServerResponse.SUCCESS(courseVoList);
    }

    @Override
    @Transactional(rollbackFor = CourseException.class)
    public ServerResponse getVideoUrl(Course course) {
        //获取id
        Integer courseId = course.getId();
        if (courseId == null){
            return ServerResponse.createByErrorMessage("id未获取到");

        }
        course = courseMapper.getVideoUrl(courseId);
        return ServerResponse.SUCCESS(course);
    }

    @Override
    @Transactional(rollbackFor = CourseException.class)
    public ServerResponse selectByType(Course course) {
        List<Course> courseList = new ArrayList<>();
        List<CourseVo> courseVoList = new ArrayList<>();
        //获取type
        String courseType = course.getType();
        if(courseType == null || courseType.isEmpty()){
            //如果type为空 则查询出全部
            courseList = courseMapper.selectAll();
            for (Course course1: courseList) {
                CourseVo courseVo = new CourseVo();
                courseVo.setId(course1.getId());
                courseVo.setName(course1.getName());
                courseVo.setType(course1.getType());
                courseVo.setTime(course1.getTime());
                //禁忌疾病 多选 id转换
                List<String> diseaseList = new ArrayList<>();
                //获得疾病id
                String diseaseId = course1.getDiseaseId();
                //采用逗号分隔 然后把id存入数组中
                String[] strArrayDisease = diseaseId.split(",");
                // 存入list中
                List<String> diseaseIdList = new ArrayList<>();
                diseaseIdList = Arrays.asList(strArrayDisease);
                //遍历list中的id 然后输出对应的name
                for (int i = 0; i < diseaseIdList.size(); i++) {
                    Integer diseaseId2 = Integer.valueOf(diseaseIdList.get(i));
                    Disease disease = new Disease();
                    //根据id获取name
                    disease=diseaseMapper.selectGetName(diseaseId2);
                    //那name添加到疾病列表中
                    diseaseList.add(disease.getDiseaseName());
                }
                //vo去获取name
                courseVo.setDiseaseList(diseaseList);
                //所需器材 多选 id转换
                List<String> equipmentList = new ArrayList<>();
                String equipmentId = course1.getEquipmentId();
                String[] strArrayEquipment = equipmentId.split(",");
                List<String> equipmentIdList = new ArrayList<>();
                equipmentIdList = Arrays.asList(strArrayEquipment);
                for (int i = 0; i < equipmentIdList.size() ; i++) {
                    Integer equipmentId2 = Integer.valueOf(equipmentIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(equipmentId2);
                    equipmentList.add(sportEquipment.getEquipmentName());
                }
                courseVo.setEquipmentList(equipmentList);
                courseVo.setGenderPreference(course1.getGenderPreference());
                //适合的年龄范围 多选 id转换
                List<String> suitableAgeList = new ArrayList<>();
                String ageId = course1.getSuitableAgeId();
                String [] strArrayAge = ageId.split(",");
                List<String> ageIdList = new ArrayList<>();
                ageIdList = Arrays.asList(strArrayAge);
                for (int i = 0; i < ageIdList.size() ; i++) {
                    Integer ageId2 = Integer.valueOf(ageIdList.get(i));
                    AgeGroup ageGroup = new AgeGroup();
                    ageGroup = ageGroupMapper.getNameById(ageId2);
                    suitableAgeList.add(ageGroup.getGroupName());
                }
                courseVo.setVideoUrl(course1.getVideoUrl());
                courseVo.setSuitableAgeList(suitableAgeList);
                courseVo.setPracticeNumber(course1.getPracticeNumber());
                courseVo.setNotice(course1.getNotice());
                courseVo.setCourseDescription(course1.getCourseDescription());
                courseVoList.add(courseVo);
            }
        }else{
            courseList = courseMapper.selectByType(courseType);
            for (Course course1: courseList) {
                CourseVo courseVo = new CourseVo();
                courseVo.setId(course1.getId());
                courseVo.setType(course1.getType());
                courseVo.setName(course1.getName());
                courseVo.setTime(course1.getTime());
                //禁忌疾病 多选 id转换
                List<String> diseaseList = new ArrayList<>();
                //获得疾病id
                String diseaseId = course1.getDiseaseId();
                //采用逗号分隔 然后把id存入数组中
                String[] strArrayDisease = diseaseId.split(",");
                // 存入list中
                List<String> diseaseIdList = new ArrayList<>();
                diseaseIdList = Arrays.asList(strArrayDisease);
                //遍历list中的id 然后输出对应的name
                for (int i = 0; i < diseaseIdList.size(); i++) {
                    Integer diseaseId2 = Integer.valueOf(diseaseIdList.get(i));
                    Disease disease = new Disease();
                    //根据id获取name
                    disease=diseaseMapper.selectGetName(diseaseId2);
                    //那name添加到疾病列表中
                    diseaseList.add(disease.getDiseaseName());
                }
                //vo去获取name
                courseVo.setDiseaseList(diseaseList);
                //所需器材 多选 id转换
                List<String> equipmentList = new ArrayList<>();
                String equipmentId = course1.getEquipmentId();
                String[] strArrayEquipment = equipmentId.split(",");
                List<String> equipmentIdList = new ArrayList<>();
                equipmentIdList = Arrays.asList(strArrayEquipment);
                for (int i = 0; i < equipmentIdList.size() ; i++) {
                    Integer equipmentId2 = Integer.valueOf(equipmentIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(equipmentId2);
                    equipmentList.add(sportEquipment.getEquipmentName());
                }
                courseVo.setEquipmentList(equipmentList);
                courseVo.setGenderPreference(course1.getGenderPreference());
                //适合的年龄范围 多选 id转换
                List<String> suitableAgeList = new ArrayList<>();
                String ageId = course1.getSuitableAgeId();
                String [] strArrayAge = ageId.split(",");
                List<String> ageIdList = new ArrayList<>();
                ageIdList = Arrays.asList(strArrayAge);
                for (int i = 0; i < ageIdList.size() ; i++) {
                    Integer ageId2 = Integer.valueOf(ageIdList.get(i));
                    AgeGroup ageGroup = new AgeGroup();
                    ageGroup = ageGroupMapper.getNameById(ageId2);
                    suitableAgeList.add(ageGroup.getGroupName());
                }
                courseVo.setVideoUrl(course1.getVideoUrl());
                courseVo.setSuitableAgeList(suitableAgeList);
                courseVo.setPracticeNumber(course1.getPracticeNumber());
                courseVo.setNotice(course1.getNotice());
                courseVo.setCourseDescription(course1.getCourseDescription());
                courseVoList.add(courseVo);
            }
        }
        return ServerResponse.SUCCESS(courseVoList);
    }

    @Override
    @Transactional(rollbackFor = CourseException.class)
    public String deleteCourse(Course course) {
        Integer courseId = course.getId();
        if(courseId == null){
            return "id未获取";
        }
        courseMapper.deleteByPrimaryKey(courseId);
        return "删除成功";
    }

}
