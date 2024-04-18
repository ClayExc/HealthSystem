package com.sports.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.Objects;

public class RunData {
    private Integer id;

    private Integer userId;

    private String userName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String recordTime;

    private Date startTime;

    private Date endTime;

    private String runTime;

    private Integer timeInterval;

    private String heartRate;

    private Double quietHeartRate;

    private Double maxHeartRate;

    private Double minHeartRate;

    private String runSpeed;

    private Double averageSpeed;

    private Double distance;

    private Double calorie;

    private Integer recipeId;

    private Double temperature;

    private String weather;

    private Double altitude;

    private Double humidity;

    private Integer stepNumber;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
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

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate == null ? null : heartRate.trim();
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

    public String getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(String runSpeed) {
        this.runSpeed = runSpeed == null ? null : runSpeed.trim();
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RunData runData = (RunData) object;
        return Objects.equals(id, runData.id) &&
                Objects.equals(userId, runData.userId) &&
                Objects.equals(userName, runData.userName) &&
                Objects.equals(recordTime, runData.recordTime) &&
                Objects.equals(startTime, runData.startTime) &&
                Objects.equals(endTime, runData.endTime) &&
                Objects.equals(runTime, runData.runTime) &&
                Objects.equals(timeInterval, runData.timeInterval) &&
                Objects.equals(heartRate, runData.heartRate) &&
                Objects.equals(quietHeartRate, runData.quietHeartRate) &&
                Objects.equals(maxHeartRate, runData.maxHeartRate) &&
                Objects.equals(minHeartRate, runData.minHeartRate) &&
                Objects.equals(runSpeed, runData.runSpeed) &&
                Objects.equals(averageSpeed, runData.averageSpeed) &&
                Objects.equals(distance, runData.distance) &&
                Objects.equals(calorie, runData.calorie) &&
                Objects.equals(recipeId, runData.recipeId) &&
                Objects.equals(temperature, runData.temperature) &&
                Objects.equals(weather, runData.weather) &&
                Objects.equals(altitude, runData.altitude) &&
                Objects.equals(humidity, runData.humidity) &&
                Objects.equals(stepNumber, runData.stepNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userName, recordTime, startTime, endTime, runTime, timeInterval, heartRate, quietHeartRate, maxHeartRate, minHeartRate, runSpeed, averageSpeed, distance, calorie, recipeId, temperature, weather, altitude, humidity, stepNumber);
    }
}