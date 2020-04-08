package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Peca implements Serializable {

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
    private String ultimaManutencao;
    @SerializedName("hierarquia")
    @Expose
    private String hierarquia;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("mtbf")
    @Expose
    private String mtbf;
    @SerializedName("mttr")
    @Expose
    private String mttr;
    @SerializedName("codigoSap")
    @Expose
    private String codigoSap;
    @SerializedName("cicloTp")
    @Expose
    private String cicloTp;
    @SerializedName("NumeroCiclos")
    @Expose
    private int numeroCiclos;
    @SerializedName("enumTp")
    @Expose
    private String enumTp;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("ewo")
    @Expose
    private String ewo;
    @SerializedName("idEstoque")
    @Expose
    private IdEstoque idEstoque;
    @SerializedName("ultimoRevisor")
    @Expose
    private Responsavel ultimoRevisor;
    @SerializedName("idArea")
    @Expose
    private IdArea idArea;
    @SerializedName("subConjunto")
    @Expose
    private SubConjunto subConjunto;

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

    public String getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(String ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMtbf() {
        return mtbf;
    }

    public void setMtbf(String mtbf) {
        this.mtbf = mtbf;
    }

    public String getMttr() {
        return mttr;
    }

    public void setMttr(String mttr) {
        this.mttr = mttr;
    }

    public String getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(String codigoSap) {
        this.codigoSap = codigoSap;
    }

    public String getCicloTp() {
        return cicloTp;
    }

    public void setCicloTp(String cicloTp) {
        this.cicloTp = cicloTp;
    }

    public int getNumeroCiclos() {
        return numeroCiclos;
    }

    public void setNumeroCiclos(int numeroCiclos) {
        this.numeroCiclos = numeroCiclos;
    }

    public String getEnumTp() {
        return enumTp;
    }

    public void setEnumTp(String enumTp) {
        this.enumTp = enumTp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEwo() {
        return ewo;
    }

    public void setEwo(String ewo) {
        this.ewo = ewo;
    }

    public IdEstoque getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(IdEstoque idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Responsavel getUltimoRevisor() {
        return ultimoRevisor;
    }

    public void setUltimoRevisor(Responsavel ultimoRevisor) {
        this.ultimoRevisor = ultimoRevisor;
    }

    public IdArea getIdArea() {
        return idArea;
    }

    public void setIdArea(IdArea idArea) {
        this.idArea = idArea;
    }

    public SubConjunto getSubConjunto() {
        return subConjunto;
    }

    public void setSubConjunto(SubConjunto subConjunto) {
        this.subConjunto = subConjunto;
    }

}