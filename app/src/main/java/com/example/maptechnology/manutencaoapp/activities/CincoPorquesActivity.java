package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.models.CincoPorques;
import com.example.maptechnology.manutencaoapp.models.Falha;
import com.example.maptechnology.manutencaoapp.models.Falhas;
import com.example.maptechnology.manutencaoapp.models.Porque;
import com.example.maptechnology.manutencaoapp.models.Porques;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CincoPorquesActivity extends AppCompatActivity {

    String url;
    Gson gson;
    Retrofit retrofit;
    RetrofitClass apiService;
    int idOrdem;
    EditText edtPorque1;
    EditText edtPorque2;
    EditText edtPorque3;
    EditText edtPorque4;
    EditText edtPorque5;
    Porques porques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_porques);

        Intent i = getIntent();
        idOrdem = i.getIntExtra("idOrdem",0);

        edtPorque1 = (EditText) findViewById(R.id.edtPorque1);
        edtPorque2 = (EditText) findViewById(R.id.edtPorque2);
        edtPorque3 = (EditText) findViewById(R.id.edtPorque3);
        edtPorque4 = (EditText) findViewById(R.id.edtPorque4);
        edtPorque5 = (EditText) findViewById(R.id.edtPorque5);


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


    }


    public void finalizaOrdem(){

        Call<Void> call2 = apiService.alteraOrdemStatus(idOrdem,4);

        call2.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));

                if (response.message().equals("OK")) {

                    Toast.makeText(getApplicationContext(), "Ordem finalizada com Sucesso!", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Erro as Finalizar a Ordem!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }

    public void salvarCincoPorques(){

        Call<CincoPorques> call2 = apiService.criarCincoPorques(idOrdem,
                edtPorque1.getText().toString(),
                edtPorque2.getText().toString(),
                edtPorque3.getText().toString(),
                edtPorque4.getText().toString(),
                edtPorque5.getText().toString());

        call2.enqueue(new Callback<CincoPorques>() {

            @Override
            public void onResponse(Call<CincoPorques> call, retrofit2.Response<CincoPorques> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));

                if (response.message().equals("OK")) {

                    Toast.makeText(getApplicationContext(), "Porquês adicionados com sucesso", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Erro ao inserir os porquês", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CincoPorques> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insere_atividades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.inserir) {

            if(avaliaEdtProntosParaSalvar()){
                salvarCincoPorques();
                finalizaOrdem();
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Complete pelo menos os dois primeiros campos de escrita", Toast.LENGTH_LONG).show();
            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean avaliaEdtProntosParaSalvar(){

        int numerosPreenchidos = 0;

        if(!edtPorque1.getText().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque2.getText().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque3.getText().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque4.getText().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque5.getText().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }

        if(numerosPreenchidos >= 2){
            return true;
        }else{
            return false;
        }

    }
}
