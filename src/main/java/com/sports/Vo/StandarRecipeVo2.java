package com.sports.Vo;

import java.util.List;

public class StandarRecipeVo2 {
    private Integer id;

    private String name;

    //private String sportsEffectId;
    private List<String> sportsEffectList;

    private String sportsTypeId;

    private int sportsIntensityId;

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

    public List<String> getSportsEffectList() {
        return sportsEffectList;
    }

    public void setSportsEffectList(List<String> sportsEffectId) {
        this.sportsEffectList = sportsEffectId;
    }

    public String getSportsTypeId() {
        return sportsTypeId;
    }

    public void setSportsTypeId(String sportsTypeId) {
        this.sportsTypeId = sportsTypeId;
    }

    public int getSportsIntensityId() {
        return sportsIntensityId;
    }

    public void setSportsIntensityId(int sportsIntensityId) {
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

    public String getSportsFatigue() {
        return sportsFatigue;
    }

    public void setSportsFatigue(String sportsFatigue) {
        this.sportsFatigue = sportsFatigue;
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

    private List<String> sportsMustEquipmentList;

    public List<String> getSportsMustEquipmentList() {
        return sportsMustEquipmentList;
    }

    public void setSportsMustEquipmentList(List<String> sportsMustEquipmentList) {
        this.sportsMustEquipmentList = sportsMustEquipmentList;
    }

    private String sportsFatigue;

    private List<String> tabooDiseaseList;

    public List<String> getTabooDiseaseList() {
        return tabooDiseaseList;
    }

    public void setTabooDiseaseList(List<String> tabooDiseaseList) {
        this.tabooDiseaseList = tabooDiseaseList;
    }

    private String methodsIntroduce;

    private Integer reviewCycle;

    private String videoUrl;

    private String notice;
//运动强度的
    private Integer heartRateMin;

    private Integer heartRateMax;

    private String fuzhongRange;

    private String number;

    private String groupNum;

    private String intensityType;

    public Integer getHeartRateMin() {
        return heartRateMin;
    }

    public void setHeartRateMin(Integer heartRateMin) {
        this.heartRateMin = heartRateMin;
    }

    public Integer getHeartRateMax() {
        return heartRateMax;
    }

    public void setHeartRateMax(Integer heartRateMax) {
        this.heartRateMax = heartRateMax;
    }

    public String getFuzhongRange() {
        return fuzhongRange;
    }

    public void setFuzhongRange(String fuzhongRange) {
        this.fuzhongRange = fuzhongRange;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getIntensityType() {
        return intensityType;
    }

    public void setIntensityType(String intensityType) {
        this.intensityType = intensityType;
    }
}
