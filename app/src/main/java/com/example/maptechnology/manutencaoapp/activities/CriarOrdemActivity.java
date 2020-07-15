package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapterOrdem;
import com.example.maptechnology.manutencaoapp.models.Areas;
import com.example.maptechnology.manutencaoapp.models.Conjunto;
import com.example.maptechnology.manutencaoapp.models.Conjuntos;
import com.example.maptechnology.manutencaoapp.models.Falha;
import com.example.maptechnology.manutencaoapp.models.Falhas;
import com.example.maptechnology.manutencaoapp.models.IdArea;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;
import com.example.maptechnology.manutencaoapp.models.OrdemManutencao;
import com.example.maptechnology.manutencaoapp.models.OrdensDoDia;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriarOrdemActivity extends AppCompatActivity {

    EditText edtDescricao;
    EditText edtFrequencia;
    Spinner spnFrequencia;
    Spinner spnTipo;
    EditText edtFalha;
    Spinner spnConjunto;
    Spinner spnArea;
    Switch swithch1;
    Switch isParada;
    TextView textView22;
    TextView textView23;
    Falhas falhas;
    OrdemManutencao ordem;
    Areas areas;
    Conjuntos conjuntosPorArea;

    String url;
    Gson gson;
    RetrofitClass apiService;
    Retrofit retrofit;
    int user_id;
    String hierarquia;
    HashMap<String,Integer> tipos;
    int telaPrevia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_ordem);

        tipos = new HashMap<String,Integer>();

        int idOrdem = getIntent().getIntExtra("idOrdem",0);

        SharedPreferences preferences=getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);

        url = "http://" + preferences.getString("ip", "") + "/";

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(RetrofitClass.class);

        tipos.put( "Preventiva", 1);
        tipos.put( "Corretiva", 2);
        tipos.put( "Preditiva", 3);

        user_id = preferences.getInt(getString(R.string.user_id),0);
        hierarquia = preferences.getString(getString(R.string.hierarquia),"");



        edtDescricao = (EditText) findViewById(R.id.edtOrdemDescricao);
        edtFrequencia = (EditText) findViewById(R.id.edtFrequencia);
        spnFrequencia = (Spinner) findViewById(R.id.spnFrenquencia);
        edtFalha = (EditText) findViewById(R.id.edtFalha);
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        spnConjunto = (Spinner) findViewById(R.id.spnConjunto);
        spnArea = (Spinner) findViewById(R.id.spnArea);
        swithch1 = (Switch) findViewById(R.id.switch1);
        isParada = (Switch) findViewById(R.id.isParada);

        textView22 = (TextView) findViewById(R.id.textView22);
        textView23 = (TextView) findViewById(R.id.textView23);

        textView22.setText("Linha: ");
        textView23.setText("Máquina: ");
        swithch1.setChecked(false) ;

        if (hierarquia.equals("3")){
            setTitle("Solicitação de Ordem");
            swithch1.setEnabled(false);
            spnTipo.setEnabled(false);
            edtFrequencia.setEnabled(false);
            spnFrequencia.setEnabled(false);


        }else{
            setTitle("Criação de Ordem");
        }

        swithch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false){
                    edtFrequencia.setEnabled(false);
                    spnFrequencia.setEnabled(false);

                }else{
                    edtFrequencia.setEnabled(true);
                    spnFrequencia.setEnabled(true);
                }
            }
        });

        ArrayAdapter<CharSequence> adapter_frequencia = ArrayAdapter.createFromResource(this,
                R.array.frequencia_list, android.R.layout.simple_spinner_item);

        spnFrequencia.setAdapter(adapter_frequencia);

        ArrayAdapter<CharSequence> adapter_tipo = ArrayAdapter.createFromResource(this,
                R.array.tipo_list, android.R.layout.simple_spinner_item);

        spnTipo.setAdapter(adapter_tipo);

        if (idOrdem == 0){
            telaPrevia = 1;
        }else{
            telaPrevia = 2;
            completarOrdem(idOrdem);
        }

        populaSpinnerFalhas();
        populaSpinnerArea();

        spnArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populaSpinnerConjuntos(areas.getArea().get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnConjunto.setEnabled(false);

    }

    private void completarOrdem( int idOrdem) {

        Call<OrdemManutencao> call2 = apiService.detalheOrdem(idOrdem);

        call2.enqueue(new Callback<OrdemManutencao>() {

            @Override
            public void onResponse(Call<OrdemManutencao> call, retrofit2.Response<OrdemManutencao> response) {
                int statusCode = response.code();
                ordem= response.body();

                Log.d("Retrofit ", String.valueOf(statusCode));

                if (response.message().equals("OK")) {

                        edtDescricao.setText(ordem.getOrdem().getDescricao());
                        spnTipo.setSelection(retornaPosicaoSpinnerFalhas(ordem.getOrdem().getFalha()));


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível criar a Ordem :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrdemManutencao> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });


    }

    public int retornaPosicaoSpinnerFalhas(String falha){

        for (int i = 0; i < falhas.getFalhas().size();i++){

            if(falhas.getFalhas().get(i).getDescricao().equals(falha)){

                return i;
            }
        }

        return 0;
    }

    public int retornaPosicaoSpinnerTipo(String falha){


        return 0;
    }


    private void populaSpinnerFalhas() {

        Call<Falhas> call2 = apiService.getFalhas();

        call2.enqueue(new Callback<Falhas>() {

            @Override
            public void onResponse(Call<Falhas> call, retrofit2.Response<Falhas> response) {
                int statusCode = response.code();
                falhas= response.body();

                ArrayList<String> listaFalhas = new ArrayList<String>();

                Log.d("Retrofit ", String.valueOf(statusCode));


                if (response.message().equals("OK")) {

                    for (Falha falha: falhas.getFalhas()){

                        listaFalhas.add(falha.getDescricao());
                    }

//                    if ()
//                    spnFalhas.setSelection(retornaPosicaoSpinnerFalhas(ordem.getOrdem().getFalha().getDescricao()));

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível criar a Ordem :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Falhas> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }

    private void populaSpinnerArea() {

        Call<Areas> call2 = apiService.getAreas();

        call2.enqueue(new Callback<Areas>() {

            @Override
            public void onResponse(Call<Areas> call, retrofit2.Response<Areas> response) {
                int statusCode = response.code();
                areas= response.body();

                ArrayList<String> listaAreas = new ArrayList<String>();

                Log.d("Retrofit ", String.valueOf(statusCode));


                if (response.message().equals("OK")) {

                    for (IdArea falha: areas.getArea()){

                        listaAreas.add(falha.getDescricao());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, listaAreas);

                    spnArea.setAdapter(dataAdapter);

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível Preencher o Spinner de Áreas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Areas> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }

    private void populaSpinnerConjuntos(int idArea) {

        Call<Conjuntos> call2 = apiService.detalheConjuntosPorArea(idArea);

        call2.enqueue(new Callback<Conjuntos>() {

            @Override
            public void onResponse(Call<Conjuntos> call, retrofit2.Response<Conjuntos> response) {
                int statusCode = response.code();
                conjuntosPorArea= response.body();

                ArrayList<String> listaConjuntosPorArea = new ArrayList<String>();

                Log.d("Retrofit ", String.valueOf(statusCode));


                if (response.message().equals("OK")) {

                    for (Conjunto conjunto: conjuntosPorArea.getConjuntos()){

                        listaConjuntosPorArea.add(conjunto.getNome());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, listaConjuntosPorArea);

//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnConjunto.setAdapter(dataAdapter);
                    spnConjunto.setEnabled(true);

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível Preencher o Spinner de Áreas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Conjuntos> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }




    //Método para retornar tempo em segundos para próxima manutenção
    public int returnDate(){

        int retorno = 0;

        if(edtFrequencia.getText().toString()!=null) {

            if (spnTipo.getSelectedItem().toString().equals("Minuto")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 60;
            } else if (spnTipo.getSelectedItem().toString().equals("Hora")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 3600;
            } else if (spnTipo.getSelectedItem().toString().equals("Dia")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 86400;
            } else if (spnTipo.getSelectedItem().toString().equals("Semana")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 604800;
            } else if (spnTipo.getSelectedItem().toString().equals("Dia")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 86400;
            } else if (spnTipo.getSelectedItem().toString().equals("Mês")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 2419200;
            } else if (spnTipo.getSelectedItem().toString().equals("Ano")) {
                retorno = Integer.valueOf(edtFrequencia.getText().toString()) * 29030400;
            }
        }

        return retorno;

    }

    public void ChamadaCriarOrdem(){

        Date currentTime = Calendar.getInstance().getTime();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Log.d("time ", String.valueOf(returnDate()));
        Log.d("tipo ", String.valueOf(tipos.get(spnTipo.getSelectedItem().toString())));
        Log.d("descricao", edtDescricao.getText().toString());
        Log.d("data ", simpleDateFormat.format(currentTime));
        Log.d("user_id ", String.valueOf(user_id));

        int frequenciaManutencao;
        boolean checkisParada = false;
        if(swithch1.isChecked()){
            frequenciaManutencao = returnDate();
        }else{
            frequenciaManutencao = 0;
        }
        final int status = ((hierarquia.equals("3")) ? 1 : 2);

        if(isParada.isChecked()){
            checkisParada = true;
        }
        Call<IdOrdem> call2 = apiService.criarOrdem(edtDescricao.getText().toString(),
               "cod"+currentTime.toString(),
                simpleDateFormat.format(currentTime),
                frequenciaManutencao,
                user_id,
                tipos.get(spnTipo.getSelectedItem().toString()),
                status,
                edtFalha.getText().toString(),
                areas.getArea().get(spnArea.getSelectedItemPosition()).getId(),
                conjuntosPorArea.getConjuntos().get(spnConjunto.getSelectedItemPosition()).getId(),
                checkisParada
                );

        call2.enqueue(new Callback<IdOrdem>() {

            @Override
            public void onResponse(Call<IdOrdem> call, retrofit2.Response<IdOrdem> response) {
                int statusCode = response.code();
                IdOrdem ordem = response.body();
                Log.d("Retrofit ", String.valueOf(statusCode));

                if (response.message().equals("OK")) {

                    if(status == 2) {
                        Intent i = new Intent(CriarOrdemActivity.this, InsereAtividadesNaOrdemActivity.class);
                        i.putExtra("idOrdem", ordem.getId());
                        i.putExtra("descricaoOrdem", ordem.getDescricao());
                        startActivity(i);

                        Toast.makeText(getApplicationContext(), "Ordem Criada com Sucesso!!! :)", Toast.LENGTH_SHORT).show();
                    }else{
                        finish();
                        Toast.makeText(getApplicationContext(), "Ordem Criada com Sucesso!!! :)", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível criar a Ordem :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IdOrdem> call, Throwable t) {
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.inserir) {
            ChamadaCriarOrdem();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
