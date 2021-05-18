package com.example.logingmail.Chat;

import com.example.logingmail.UserProfile;

import java.io.Serializable;

public class MessageData implements Serializable {
    private String uid;
    private String sender;
    private String message;
    private String urlAvatar;

    public MessageData() {
    }

    public MessageData(String uid, String sender, String message, String urlAvatar) {
        this.uid = uid;
        this.sender = sender;
        this.message = message;
        this.urlAvatar = urlAvatar;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getUid() {
        return uid;
    }
}
