package com.app.myapplication.models;

public class ChatMessage {
    private String id;
    private String message;
    private long timestamp;
    private boolean isUser;

    public ChatMessage(String id, String message, long timestamp, boolean isUser) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.isUser = isUser;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isUser() {
        return isUser;
    }
}