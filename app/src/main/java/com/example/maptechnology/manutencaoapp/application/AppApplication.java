package com.example.maptechnology.manutencaoapp.application;

import android.app.Application;

public class AppApplication extends Application {

    private String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
