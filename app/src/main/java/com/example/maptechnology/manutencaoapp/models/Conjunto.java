package com.example.maptechnology.manutencaoapp.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Conjunto implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("ultimaManutencao")
    @Expose
    private Object ultimaManutencao;
    @SerializedName("hierarquia")
    @Expose
    private Object hierarquia;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("mtbfMeta")
    @Expose
    private String mtbfMeta;
    @SerializedName("mtbf")
    @Expose
    private Object mtbf;
    @SerializedName("mttrMeta")
    @Expose
    private String mttrMeta;
    @SerializedName("mttr")
    @Expose
    private Object mttr;
    @SerializedName("imageUrl")
    @Expose
    private Object imageUrl;
    @SerializedName("coordenadas")
    @Expose
    private Object coordenadas;
    @SerializedName("temFilho")
    @Expose
    private Boolean temFilho;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("numeroSerie")
    @Expose
    private String numeroSerie;
    @SerializedName("anoFabricacao")
    @Expose
    private String anoFabricacao;
    @SerializedName("idEstoque")
    @Expose
    private int idEstoque;
    @SerializedName("ultimoRevisor")
    @Expose
    private int ultimoRevisor;
    @SerializedName("idArea")
    @Expose
    private IdArea idArea;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Object getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(Object ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public Object getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(Object hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMtbfMeta() {
        return mtbfMeta;
    }

    public void setMtbfMeta(String mtbfMeta) {
        this.mtbfMeta = mtbfMeta;
    }

    public Object getMtbf() {
        return mtbf;
    }

    public void setMtbf(Object mtbf) {
        this.mtbf = mtbf;
    }

    public String getMttrMeta() {
        return mttrMeta;
    }

    public void setMttrMeta(String mttrMeta) {
        this.mttrMeta = mttrMeta;
    }

    public Object getMttr() {
        return mttr;
    }

    public void setMttr(Object mttr) {
        this.mttr = mttr;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Object coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Boolean getTemFilho() {
        return temFilho;
    }

    public void setTemFilho(Boolean temFilho) {
        this.temFilho = temFilho;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getUltimoRevisor() {
        return ultimoRevisor;
    }

    public void setUltimoRevisor(int ultimoRevisor) {
        this.ultimoRevisor = ultimoRevisor;
    }

    public IdArea getIdArea() {
        return idArea;
    }

    public void setIdArea(IdArea idArea) {
        this.idArea = idArea;
    }

}