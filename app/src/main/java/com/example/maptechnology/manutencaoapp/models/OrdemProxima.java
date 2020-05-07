package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdemProxima {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("dataCriacao")
    @Expose
    private String dataCriacao;
    @SerializedName("tempoTotalManutencao")
    @Expose
    private String tempoTotalManutencao;
    @SerializedName("frequenciaManutencao")
    @Expose
    private Integer frequenciaManutencao;
    @SerializedName("tipo")
    @Expose
    private Integer tipo;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("falha")
    @Expose
    private String falha;
    @SerializedName("isParada")
    @Expose
    private Boolean isParada;
    @SerializedName("responsavelCriacao")
    @Expose
    private Integer responsavelCriacao;
    @SerializedName("area")
    @Expose
    private Integer area;
    @SerializedName("conjunto")
    @Expose
    private Integer conjunto;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTempoTotalManutencao() {
        return tempoTotalManutencao;
    }

    public void setTempoTotalManutencao(String tempoTotalManutencao) {
        this.tempoTotalManutencao = tempoTotalManutencao;
    }

    public Integer getFrequenciaManutencao() {
        return frequenciaManutencao;
    }

    public void setFrequenciaManutencao(Integer frequenciaManutencao) {
        this.frequenciaManutencao = frequenciaManutencao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFalha() {
        return falha;
    }

    public void setFalha(String falha) {
        this.falha = falha;
    }

    public Boolean getIsParada() {
        return isParada;
    }

    public void setIsParada(Boolean isParada) {
        this.isParada = isParada;
    }

    public Integer getResponsavelCriacao() {
        return responsavelCriacao;
    }

    public void setResponsavelCriacao(Integer responsavelCriacao) {
        this.responsavelCriacao = responsavelCriacao;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getConjunto() {
        return conjunto;
    }

    public void setConjunto(Integer conjunto) {
        this.conjunto = conjunto;
    }

}