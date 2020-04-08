package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubConjuntos {

    @SerializedName("subconjuntos")
    @Expose
    private List<SubConjunto> subconjuntos = null;

    public List<SubConjunto> getSubconjuntos() {
        return subconjuntos;
    }

    public void setSubconjuntos(List<SubConjunto> subconjuntos) {
        this.subconjuntos = subconjuntos;
    }

}