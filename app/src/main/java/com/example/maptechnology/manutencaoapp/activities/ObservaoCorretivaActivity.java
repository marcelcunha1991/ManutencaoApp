package com.example.maptechnology.manutencaoapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.application.AppApplication;
import com.example.maptechnology.manutencaoapp.models.Estoque;
import com.example.maptechnology.manutencaoapp.models.Estoque_;
import com.example.maptechnology.manutencaoapp.models.Linha;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObservaoCorretivaActivity extends AppCompatActivity {

    EditText edtObservacao;
    EditText edtQuantidade;
    Spinner spnItemEstoque;
    ListView lstItensConsumidos;
    ImageButton btnAdicionar;

    String url;
    Gson gson;
    Retrofit retrofit;
    RetrofitClass apiService;

    ArrayList<String> listaDeItens = new ArrayList<String>();
    ArrayList<Linha> listaDeEstoque = new ArrayList<Linha>();

    Estoque estoque;

    ArrayAdapter<String> adapter;

    int idAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observao_corretiva);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode

        Intent i = getIntent();
        idAtividade = i.getIntExtra("idAtividade",0);

        url = "http://" + pref.getString("ip", "") + "/";

        edtObservacao = (EditText) findViewById(R.id.edtObservacao);
        edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
        spnItemEstoque = (Spinner) findViewById(R.id.spnItemEstoque);
        lstItensConsumidos = (ListView) findViewById(R.id.list);
        btnAdicionar = (ImageButton) findViewById(R.id.btnAdicionar);

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(RetrofitClass.class);

        carregaItensEstque();

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listaDeItens){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);

                    text.setTextColor(Color.BLACK);

                return view;
            }
        };

        lstItensConsumidos.setAdapter(adapter);


        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Linha linha = new Linha();
                linha.setEstoque(estoque.getEstoque().get(spnItemEstoque.getSelectedItemPosition()));
                linha.setQuantidade(Integer.valueOf(edtQuantidade.getText().toString()));

                listaDeEstoque.add(linha);
                listaDeItens.add(estoque.getEstoque().get(spnItemEstoque.getSelectedItemPosition()).getDescricao() + " X " + Integer.valueOf(edtQuantidade.getText().toString()) );

                adapter.notifyDataSetChanged();

                edtQuantidade.setText("");



            }
        });

        carregaItensEstque();

    }

    private void carregaItensEstque() {

        Call<Estoque> call2 = apiService.getEstoque();

        call2.enqueue(new Callback<Estoque>() {

            @Override
            public void onResponse(Call<Estoque> call, retrofit2.Response<Estoque> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                estoque = response.body();

                if (response.message().equals("OK")) {
                    List<String> itensEstoque = new ArrayList<String>();
                    for(Estoque_ item : estoque.getEstoque()){

                        itensEstoque.add(item.getDescricao());

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, itensEstoque);

                    spnItemEstoque.setAdapter(dataAdapter);


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Estoque> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_observacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.salvarObservacao) {

            salvarObservacao();

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void salvarObservacao() {

        for(int i=0;i<listaDeEstoque.size();i++){

            Call<Void> call2 = apiService.itensCorretivosCriar(
                    idAtividade,
                    listaDeEstoque.get(i).getEstoque().getId(),
                    listaDeEstoque.get(i).getQuantidade());

            call2.enqueue(new Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                    int statusCode = response.code();
                    Log.d("Retrofit ", String.valueOf(statusCode));

                    if (response.message().equals("OK")) {

                        Toast.makeText(getApplicationContext(), "Itens Adicionados", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Log error here since request failed
                    Log.d("error", t.toString());
                }
            });
        }


    }

}
