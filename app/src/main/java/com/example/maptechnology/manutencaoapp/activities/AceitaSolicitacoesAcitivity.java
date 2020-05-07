package com.example.maptechnology.manutencaoapp.activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapterAceitaAtividades;
import com.example.maptechnology.manutencaoapp.models.OrdensDoDia;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AceitaSolicitacoesAcitivity extends AppCompatActivity {

    ListView listView;
    String url;
    Gson gson;
    RetrofitClass apiService;
    Retrofit retrofit;
    SharedPreferences pref;
    OrdensDoDia lista;
    private static CustomAdapterAceitaAtividades adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceita_solicitacoes_acitivity);
        pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode

        listView = (ListView) findViewById(R.id.list);

        setTitle("Solicitações de Manutenção");

        final SharedPreferences.Editor editor = pref.edit();

        url = "http://" + pref.getString("ip", "") + "/";

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(RetrofitClass.class);

        AceitaSolicitacaoDeOrdem();
    }

    public void AceitaSolicitacaoDeOrdem(){

        Call<OrdensDoDia> call2 = apiService.ordensComoSolicitacoes();

        call2.enqueue(new Callback<OrdensDoDia>() {

            @Override
            public void onResponse(Call<OrdensDoDia> call, retrofit2.Response<OrdensDoDia> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                lista = response.body();

                if (response.message().equals("OK")) {

                    adapter = new CustomAdapterAceitaAtividades(lista.getOrdem(), getApplicationContext(),gson,apiService,retrofit,AceitaSolicitacoesAcitivity.this);
                    listView.setAdapter(adapter);


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrdensDoDia> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }
}
