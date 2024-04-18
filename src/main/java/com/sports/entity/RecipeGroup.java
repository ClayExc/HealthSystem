package com.sports.entity;

public class RecipeGroup {
    private Integer id;

    private String effect;

    private String recipeName;

    private String type;

    private String typevalue;

    private String musclesTrained;

    private String methodIntroduction;

    private String ageGroup;

    private Integer minHeartrate;

    private Integer maxHeartrate;

    private String sportsFrequency;

    private String sportsTime;

    private String notice;

    private String equipment;

    private String tabooDisease;

    private String image;

    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect == null ? null : effect.trim();
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName == null ? null : recipeName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypevalue() {
        return typevalue;
    }

    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue == null ? null : typevalue.trim();
    }

    public String getMusclesTrained() {
        return musclesTrained;
    }

    public void setMusclesTrained(String musclesTrained) {
        this.musclesTrained = musclesTrained == null ? null : musclesTrained.trim();
    }

    public String getMethodIntroduction() {
        return methodIntroduction;
    }

    public void setMethodIntroduction(String methodIntroduction) {
        this.methodIntroduction = methodIntroduction == null ? null : methodIntroduction.trim();
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup == null ? null : ageGroup.trim();
    }

    public Integer getMinHeartrate() {
        return minHeartrate;
    }

    public void setMinHeartrate(Integer minHeartrate) {
        this.minHeartrate = minHeartrate;
    }

    public Integer getMaxHeartrate() {
        return maxHeartrate;
    }

    public void setMaxHeartrate(Integer maxHeartrate) {
        this.maxHeartrate = maxHeartrate;
    }

    public String getSportsFrequency() {
        return sportsFrequency;
    }

    public void setSportsFrequency(String sportsFrequency) {
        this.sportsFrequency = sportsFrequency == null ? null : sportsFrequency.trim();
    }

    public String getSportsTime() {
        return sportsTime;
    }

    public void setSportsTime(String sportsTime) {
        this.sportsTime = sportsTime == null ? null : sportsTime.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    public String getTabooDisease() {
        return tabooDisease;
    }

    public void setTabooDisease(String tabooDisease) {
        this.tabooDisease = tabooDisease == null ? null : tabooDisease.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}