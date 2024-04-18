package com.sports.Vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class RunDataVo {

    private Integer id;

    private Integer userId;

    private String userName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String recordTime;

    private String startTime;

    private Date endTime;

    private String runTime;

    private Integer timeInterval;

    private Double heartRate;

    private Double quietHeartRate;

    private Double maxHeartRate;

    private Double minHeartRate;

    private Double runSpeed;

    private Double averageSpeed;

    private Double distance;

    private Double calorie;

    private Integer recipeId;

    private Double temperature;

    private String weather;

    private Double altitude;

    private Double humidity;

    private Integer stepNumber;

    private  String week;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime == null ? null : runTime.trim();
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Double getQuietHeartRate() {
        return quietHeartRate;
    }

    public void setQuietHeartRate(Double quietHeartRate) {
        this.quietHeartRate = quietHeartRate;
    }

    public Double getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Double maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Double getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(Double minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

    public Double getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(Double runSpeed) {
        this.runSpeed = runSpeed;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }
}
