package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  IdArea {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("codigo")
    @Expose
    private String codigo;
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
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("temFilho")
    @Expose
    private Boolean temFilho;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Boolean getTemFilho() {
        return temFilho;
    }

    public void setTemFilho(Boolean temFilho) {
        this.temFilho = temFilho;
    }

}