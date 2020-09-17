package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManutencaoCorretiva {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("tempoManutencao")
    @Expose
    private Integer tempoManutencao;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTempoManutencao() {
        return tempoManutencao;
    }

    public void setTempoManutencao(Integer tempoManutencao) {
        this.tempoManutencao = tempoManutencao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}