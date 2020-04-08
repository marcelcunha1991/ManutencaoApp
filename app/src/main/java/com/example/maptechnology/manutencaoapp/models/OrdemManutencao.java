package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdemManutencao {

    @SerializedName("ordem")
    @Expose
    private IdOrdem ordem;

    public IdOrdem getOrdem() {
        return ordem;
    }

    public void setOrdem(IdOrdem ordem) {
        this.ordem = ordem;
    }

}


