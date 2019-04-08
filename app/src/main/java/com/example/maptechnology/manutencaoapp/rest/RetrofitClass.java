package com.example.maptechnology.manutencaoapp.rest;

import com.example.maptechnology.manutencaoapp.models.Calendario;
import com.example.maptechnology.manutencaoapp.models.CalendarioDetalhe;
import com.example.maptechnology.manutencaoapp.models.Calendarios;
import com.example.maptechnology.manutencaoapp.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by MAPTECHNOLOGY on 22/02/2019.
 */

public interface RetrofitClass {

    @FormUrlEncoded
    @POST("usuario/criar/")
    Call<Usuario> cadastraUsuario(@Field("nome") String nome ,
                               @Field("dataCriacao") String dataCriacao,
                               @Field("matricula") String matricula,
                               @Field("senha") String senha,
                               @Field("hierarquia") String hierarquia,
                               @Field("status") String status,
                               @Field("valorhh") String valorhh

    );

    @FormUrlEncoded
    @POST("usuario/login/")
    Call<Usuario> logar(@Field("matricula") String matricula ,
                                  @Field("senha") String senha


    );


    @FormUrlEncoded
    @POST("calendario/detalheCalendarioPorData/")
    Call<Calendarios> detalheCalendarioPorData(@Field("dia") String dia);


    @FormUrlEncoded
    @POST("calendario/detalheCalendario/")
    Call<CalendarioDetalhe> detalheCalendario(@Field("id") String id);

    @FormUrlEncoded
    @POST("calendario/alteraCalendario/")
    Call<Void> alteraStatusCalendario(@Field("status") int status,@Field("id") int id);
}
