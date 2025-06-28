package com.app.myapplication.models;

public class PopularTip {
    private String tip;
    private String category;
    private String likes;
    private int imageResId;

    public PopularTip(String tip, String category, String likes, int imageResId) {
        this.tip = tip;
        this.category = category;
        this.likes = likes;
        this.imageResId = imageResId;
    }

    public String getTip() {
        return tip;
    }

    public String getCategory() {
        return category;
    }

    public String getLikes() {
        return likes;
    }

    public int getImageResId() {
        return imageResId;
    }
}