package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IdOrdem implements Serializable {

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
    private int frequenciaManutencao;
    @SerializedName("tipo")
    @Expose
    private int tipo;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("responsavelCriacao")
    @Expose
    private Responsavel responsavelCriacao;
    @SerializedName("falha")
    @Expose
    private Falha falha;
    @SerializedName("area")
    @Expose
    private IdArea area;
    @SerializedName("conjunto")
    @Expose
    private Conjunto conjunto;

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

    public int getFrequenciaManutencao() {
        return frequenciaManutencao;
    }

    public void setFrequenciaManutencao(int frequenciaManutencao) {
        this.frequenciaManutencao = frequenciaManutencao;
    }

    public int  getStatus() {
        return status;
    }

    public void setStatus(int  status) {
        this.status = status;
    }

    public Responsavel getResponsavelCriacao() {
        return responsavelCriacao;
    }

    public void setResponsavelCriacao(Responsavel responsavelCriacao) {
        this.responsavelCriacao = responsavelCriacao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }


    public Falha getFalha() {
        return falha;
    }

    public void setFalha(Falha falha) {
        this.falha = falha;
    }

    public IdArea getArea() {
        return area;
    }

    public void setArea(IdArea area) {
        this.area = area;
    }

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
    }
}