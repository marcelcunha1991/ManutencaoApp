package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapterOrdem;
import com.example.maptechnology.manutencaoapp.models.OrdensDoDia;
import com.example.maptechnology.manutencaoapp.models.QrCodeResult;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QrCordeResultActivity extends AppCompatActivity {

    String url;
    Gson gson;
    RetrofitClass apiService;
    QrCodeResult lista;
    Retrofit retrofit;
    SharedPreferences pref;
    String codigo;

    TextView txtDescricao;
    TextView txtMtbfAtual;
    TextView txtMttrAtual;
    TextView txtProxManuten;
    TextView txtDataProx;
    TextView txtUltimManut;
    TextView txtDataUltimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_corde_result);

        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        txtMtbfAtual = (TextView) findViewById(R.id.txtMtbfAtual);
        txtMttrAtual = (TextView) findViewById(R.id.txtMttrAtual);
        txtProxManuten = (TextView) findViewById(R.id.txtProxManuten);
        txtDataProx = (TextView) findViewById(R.id.txtDataProx);
        txtUltimManut = (TextView) findViewById(R.id.txtUltimManut);
        txtDataUltimo = (TextView) findViewById(R.id.txtDataUltimo);

        codigo = getIntent().getStringExtra("code");
        pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode
        url = "http://" + pref.getString("ip", "") + "/";

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(RetrofitClass.class);
        Log.d("code :", codigo);
        carregaOrdem(codigo);

    }

    private void carregaOrdem(String data) {

        Call<QrCodeResult> call2 = apiService.detalheQrCode(data);

        call2.enqueue(new Callback<QrCodeResult>() {

            @Override
            public void onResponse(Call<QrCodeResult> call, retrofit2.Response<QrCodeResult> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                lista = response.body();

                if (response.message().equals("OK")) {

                    txtDescricao.setText(lista.getDetalheQrCode().getNome());
                    txtMtbfAtual.setText(lista.getDetalheQrCode().getMtbf());
                    txtMttrAtual.setText(lista.getDetalheQrCode().getMttr());

                    if(lista.getOrdemProxima() != null) txtProxManuten.setText(lista.getOrdemProxima().getDescricao());

                    if(lista.getAtividadeProx() != null)  txtDataProx. setText(lista.getAtividadeProx());

                    if(lista.getOrdemUltima() != null) txtUltimManut.setText(lista.getOrdemUltima().getDescricao());
                    if(lista.getAtividadeUltimo() != null) txtDataUltimo.setText(lista.getAtividadeUltimo());



                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<QrCodeResult> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

    }


}
