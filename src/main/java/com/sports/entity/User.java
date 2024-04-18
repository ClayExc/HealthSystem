package com.sports.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirthday;

    private String userSex;

    private String userAccount;

    private String userPassword;

    private Float userBmi;

    private String userDisease;

    private Integer userOptimalRate;

    private Integer userIsManager;

    private String userImage;

    private String userDelcar;

    private Float userWeight;

    private Float userHeight;

    private Float userWaist;

    private Float userChest;

    private Float userHipline;

    private Integer userSubhealthy;

    private Integer userSportAbility;

    private String userSportEquipmentId;

    private String userSportLike;

    private Integer userSportObjective1;

    private Integer userSportObjective2;

    private String userObjectiveDescription;

    private Integer userRecipeNum;

    private Integer userRecipeStatus;






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

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Float getUserBmi() {
        return userBmi;
    }

    public void setUserBmi(Float userBmi) {
        this.userBmi = userBmi;
    }

    public String getUserDisease() {
        return userDisease;
    }

    public void setUserDisease(String userDisease) {
        this.userDisease = userDisease == null ? null : userDisease.trim();
    }

    public Integer getUserOptimalRate() {
        return userOptimalRate;
    }

    public void setUserOptimalRate(Integer userOptimalRate) {
        this.userOptimalRate = userOptimalRate;
    }

    public Integer getUserIsManager() {
        return userIsManager;
    }

    public void setUserIsManager(Integer userIsManager) {
        this.userIsManager = userIsManager;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }

    public String getUserDelcar() {
        return userDelcar;
    }

    public void setUserDelcar(String userDelcar) {
        this.userDelcar = userDelcar == null ? null : userDelcar.trim();
    }

    public Float getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(Float userWeight) {
        this.userWeight = userWeight;
    }

    public Float getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(Float userHeight) {
        this.userHeight = userHeight;
    }

    public Float getUserWaist() {
        return userWaist;
    }

    public void setUserWaist(Float userWaist) {
        this.userWaist = userWaist;
    }

    public Float getUserChest() {
        return userChest;
    }

    public void setUserChest(Float userChest) {
        this.userChest = userChest;
    }

    public Float getUserHipline() {
        return userHipline;
    }

    public void setUserHipline(Float userHipline) {
        this.userHipline = userHipline;
    }

    public Integer getUserSubhealthy() {
        return userSubhealthy;
    }

    public void setUserSubhealthy(Integer userSubhealthy) {
        this.userSubhealthy = userSubhealthy;
    }

    public Integer getUserSportAbility() {
        return userSportAbility;
    }

    public void setUserSportAbility(Integer userSportAbility) {
        this.userSportAbility = userSportAbility;
    }

    public String getUserSportEquipmentId() {
        return userSportEquipmentId;
    }

    public void setUserSportEquipmentId(String userSportEquipmentId) {
        this.userSportEquipmentId = userSportEquipmentId == null ? null : userSportEquipmentId.trim();
    }

    public String getUserSportLike() {
        return userSportLike;
    }

    public void setUserSportLike(String userSportLike) {
        this.userSportLike = userSportLike == null ? null : userSportLike.trim();
    }

    public Integer getUserSportObjective1() {
        return userSportObjective1;
    }

    public void setUserSportObjective1(Integer userSportObjective1) {
        this.userSportObjective1 = userSportObjective1;
    }

    public Integer getUserSportObjective2() {
        return userSportObjective2;
    }

    public void setUserSportObjective2(Integer userSportObjective2) {
        this.userSportObjective2 = userSportObjective2;
    }

    public String getUserObjectiveDescription() {
        return userObjectiveDescription;
    }

    public void setUserObjectiveDescription(String userObjectiveDescription) {
        this.userObjectiveDescription = userObjectiveDescription == null ? null : userObjectiveDescription.trim();
    }

    public Integer getUserRecipeNum() {
        return userRecipeNum;
    }

    public void setUserRecipeNum(Integer userRecipeNum) {
        this.userRecipeNum = userRecipeNum;
    }

    public Integer getUserRecipeStatus() {
        return userRecipeStatus;
    }

    public void setUserRecipeStatus(Integer userRecipeStatus) {
        this.userRecipeStatus = userRecipeStatus;
    }
}