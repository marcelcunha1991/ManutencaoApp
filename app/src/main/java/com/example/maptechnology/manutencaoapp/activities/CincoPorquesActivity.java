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
//    EditText edtPorque1;
//    EditText edtPorque2;
//    EditText edtPorque3;
//    EditText edtPorque4;
//    EditText edtPorque5;
    Spinner edtPorque1;
    Spinner edtPorque2;
    Spinner edtPorque3;
    Spinner edtPorque4;
    Spinner edtPorque5;
    Porques porques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_porques);

        Intent i = getIntent();
        idOrdem = i.getIntExtra("idOrdem",0);

//        edtPorque1 = (EditText) findViewById(R.id.edtPorque1);
//        edtPorque2 = (EditText) findViewById(R.id.edtPorque2);
//        edtPorque3 = (EditText) findViewById(R.id.edtPorque3);
//        edtPorque4 = (EditText) findViewById(R.id.edtPorque4);
//        edtPorque5 = (EditText) findViewById(R.id.edtPorque5)
        edtPorque1 = (Spinner) findViewById(R.id.spnPorque01);
        edtPorque2 = (Spinner) findViewById(R.id.spnPorque02);
        edtPorque3 = (Spinner) findViewById(R.id.spnPorque03);
        edtPorque4 = (Spinner) findViewById(R.id.spnPorque04);
        edtPorque5 = (Spinner) findViewById(R.id.spnPorque05);


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


        Call<Porques> call2 = apiService.getPorques();

        call2.enqueue(new Callback<Porques>() {

            @Override
            public void onResponse(Call<Porques> call, retrofit2.Response<Porques> response) {
                int statusCode = response.code();
                porques= response.body();

                ArrayList<String> listaAreas = new ArrayList<String>();

                Log.d("Retrofit ", String.valueOf(statusCode));


                if (response.message().equals("OK")) {

                    for (Porque falha: porques.getPorque()){

                        listaAreas.add(falha.getDescricao());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, listaAreas);

                    edtPorque1.setAdapter(dataAdapter);
                    edtPorque2.setAdapter(dataAdapter);
                    edtPorque3.setAdapter(dataAdapter);
                    edtPorque4.setAdapter(dataAdapter);
                    edtPorque5.setAdapter(dataAdapter);

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível Preencher o Spinner de Áreas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Porques> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });




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

//        Call<CincoPorques> call2 = apiService.criarCincoPorques(idOrdem,
//                edtPorque1.getSelectedItem().toString(),
//                edtPorque2.getSelectedItem().toString(),
//                edtPorque3.getSelectedItem().toString(),
//                edtPorque4.getSelectedItem().toString(),
//                edtPorque5.getSelectedItem().toString());

        Call<CincoPorques> call2 = apiService.criarCincoPorques(idOrdem,
                porques.getPorque().get(edtPorque1.getSelectedItemPosition()).getId(),
                porques.getPorque().get(edtPorque2.getSelectedItemPosition()).getId(),
                porques.getPorque().get(edtPorque3.getSelectedItemPosition()).getId(),
                porques.getPorque().get(edtPorque4.getSelectedItemPosition()).getId(),
                porques.getPorque().get(edtPorque5.getSelectedItemPosition()).getId());

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

        if(!edtPorque1.getSelectedItem().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque2.getSelectedItem().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque3.getSelectedItem().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque4.getSelectedItem().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }
        if(!edtPorque5.getSelectedItem().toString().equals("")){
            numerosPreenchidos = numerosPreenchidos + 1;
        }

        if(numerosPreenchidos >= 2){
            return true;
        }else{
            return false;
        }

    }
}
