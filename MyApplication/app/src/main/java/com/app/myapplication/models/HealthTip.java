package com.app.myapplication.models;

public class HealthTip {
    private String title;
    private String content;

    public HealthTip(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}