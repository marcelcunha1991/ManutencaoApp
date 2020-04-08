package com.example.maptechnology.manutencaoapp.models;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllUsers {

    @SerializedName("all_Users")
    @Expose
    private List<User> allUsers = null;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

}
