package com.sports.entity;

public class FatigueDegree {

    private Integer id;

    private String name;
    //自我感觉

    private String feel;
    //面色

    private String face;
    //排汗量

    private String sweat;
    //呼吸

    private String breathe;
    //动作

    private String action;
    //注意力

    private String attention;

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

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel == null ? null : feel.trim();
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face == null ? null : face.trim();
    }

    public String getSweat() {
        return sweat;
    }

    public void setSweat(String sweat) {
        this.sweat = sweat == null ? null : sweat.trim();
    }

    public String getBreathe() {
        return breathe;
    }

    public void setBreathe(String breathe) {
        this.breathe = breathe == null ? null : breathe.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }
}