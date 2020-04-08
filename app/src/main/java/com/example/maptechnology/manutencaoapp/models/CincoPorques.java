package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CincoPorques {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("primeiroPorque")
    @Expose
    private String primeiroPorque;
    @SerializedName("segundoPorque")
    @Expose
    private String segundoPorque;
    @SerializedName("terceiroPorque")
    @Expose
    private String terceiroPorque;
    @SerializedName("quartoPorque")
    @Expose
    private String quartoPorque;
    @SerializedName("quintoPorque")
    @Expose
    private String quintoPorque;
    @SerializedName("idOrdem")
    @Expose
    private IdOrdem idOrdem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimeiroPorque() {
        return primeiroPorque;
    }

    public void setPrimeiroPorque(String primeiroPorque) {
        this.primeiroPorque = primeiroPorque;
    }

    public String getSegundoPorque() {
        return segundoPorque;
    }

    public void setSegundoPorque(String segundoPorque) {
        this.segundoPorque = segundoPorque;
    }

    public String getTerceiroPorque() {
        return terceiroPorque;
    }

    public void setTerceiroPorque(String terceiroPorque) {
        this.terceiroPorque = terceiroPorque;
    }

    public String getQuartoPorque() {
        return quartoPorque;
    }

    public void setQuartoPorque(String quartoPorque) {
        this.quartoPorque = quartoPorque;
    }

    public String getQuintoPorque() {
        return quintoPorque;
    }

    public void setQuintoPorque(String quintoPorque) {
        this.quintoPorque = quintoPorque;
    }

    public IdOrdem getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(IdOrdem idOrdem) {
        this.idOrdem = idOrdem;
    }

}