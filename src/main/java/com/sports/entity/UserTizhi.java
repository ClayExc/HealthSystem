package com.sports.entity;

public class UserTizhi {
    private Integer id;

    private Integer userid;

    private Integer heartLungAbility;

    private Integer muscularEndurance;

    private Integer physique;

    private Integer withinFat;

    private String qualityNumber;

    private Integer subhealtNum;

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

    public Integer getHeartLungAbility() {
        return heartLungAbility;
    }

    public void setHeartLungAbility(Integer heartLungAbility) {
        this.heartLungAbility = heartLungAbility;
    }

    public Integer getMuscularEndurance() {
        return muscularEndurance;
    }

    public void setMuscularEndurance(Integer muscularEndurance) {
        this.muscularEndurance = muscularEndurance;
    }

    public Integer getPhysique() {
        return physique;
    }

    public void setPhysique(Integer physique) {
        this.physique = physique;
    }

    public Integer getWithinFat() {
        return withinFat;
    }

    public void setWithinFat(Integer withinFat) {
        this.withinFat = withinFat;
    }

    public String getQualityNumber() {
        return qualityNumber;
    }

    public void setQualityNumber(String qualityNumber) {
        this.qualityNumber = qualityNumber == null ? null : qualityNumber.trim();
    }

    public Integer getSubhealtNum() {
        return subhealtNum;
    }

    public void setSubhealtNum(Integer subhealtNum) {
        this.subhealtNum = subhealtNum;
    }
}