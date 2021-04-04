package com.example.logingmail;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String uid;
    private String name;
    private String email;
    private String urlAvatar;

    public UserProfile() {

    }

    public UserProfile(String uid, String name, String email,@NonNull String urlAvatar) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.urlAvatar = urlAvatar;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }
}
