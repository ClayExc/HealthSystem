package com.sports.entity;

import java.util.Date;

public class IndividuationRecipe {
    private Integer id;

    private String name;

    private Integer userid;

    private Integer standardId;

    private Integer sportsEffectId;

    private Integer sportsTypeId;

    private Integer sportsIntensityId;

    private String sportsTime;

    private String sportsFrequency;

    private String sportsMustEquipment;

    private Integer sportsFatigue;

    private String runSpeed;

    private Integer subjectFeelId;

    private String tabooDiseaseId;

    private String methodsIntroduce;

    private Integer reviewCycle;

    private String videoUrl;

    private String notice;

    private Integer recipeNum;

    private Date recipeTime;

    private String effectLevel;

    private String satisfactionLevel;

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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public Integer getSportsEffectId() {
        return sportsEffectId;
    }

    public void setSportsEffectId(Integer sportsEffectId) {
        this.sportsEffectId = sportsEffectId;
    }

    public Integer getSportsTypeId() {
        return sportsTypeId;
    }

    public void setSportsTypeId(Integer sportsTypeId) {
        this.sportsTypeId = sportsTypeId;
    }

    public Integer getSportsIntensityId() {
        return sportsIntensityId;
    }

    public void setSportsIntensityId(Integer sportsIntensityId) {
        this.sportsIntensityId = sportsIntensityId;
    }

    public String getSportsTime() {
        return sportsTime;
    }

    public void setSportsTime(String sportsTime) {
        this.sportsTime = sportsTime == null ? null : sportsTime.trim();
    }

    public String getSportsFrequency() {
        return sportsFrequency;
    }

    public void setSportsFrequency(String sportsFrequency) {
        this.sportsFrequency = sportsFrequency == null ? null : sportsFrequency.trim();
    }

    public String getSportsMustEquipment() {
        return sportsMustEquipment;
    }

    public void setSportsMustEquipment(String sportsMustEquipment) {
        this.sportsMustEquipment = sportsMustEquipment == null ? null : sportsMustEquipment.trim();
    }

    public Integer getSportsFatigue() {
        return sportsFatigue;
    }

    public void setSportsFatigue(Integer sportsFatigue) {
        this.sportsFatigue = sportsFatigue;
    }

    public String getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(String runSpeed) {
        this.runSpeed = runSpeed == null ? null : runSpeed.trim();
    }

    public Integer getSubjectFeelId() {
        return subjectFeelId;
    }

    public void setSubjectFeelId(Integer subjectFeelId) {
        this.subjectFeelId = subjectFeelId;
    }

    public String getTabooDiseaseId() {
        return tabooDiseaseId;
    }

    public void setTabooDiseaseId(String tabooDiseaseId) {
        this.tabooDiseaseId = tabooDiseaseId == null ? null : tabooDiseaseId.trim();
    }

    public String getMethodsIntroduce() {
        return methodsIntroduce;
    }

    public void setMethodsIntroduce(String methodsIntroduce) {
        this.methodsIntroduce = methodsIntroduce == null ? null : methodsIntroduce.trim();
    }

    public Integer getReviewCycle() {
        return reviewCycle;
    }

    public void setReviewCycle(Integer reviewCycle) {
        this.reviewCycle = reviewCycle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public Integer getRecipeNum() {
        return recipeNum;
    }

    public void setRecipeNum(Integer recipeNum) {
        this.recipeNum = recipeNum;
    }

    public Date getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(Date recipeTime) {
        this.recipeTime = recipeTime;
    }

    public String getEffectLevel() {
        return effectLevel;
    }

    public void setEffectLevel(String effectLevel) {
        this.effectLevel = effectLevel == null ? null : effectLevel.trim();
    }

    public String getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(String satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel == null ? null : satisfactionLevel.trim();
    }
}