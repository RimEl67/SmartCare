package com.app.myapplication.models;

public class Hospital {
    private String name;
    private String distance;
    private String description;
    private String latitude;
    private String longitude;

    public Hospital(String name, String distance, String description, String latitude, String longitude) {
        this.name = name;
        this.distance = distance;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getDescription() {
        return description;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}