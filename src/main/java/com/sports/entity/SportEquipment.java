package com.sports.entity;

public class SportEquipment {
    private Integer id;

    private String equipmentName;

    private String equipmentDescription;

    private Object equipmentImage;

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public Object getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(Object equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }
}