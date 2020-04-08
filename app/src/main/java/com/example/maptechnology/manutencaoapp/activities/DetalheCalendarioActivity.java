package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.models.Atividade;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalheCalendarioActivity extends AppCompatActivity {

    String url;
    Gson gson;
    RetrofitClass apiService;
    Retrofit retrofit;
    Atividade calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_calendario);

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

        Intent i = getIntent();
        calendario = (Atividade) i.getSerializableExtra("calendario");

        TextView txtManutencao = (TextView) findViewById(R.id.txtResponsavel);
        TextView txtItem = (TextView) findViewById(R.id.txtItem);
        TextView txtData = (TextView) findViewById(R.id.txtData);
        TextView txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        TextView txtcusto = (TextView) findViewById(R.id.txtCusto);
        TextView txtResponsavel = (TextView) findViewById(R.id.txtResponsavel);
        EditText edtObservacao = (EditText) findViewById(R.id.edtObservacao);
        Button btnEncerrar = (Button) findViewById(R.id.btnEncerrar);

        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamadaAlteraCalendario();
            }
        });


        txtManutencao.setText(calendario.getManutencao().getDescricao());
        if(calendario.getIdConjunto() != null){
            txtItem.setText("Vazio");
        }else if (calendario.getIdSubConjunto() != null){
            txtItem.setText(calendario.getIdSubConjunto().getNome().toString());
        }else{
            txtItem.setText("Vazio");
        }
        txtData.setText(calendario.getDataManutencao());
        txtDescricao.setText(calendario.getDescricao());
        txtcusto.setText("R$ 48,00");
        txtResponsavel.setText(calendario.getResponsavel().getUsername());




    }

    public void chamadaAlteraCalendario(){

        Call<Void> call2 = apiService.alteraStatusCalendario(2,calendario.getId());

        call2.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));

                if (response.message().equals("OK")) {

                    Toast.makeText(getApplicationContext(),"Manutenção Finalizada", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    setResult(CommonStatusCodes.SUCCESS,intent);
                    //encerra activity
                    finish();

                } else {

                    Toast.makeText(getApplicationContext(), "Erro ao Finalizar", Toast.LENGTH_SHORT).show();
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
