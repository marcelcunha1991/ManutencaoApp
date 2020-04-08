package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Falhas {

    @SerializedName("falhas")
    @Expose
    private List<Falha> falhas = null;

    public List<Falha> getFalhas() {
        return falhas;
    }

    public void setFalhas(List<Falha> falhas) {
        this.falhas = falhas;
    }

}

