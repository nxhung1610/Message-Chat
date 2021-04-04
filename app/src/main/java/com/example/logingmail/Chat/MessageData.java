package com.example.logingmail.Chat;

import com.example.logingmail.UserProfile;

import java.io.Serializable;

public class MessageData implements Serializable {
    private  String sender;
    private  String message;
    private  String urlAvatar;
    private  UserProfile profile;

    public MessageData() {
    }

    public MessageData(String sender, String message, String urlAvatar) {
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


    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
