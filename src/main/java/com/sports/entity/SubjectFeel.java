package com.sports.entity;

public class SubjectFeel {
    private Integer id;

    private String name;

    private String description;

    private Integer heartRateUp;

    private Integer heartRateDown;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getHeartRateUp() {
        return heartRateUp;
    }

    public void setHeartRateUp(Integer heartRateUp) {
        this.heartRateUp = heartRateUp;
    }

    public Integer getHeartRateDown() {
        return heartRateDown;
    }

    public void setHeartRateDown(Integer heartRateDown) {
        this.heartRateDown = heartRateDown;
    }
}