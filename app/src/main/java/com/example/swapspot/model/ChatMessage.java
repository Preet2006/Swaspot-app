package com.example.swapspot.model;

public class ChatMessage {
    private String message;
    private boolean isSent;
    private String imageUri; // null if not an image message
    private MessageType type;

    public enum MessageType {
        TEXT,
        IMAGE
    }

    // Text message constructor
    public ChatMessage(String message, boolean isSent) {
        this.message = message;
        this.isSent = isSent;
        this.type = MessageType.TEXT;
        this.imageUri = null;
    }

    // Image message constructor
    public ChatMessage(String imageUri, boolean isSent, boolean isImage) {
        this.imageUri = imageUri;
        this.isSent = isSent;
        this.type = MessageType.IMAGE;
        this.message = null;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSent() {
        return isSent;
    }

    public String getImageUri() {
        return imageUri;
    }

    public MessageType getType() {
        return type;
    }
} 