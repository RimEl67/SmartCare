package com.app.myapplication.models;

public class MedicineInfo {
    private String name;
    private String category;
    private String description;

    public MedicineInfo(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}