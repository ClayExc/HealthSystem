package com.sports.entity;

public class SportsIntensity {
    private Integer id;

    private Integer heartRateMin;

    private Integer heartRateMax;

    private String fuzhongRange;

    private String number;

    private String groupNum;

    private String intensityType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.fuzhongRange = fuzhongRange == null ? null : fuzhongRange.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum == null ? null : groupNum.trim();
    }

    public String getIntensityType() {
        return intensityType;
    }

    public void setIntensityType(String intensityType) {
        this.intensityType = intensityType == null ? null : intensityType.trim();
    }
}