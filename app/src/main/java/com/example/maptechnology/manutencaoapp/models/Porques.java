package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Porques {

    @SerializedName("porques")
    @Expose
    private List<Porque> porque = null;

    public List<Porque> getPorque() {
        return porque;
    }

    public void setPorque(List<Porque> porque) {
        this.porque = porque;
    }
}
