package com.example.swapspot.model;

public class Message {
    private String userName;
    private String lastMessage;
    private String time;
    private int profileImage;
    private boolean isOnline;

    public Message(String userName, String lastMessage, String time, int profileImage, boolean isOnline) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.time = time;
        this.profileImage = profileImage;
        this.isOnline = isOnline;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public boolean isOnline() {
        return isOnline;
    }
} 