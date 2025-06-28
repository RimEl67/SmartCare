package com.app.myapplication.models;

public class Medicine {
    private String name;
    private String genericName;
    private String category;
    private String description;
    private String sideEffects;
    private String dosage;
    private int imageResId;

    public Medicine(String name, String genericName, String category, String description, String sideEffects, String dosage, int imageResId) {
        this.name = name;
        this.genericName = genericName;
        this.category = category;
        this.description = description;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public int getImageResId() {
        return imageResId;
    }
}