package com.example.maptechnology.manutencaoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QrCodeResult {

    @SerializedName("ordemUltima")
    @Expose
    private OrdemUltima ordemUltima;
    @SerializedName("AtividadeProx")
    @Expose
    private String atividadeProx;
    @SerializedName("detalheQrCode")
    @Expose
    private DetalheQrCode detalheQrCode;
    @SerializedName("AtividadeUltimo")
    @Expose
    private String atividadeUltimo;
    @SerializedName("ordemProxima")
    @Expose
    private OrdemProxima ordemProxima;

    public OrdemUltima getOrdemUltima() {
        return ordemUltima;
    }

    public void setOrdemUltima(OrdemUltima ordemUltima) {
        this.ordemUltima = ordemUltima;
    }

    public String getAtividadeProx() {
        return atividadeProx;
    }

    public void setAtividadeProx(String atividadeProx) {
        this.atividadeProx = atividadeProx;
    }

    public DetalheQrCode getDetalheQrCode() {
        return detalheQrCode;
    }

    public void setDetalheQrCode(DetalheQrCode detalheQrCode) {
        this.detalheQrCode = detalheQrCode;
    }

    public String getAtividadeUltimo() {
        return atividadeUltimo;
    }

    public void setAtividadeUltimo(String atividadeUltimo) {
        this.atividadeUltimo = atividadeUltimo;
    }

    public OrdemProxima getOrdemProxima() {
        return ordemProxima;
    }

    public void setOrdemProxima(OrdemProxima ordemProxima) {
        this.ordemProxima = ordemProxima;
    }

}