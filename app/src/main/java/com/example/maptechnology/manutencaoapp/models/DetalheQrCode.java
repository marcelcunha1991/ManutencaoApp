package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalheQrCode {

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
    private Object hierarquia;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("mtbfMeta")
    @Expose
    private String mtbfMeta;
    @SerializedName("mtbf")
    @Expose
    private String mtbf;
    @SerializedName("mttrMeta")
    @Expose
    private String mttrMeta;
    @SerializedName("mttr")
    @Expose
    private String mttr;
    @SerializedName("cicloTp")
    @Expose
    private Object cicloTp;
    @SerializedName("NumeroCiclos")
    @Expose
    private Object numeroCiclos;
    @SerializedName("enumTp")
    @Expose
    private Object enumTp;
    @SerializedName("imageUrl")
    @Expose
    private Object imageUrl;
    @SerializedName("ewo")
    @Expose
    private Object ewo;
    @SerializedName("coordenadas")
    @Expose
    private Object coordenadas;
    @SerializedName("idEstoque")
    @Expose
    private Integer idEstoque;
    @SerializedName("ultimoRevisor")
    @Expose
    private Object ultimoRevisor;
    @SerializedName("idSubConjunto")
    @Expose
    private Integer idSubConjunto;

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

    public String getMtbf() {
        return mtbf;
    }

    public void setMtbf(String mtbf) {
        this.mtbf = mtbf;
    }

    public String getMttrMeta() {
        return mttrMeta;
    }

    public void setMttrMeta(String mttrMeta) {
        this.mttrMeta = mttrMeta;
    }

    public String getMttr() {
        return mttr;
    }

    public void setMttr(String mttr) {
        this.mttr = mttr;
    }

    public Object getCicloTp() {
        return cicloTp;
    }

    public void setCicloTp(Object cicloTp) {
        this.cicloTp = cicloTp;
    }

    public Object getNumeroCiclos() {
        return numeroCiclos;
    }

    public void setNumeroCiclos(Object numeroCiclos) {
        this.numeroCiclos = numeroCiclos;
    }

    public Object getEnumTp() {
        return enumTp;
    }

    public void setEnumTp(Object enumTp) {
        this.enumTp = enumTp;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getEwo() {
        return ewo;
    }

    public void setEwo(Object ewo) {
        this.ewo = ewo;
    }

    public Object getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Object coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Object getUltimoRevisor() {
        return ultimoRevisor;
    }

    public void setUltimoRevisor(Object ultimoRevisor) {
        this.ultimoRevisor = ultimoRevisor;
    }

    public Integer getIdSubConjunto() {
        return idSubConjunto;
    }

    public void setIdSubConjunto(Integer idSubConjunto) {
        this.idSubConjunto = idSubConjunto;
    }

}