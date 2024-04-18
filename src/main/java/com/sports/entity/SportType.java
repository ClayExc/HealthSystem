package com.sports.entity;

public class SportType {
    private Integer id;

    private String sportType;

    private Integer sportTypesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType == null ? null : sportType.trim();
    }

    public Integer getSportTypesId() {
        return sportTypesId;
    }

    public void setSportTypesId(Integer sportTypesId) {
        this.sportTypesId = sportTypesId;
    }
}