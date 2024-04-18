package com.sports.entity;

public class RecipeStrengthGroup {
    private Integer id;

    private String effect;

    private String recipeName;

    private String type;

    private String typeValue;

    private String musclesTrained;

    private String methodIntroduction;

    private String loadRange;

    private String frequencyRange;

    private String groupRange;

    private String sportsFrequency;

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

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue == null ? null : typeValue.trim();
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

    public String getLoadRange() {
        return loadRange;
    }

    public void setLoadRange(String loadRange) {
        this.loadRange = loadRange == null ? null : loadRange.trim();
    }

    public String getFrequencyRange() {
        return frequencyRange;
    }

    public void setFrequencyRange(String frequencyRange) {
        this.frequencyRange = frequencyRange == null ? null : frequencyRange.trim();
    }

    public String getGroupRange() {
        return groupRange;
    }

    public void setGroupRange(String groupRange) {
        this.groupRange = groupRange == null ? null : groupRange.trim();
    }

    public String getSportsFrequency() {
        return sportsFrequency;
    }

    public void setSportsFrequency(String sportsFrequency) {
        this.sportsFrequency = sportsFrequency == null ? null : sportsFrequency.trim();
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