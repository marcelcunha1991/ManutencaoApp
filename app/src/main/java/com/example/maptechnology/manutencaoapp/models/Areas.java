package com.example.maptechnology.manutencaoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Areas {

    @SerializedName("area")
    @Expose
    private List<IdArea> area = null;

    public List<IdArea> getArea() {
        return area;
    }

    public void setArea(List<IdArea> area) {
        this.area = area;
    }

}