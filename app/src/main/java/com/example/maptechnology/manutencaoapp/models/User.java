package com.example.maptechnology.manutencaoapp.models;

/**
 * Created by MAPTECHNOLOGY on 23/02/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("dataCriacao")
    @Expose
    private String dataCriacao;
    @SerializedName("hierarquia")
    @Expose
    private String hierarquia;
    @SerializedName("homemHora")
    @Expose
    private String homemHora;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public String getHomemHora() {
        return homemHora;
    }

    public void setHomemHora(String homemHora) {
        this.homemHora = homemHora;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}