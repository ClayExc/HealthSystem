package com.sports.entity;

public class StandardRecipe {
    private Integer id;

    private String name;

    private String sportsEffectId;

    private Integer sportsTypeId;

    private Integer sportsIntensityId;

    private String sportsTime;

    private String sportsFrequency;

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
        this.name = name;
    }

    public String getSportsEffectId() {
        return sportsEffectId;
    }

    public void setSportsEffectId(String sportsEffectId) {
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
        this.sportsTime = sportsTime;
    }

    public String getSportsFrequency() {
        return sportsFrequency;
    }

    public void setSportsFrequency(String sportsFrequency) {
        this.sportsFrequency = sportsFrequency;
    }

    public String getSportsMustEquipmentId() {
        return sportsMustEquipmentId;
    }

    public void setSportsMustEquipmentId(String sportsMustEquipmentId) {
        this.sportsMustEquipmentId = sportsMustEquipmentId;
    }

    public Integer getSportsFatigue() {
        return sportsFatigue;
    }

    public void setSportsFatigue(Integer sportsFatigue) {
        this.sportsFatigue = sportsFatigue;
    }

    public String getTabooDiseaseId() {
        return tabooDiseaseId;
    }

    public void setTabooDiseaseId(String tabooDiseaseId) {
        this.tabooDiseaseId = tabooDiseaseId;
    }

    public String getMethodsIntroduce() {
        return methodsIntroduce;
    }

    public void setMethodsIntroduce(String methodsIntroduce) {
        this.methodsIntroduce = methodsIntroduce;
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
        this.videoUrl = videoUrl;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    private String sportsMustEquipmentId;

    private Integer sportsFatigue;

    private String tabooDiseaseId;

    private String methodsIntroduce;

    private Integer reviewCycle;

    private String videoUrl;

    private String notice;


}