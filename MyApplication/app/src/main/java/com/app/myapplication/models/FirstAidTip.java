package com.app.myapplication.models;

public class FirstAidTip {
    private String title;
    private String steps;
    private boolean isSaved;

    public FirstAidTip(String title, String steps) {
        this.title = title;
        this.steps = steps;
        this.isSaved = false;
    }

    public String getTitle() {
        return title;
    }

    public String getSteps() {
        return steps;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}