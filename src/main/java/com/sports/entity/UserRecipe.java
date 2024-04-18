package com.sports.entity;

public class UserRecipe {
    private Integer id;

    private Integer userid;

    private String sportWay;

    private Integer sportStrength;

    private Integer minStrengthType;

    private Integer sportTime;

    private Integer minTimeType;

    private Integer sportFrequency;

    private String strengthShowType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSportWay() {
        return sportWay;
    }

    public void setSportWay(String sportWay) {
        this.sportWay = sportWay == null ? null : sportWay.trim();
    }

    public Integer getSportStrength() {
        return sportStrength;
    }

    public void setSportStrength(Integer sportStrength) {
        this.sportStrength = sportStrength;
    }

    public Integer getMinStrengthType() {
        return minStrengthType;
    }

    public void setMinStrengthType(Integer minStrengthType) {
        this.minStrengthType = minStrengthType;
    }

    public Integer getSportTime() {
        return sportTime;
    }

    public void setSportTime(Integer sportTime) {
        this.sportTime = sportTime;
    }

    public Integer getMinTimeType() {
        return minTimeType;
    }

    public void setMinTimeType(Integer minTimeType) {
        this.minTimeType = minTimeType;
    }

    public Integer getSportFrequency() {
        return sportFrequency;
    }

    public void setSportFrequency(Integer sportFrequency) {
        this.sportFrequency = sportFrequency;
    }

    public String getStrengthShowType() {
        return strengthShowType;
    }

    public void setStrengthShowType(String strengthShowType) {
        this.strengthShowType = strengthShowType == null ? null : strengthShowType.trim();
    }
}