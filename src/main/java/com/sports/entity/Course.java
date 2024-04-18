package com.sports.entity;

public class Course {
    private Integer id;

    private String name;

    private String type;

    private Double time;

    private String diseaseId;

    private String equipmentId;

    private String genderPreference;

    private String suitableAgeId;

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

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId == null ? null : diseaseId.trim();
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId == null ? null : equipmentId.trim();
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference == null ? null : genderPreference.trim();
    }

    public String getSuitableAgeId() {
        return suitableAgeId;
    }

    public void setSuitableAgeId(String suitableAgeId) {
        this.suitableAgeId = suitableAgeId == null ? null : suitableAgeId.trim();
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