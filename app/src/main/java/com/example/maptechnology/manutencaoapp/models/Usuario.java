package com.example.maptechnology.manutencaoapp.models;

/**
 * Created by MAPTECHNOLOGY on 23/02/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("user_data")
    @Expose
    private UserData userData;
    @SerializedName("user")
    @Expose
    private User user;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}