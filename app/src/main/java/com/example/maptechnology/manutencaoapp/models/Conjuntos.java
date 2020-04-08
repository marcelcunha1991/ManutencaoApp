package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conjuntos {

    @SerializedName("conjuntos")
    @Expose
    private List<Conjunto> conjuntos = null;

    public List<Conjunto> getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(List<Conjunto> conjuntos) {
        this.conjuntos = conjuntos;
    }

}