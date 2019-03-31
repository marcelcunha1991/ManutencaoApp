package com.example.maptechnology.manutencaoapp.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapter;
import com.example.maptechnology.manutencaoapp.models.Calendario;
import com.example.maptechnology.manutencaoapp.models.Calendarios;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtividadesDiaActivity extends AppCompatActivity {

    ListView listView;
    private static CustomAdapter adapter;
    ArrayList<Calendario> dataModels;
    String url;
    Gson gson;
    RetrofitClass apiService;
    Calendarios lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        listView = (ListView) findViewById(R.id.list);


        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        url = "http://" + pref.getString("ip", "") + "/";

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(RetrofitClass.class);


        Call<Calendarios> call2 = apiService.detalheCalendario(returnData());

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
            Toast.makeText(this, "Action clicked 1", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.atualizar) {

                Toast.makeText(this, "Action clicked 2", Toast.LENGTH_LONG).show();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

}