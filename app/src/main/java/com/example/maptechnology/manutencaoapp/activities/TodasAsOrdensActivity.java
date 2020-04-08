package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapterOrdem;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;
import com.example.maptechnology.manutencaoapp.models.OrdensDoDia;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodasAsOrdensActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Gson gson;
    RetrofitClass apiService;
    OrdensDoDia lista;
    Retrofit retrofit;
    String url;
    private static CustomAdapterOrdem adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas_as_ordens);

        listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(TodasAsOrdensActivity.this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode
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
        Call<OrdensDoDia> call2 = apiService.ordensAtivas();


        call2.enqueue(new Callback<OrdensDoDia>() {

            @Override
            public void onResponse(Call<OrdensDoDia> call, retrofit2.Response<OrdensDoDia> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                lista = response.body();

                if (response.message().equals("OK")) {

                    adapter = new CustomAdapterOrdem(lista.getOrdem(), getApplicationContext());
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        IdOrdem item = adapter.getCell(position);

        Intent intent = new Intent(getApplicationContext(),AtividadesPorOrdemActivity.class);
        intent.putExtra("idOrdem",item.getId());
        startActivityForResult(intent,3);

    }
}
