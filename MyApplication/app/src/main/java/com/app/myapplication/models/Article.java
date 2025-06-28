package com.app.myapplication.models;

public class Article {
    private String title;
    private String summary;
    private String readTime;
    private String date;
    private int imageResId;

    public Article(String title, String summary, String readTime, String date, int imageResId) {
        this.title = title;
        this.summary = summary;
        this.readTime = readTime;
        this.date = date;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getReadTime() {
        return readTime;
    }

    public String getDate() {
        return date;
    }

    public int getImageResId() {
        return imageResId;
    }
}