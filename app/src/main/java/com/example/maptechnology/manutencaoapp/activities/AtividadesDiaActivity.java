package com.example.maptechnology.manutencaoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapter;
import com.example.maptechnology.manutencaoapp.models.Calendario;
import com.example.maptechnology.manutencaoapp.models.CalendarioDetalhe;
import com.example.maptechnology.manutencaoapp.models.Calendarios;
import com.example.maptechnology.manutencaoapp.qrcodereader.barcode.BarcodeCaptureActivity;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtividadesDiaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    private static CustomAdapter adapter;
    ArrayList<Calendario> dataModels;
    String url;
    Gson gson;
    RetrofitClass apiService;
    Calendarios lista;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(AtividadesDiaActivity.this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),CriarCalendarioActivity.class);
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

        chamadaDetalheCalendario(returnData());


    }



    public String returnData() {
        String mes;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if ((calendar.get(Calendar.MONTH) + 1) < 10) {
            mes = "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1);
        } else {
            mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }


        String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));


        Log.d("dia:", dia + "/" + mes + "/");

        return dia + "/" + mes + "/";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.calendario) {
            Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
            startActivityForResult(intent, 2);
            return true;
        } else if (id == R.id.qrcode) {

            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, 1);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, 0);

            startActivityForResult(intent, 1);
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Calendario item = adapter.getCell(i);

        Intent intent = new Intent(getApplicationContext(),DetalheCalendarioActivity.class);
        intent.putExtra("calendario",item);
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {

                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Log.d("Valor", "Barcode read: " + barcode.displayValue.toString());

                    Call<CalendarioDetalhe> call3 = apiService.detalheCalendario(barcode.displayValue);

                    call3.enqueue(new Callback<CalendarioDetalhe>() {

                        @Override
                        public void onResponse(Call<CalendarioDetalhe> call, retrofit2.Response<CalendarioDetalhe> response) {
                            int statusCode = response.code();
                            Log.d("Retrofit ", String.valueOf(statusCode));
                            CalendarioDetalhe calendario = response.body();

                            if (response.message().equals("OK")) {

                                Intent intent = new Intent(getApplicationContext(),DetalheCalendarioActivity.class);
                                intent.putExtra("calendario",calendario.getCalendario());
                                startActivity(intent);

                            } else {

                                Toast.makeText(getApplicationContext(), "Erro de Login. Verifique Matrícula e Senha e tente outra vez.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CalendarioDetalhe> call, Throwable t) {
                            // Log error here since request failed
                            Log.d("error", t.toString());
                        }
                    });




                } else {
                    Log.d("Valor", "No barcode captured, intent data is null");
                }

        }else if(requestCode == 2 && resultCode == CommonStatusCodes.SUCCESS){
            String date = data.getStringExtra("data");
            Log.d("data", date);

            chamadaDetalheCalendario(date);

        }else if(requestCode == 3 && resultCode == CommonStatusCodes.SUCCESS){
            chamadaDetalheCalendario(returnData());
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void chamadaDetalheCalendario(String data){

        Call<Calendarios> call2 = apiService.detalheCalendarioPorData(data);

        call2.enqueue(new Callback<Calendarios>() {

            @Override
            public void onResponse(Call<Calendarios> call, retrofit2.Response<Calendarios> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                lista = response.body();

                if (response.message().equals("OK")) {

                    adapter = new CustomAdapter(lista.getCalendario(), getApplicationContext());
                    listView.setAdapter(adapter);


                } else {

                    Toast.makeText(getApplicationContext(), "Erro de Login. Verifique Matrícula e Senha e tente outra vez.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Calendarios> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }


}