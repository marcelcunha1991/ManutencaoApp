package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pecas {

    @SerializedName("pecas")
    @Expose
    private List<Peca> pecas = null;

    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

}