package com.example.maptechnology.manutencaoapp.models;

/**
 * Created by MAPTECHNOLOGY on 22/03/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Atividade implements Serializable {

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
    @SerializedName("dataInicioPausa")
    @Expose
    private String dataInicioPausa;
    @SerializedName("dataFimPausa")
    @Expose
    private String dataFimPausa;
    @SerializedName("dataFim")
    @Expose
    private String dataFim;
    @SerializedName("tempoManutencao")
    @Expose
    private String tempoManutencao;
    @SerializedName("observacao")
    @Expose
    private String observacao;
    @SerializedName("custo")
    @Expose
    private float custo;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("idConjunto")
    @Expose
    private Conjunto idConjunto;
    @SerializedName("idSubConjunto")
    @Expose
    private SubConjunto idSubConjunto;
    @SerializedName("idPeca")
    @Expose
    private Peca idPeca;
    @SerializedName("idOrdem")
    @Expose
    private IdOrdem idOrdem;
    @SerializedName("manutencao")
    @Expose
    private Manutencao manutencao;
    @SerializedName("responsavel")
    @Expose
    private Responsavel responsavel;
    @SerializedName("realizadoPor")
    @Expose
    private Responsavel realizadoPor;
    @SerializedName("falha")
    @Expose
    private Falha falha;

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

    public String getDataInicioPausa() {
        return dataInicioPausa;
    }

    public void setDataInicioPausa(String dataInicioPausa) {
        this.dataInicioPausa = dataInicioPausa;
    }

    public String getDataFimPausa() {
        return dataFimPausa;
    }

    public void setDataFimPausa(String dataFimPausa) {
        this.dataFimPausa = dataFimPausa;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Conjunto getIdConjunto() {
        return idConjunto;
    }

    public void setIdConjunto(Conjunto idConjunto) {
        this.idConjunto = idConjunto;
    }

    public SubConjunto getIdSubConjunto() {
        return idSubConjunto;
    }

    public void setIdSubConjunto(SubConjunto idSubConjunto) {
        this.idSubConjunto = idSubConjunto;
    }

    public Peca getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Peca idPeca) {
        this.idPeca = idPeca;
    }

    public IdOrdem getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(IdOrdem idOrdem) {
        this.idOrdem = idOrdem;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Responsavel getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(Responsavel realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public Falha getFalha() {
        return falha;
    }

    public void setFalha(Falha falha) {
        this.falha = falha;
    }

}