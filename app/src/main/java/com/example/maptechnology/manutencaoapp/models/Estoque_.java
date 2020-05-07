package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estoque_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("quantidade")
    @Expose
    private Integer quantidade;
    @SerializedName("quantidadeMinima")
    @Expose
    private Integer quantidadeMinima;
    @SerializedName("quantidadeMaxima")
    @Expose
    private Integer quantidadeMaxima;
    @SerializedName("validade")
    @Expose
    private Object validade;
    @SerializedName("numeroSerie")
    @Expose
    private Object numeroSerie;
    @SerializedName("spareParts")
    @Expose
    private String spareParts;
    @SerializedName("lote")
    @Expose
    private Object lote;
    @SerializedName("custo")
    @Expose
    private String custo;
    @SerializedName("endereco")
    @Expose
    private String endereco;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public Object getValidade() {
        return validade;
    }

    public void setValidade(Object validade) {
        this.validade = validade;
    }

    public Object getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(Object numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(String spareParts) {
        this.spareParts = spareParts;
    }

    public Object getLote() {
        return lote;
    }

    public void setLote(Object lote) {
        this.lote = lote;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}