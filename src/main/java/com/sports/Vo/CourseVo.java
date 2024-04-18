package com.sports.Vo;

import java.util.List;

public class CourseVo {
    private Integer id;

    private String name;

    private String type;

    private Double time;

    private List<String> diseaseList;

    private List<String> equipmentList;

    private String genderPreference;

    private List<String> suitableAgeList;

    private Integer practiceNumber;

    private String notice;

    private String courseDescription;

    private String videoUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public List<String> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<String> diseaseList) {
        this.diseaseList = diseaseList ;
    }

    public List<String> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<String> equipmentList) {
        this.equipmentList = equipmentList ;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference == null ? null : genderPreference.trim();
    }

    public List<String> getSuitableAgeList() {
        return suitableAgeList;
    }

    public void setSuitableAgeList(List<String> suitableAgeList) {
        this.suitableAgeList = suitableAgeList ;
    }

    public Integer getPracticeNumber() {
        return practiceNumber;
    }

    public void setPracticeNumber(Integer practiceNumber) {
        this.practiceNumber = practiceNumber;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription == null ? null : courseDescription.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }
}
