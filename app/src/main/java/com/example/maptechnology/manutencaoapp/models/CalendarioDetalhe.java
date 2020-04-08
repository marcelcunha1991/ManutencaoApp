package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CalendarioDetalhe  implements Serializable {

    @SerializedName("calendario")
    @Expose
    private Atividade calendario;

    public Atividade getCalendario() {
        return calendario;
    }

    public void setCalendario(Atividade calendario) {
        this.calendario = calendario;
    }

}