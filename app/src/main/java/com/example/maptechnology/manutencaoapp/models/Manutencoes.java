package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Manutencoes {

    @SerializedName("manutencao")
    @Expose
    private List<Manutencao> manutencao = null;

    public List<Manutencao> getManutencao() {
        return manutencao;
    }

    public void setManutencao(List<Manutencao> manutencao) {
        this.manutencao = manutencao;
    }

}