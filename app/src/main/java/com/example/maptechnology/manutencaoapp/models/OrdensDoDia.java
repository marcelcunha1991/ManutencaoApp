package com.example.maptechnology.manutencaoapp.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdensDoDia {

    @SerializedName("ordem")
    @Expose
    private ArrayList<IdOrdem> ordem = null;

    public ArrayList<IdOrdem> getOrdem() {
        return ordem;
    }

    public void setOrdem(ArrayList<IdOrdem> ordem) {
        this.ordem = ordem;
    }

}