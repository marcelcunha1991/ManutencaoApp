package com.example.maptechnology.manutencaoapp.models;

/**
 * Created by MAPTECHNOLOGY on 22/03/2019.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calendarios {

    @SerializedName("calendario")
    @Expose
    private ArrayList<Calendario> calendario = null;

    public ArrayList<Calendario> getCalendario() {
        return calendario;
    }

    public void setCalendario(ArrayList<Calendario> calendario) {
        this.calendario = calendario;
    }

}