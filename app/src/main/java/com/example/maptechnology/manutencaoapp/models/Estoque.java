package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estoque {

    @SerializedName("estoque")
    @Expose
    private List<Estoque_> estoque = null;

    public List<Estoque_> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Estoque_> estoque) {
        this.estoque = estoque;
    }

}
