package com.example.maptechnology.manutencaoapp.models;

/**
 * Created by MAPTECHNOLOGY on 22/03/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Calendario implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("dataManutencao")
    @Expose
    private String dataManutencao;
    @SerializedName("dataInicio")
    @Expose
    private String dataInicio;
    @SerializedName("dataFim")
    @Expose
    private String dataFim;
    @SerializedName("tempoManutencao")
    @Expose
    private String tempoManutencao;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("idConjunto")
    @Expose
    private String idConjunto;
    @SerializedName("idSubConjunto")
    @Expose
    private String idSubConjunto;
    @SerializedName("idPeca")
    @Expose
    private String idPeca;
    @SerializedName("manutencao")
    @Expose
    private String manutencao;
    @SerializedName("responsavel")
    @Expose
    private String responsavel;
    @SerializedName("realizadoPor")
    @Expose
    private String realizadoPor;
    @SerializedName("falha")
    @Expose
    private String falha;

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

    public String getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(String dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getTempoManutencao() {
        return tempoManutencao;
    }

    public void setTempoManutencao(String tempoManutencao) {
        this.tempoManutencao = tempoManutencao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdConjunto() {
        return idConjunto;
    }

    public void setIdConjunto(String idConjunto) {
        this.idConjunto = idConjunto;
    }

    public String getIdSubConjunto() {
        return idSubConjunto;
    }

    public void setIdSubConjunto(String idSubConjunto) {
        this.idSubConjunto = idSubConjunto;
    }

    public String getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(String idPeca) {
        this.idPeca = idPeca;
    }

    public String getManutencao() {
        return manutencao;
    }

    public void setManutencao(String manutencao) {
        this.manutencao = manutencao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(String realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public String getFalha() {
        return falha;
    }

    public void setFalha(String falha) {
        this.falha = falha;
    }
}