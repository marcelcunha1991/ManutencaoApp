package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapterAtividade;
import com.example.maptechnology.manutencaoapp.application.AppApplication;
import com.example.maptechnology.manutencaoapp.models.Atividade;
import com.example.maptechnology.manutencaoapp.models.Atividades;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtividadesPorOrdemActivity extends AppCompatActivity {

    ListView listView;
    private static CustomAdapterAtividade adapter;
    ArrayList<Atividade> dataModels;
    String url;
    Gson gson;
    Retrofit retrofit;
    RetrofitClass apiService;
    int idOrdem;
    Atividades lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_por_ordem);

        Intent i = getIntent();
        idOrdem = i.getIntExtra("idOrdem",0);

        listView = (ListView) findViewById(R.id.list);

        setTitle("Atividades da Ordem");

        FloatingActionButton fab = findViewById(R.id.fab_atividade);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),InsereAtividadesNaOrdemActivity.class);
                i.putExtra("idOrdem",idOrdem);
                startActivity(i);

            }
        });

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

        chamadaAtividadesPorordem();


    }

    public void listNotifyChange(){
        adapter.notifyDataSetChanged();
    }


    public void chamadaAtividadesPorordem(){

        Call<Atividades> call2 = apiService.atividadesPorOrdem(idOrdem);

        call2.enqueue(new Callback<Atividades>() {

            @Override
            public void onResponse(Call<Atividades> call, retrofit2.Response<Atividades> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));

                if (response.message().equals("OK")) {
                    lista = response.body();
                    adapter = new CustomAdapterAtividade(lista.getAtividades(), getApplicationContext(),gson,apiService,retrofit,AtividadesPorOrdemActivity.this);
                    listView.setAdapter(adapter);

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Atividades> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_atividades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.atualizar) {

            chamadaAtividadesPorordem();
            return true;

        }else if(id == R.id.finalizarOrdem){

            if(adapter.getAtividadesAbertas() > 0 ){

                Toast.makeText(getApplicationContext(), "Você possui atividades abertas, tente finalizar as mesmas " +
                        "e atualizar a tela para finalizar a ordem", Toast.LENGTH_LONG).show();
            }else{

                Intent i = new Intent(getApplicationContext(), CincoPorquesActivity.class);
                i.putExtra("idOrdem",idOrdem);
                startActivity(i);
                this.finish();
            }

        }else if(id == R.id.editarOrdem){

            if (((AppApplication) this.getApplication()).getTipoUsuario().equals("3")){

                Toast.makeText(getApplicationContext(),"Você não tem autorização para editar a ordem",Toast.LENGTH_SHORT).show();

            }else{

                Intent i = new Intent(getApplicationContext(), CriarOrdemActivity.class);
                i.putExtra("idOrdem", idOrdem);
                startActivity(i);

            }


        }

        return super.onOptionsItemSelected(item);
    }

}
