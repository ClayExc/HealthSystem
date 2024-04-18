package com.sports.entity;

public class SportsEffect {
    private Integer id;

    private String sportsEffect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportsEffect() {
        return sportsEffect;
    }

    public void setSportsEffect(String sportsEffect) {
        this.sportsEffect = sportsEffect == null ? null : sportsEffect.trim();
    }
}