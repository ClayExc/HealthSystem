package com.sports.entity;

public class SportTypes {
    private Integer id;

    private String sportTypesName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportTypesName() {
        return sportTypesName;
    }

    public void setSportTypesName(String sportTypesName) {
        this.sportTypesName = sportTypesName == null ? null : sportTypesName.trim();
    }
}