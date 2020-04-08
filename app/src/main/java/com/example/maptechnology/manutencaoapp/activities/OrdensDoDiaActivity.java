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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.adapters.CustomAdapterOrdem;
import com.example.maptechnology.manutencaoapp.models.CalendarioDetalhe;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;
import com.example.maptechnology.manutencaoapp.models.OrdensDoDia;
import com.example.maptechnology.manutencaoapp.qrcodereader.barcode.BarcodeCaptureActivity;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdensDoDiaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    private static CustomAdapterOrdem adapter;
    String url;
    Gson gson;
    RetrofitClass apiService;
    OrdensDoDia lista;
    Retrofit retrofit;
    Boolean isFABOpen = false;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    Menu menu;
    String hierarquia;
    SharedPreferences pref;
     int retorno = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode
        hierarquia = pref.getString(getString(R.string.hierarquia),"");

        listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(OrdensDoDiaActivity.this);

        setTitle("Ordens de Manutenção");


        FloatingActionButton fab = findViewById(R.id.fab);
         fab1 = findViewById(R.id.fab1);
         fab2 = findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }


            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CriarOrdemActivity.class);
                startActivity(i);

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hierarquia.equals("3")){
                    Toast.makeText(getApplicationContext(), "Você não tem autorização para ver as ordens abertas", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplicationContext(),TodasAsOrdensActivity.class);
                    startActivity(i);
                }


            }
        });
//



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

        ChamadaOrdensDoDia(returnData());


    }

    private int checkSolicitacaoOrdem() {

        Call<OrdensDoDia> call2 = apiService.ordensComoSolicitacoes();

        call2.enqueue(new Callback<OrdensDoDia>() {

            @Override
            public void onResponse(Call<OrdensDoDia> call, retrofit2.Response<OrdensDoDia> response) {
                int statusCode = response.code();
                OrdensDoDia ordens= response.body();

                if (response.message().equals("OK")) {

                    if(ordens.getOrdem().size() > 0) {

                        MenuItem item = menu.findItem(R.id.alert);

                        if (!hierarquia.equals("3")) {
                            item.setVisible(true);
                        }

                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível criar a Ordem :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrdensDoDia> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

        Log.d("retorno final", String.valueOf(retorno));
        return retorno;
    }


    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_155));

    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);

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
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.alert);

        item.setVisible(false);
        checkSolicitacaoOrdem();
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


        } else if (id == R.id.atualizar) {

            ChamadaOrdensDoDia(returnData());

            return true;


        }else if (id == R.id.alert) {


            ChamadaOrdensDoDia(returnData());

            return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        IdOrdem item = adapter.getCell(i);

        Intent intent = new Intent(getApplicationContext(),AtividadesPorOrdemActivity.class);
        intent.putExtra("idOrdem",item.getId());
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {

                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Log.d("Valor", "Barcode read: " + barcode.displayValue.toString());


                    Intent i =  new Intent(getApplicationContext(),QrCordeResultActivity.class);
                    i.putExtra("code",barcode.displayValue.toString());
                    startActivity(i);



                } else {
                    Log.d("Valor", "No barcode captured, intent data is null");
                }

        }else if(requestCode == 2 && resultCode == CommonStatusCodes.SUCCESS){
            String date = data.getStringExtra("data");
            Log.d("data", date);

            ChamadaOrdensDoDia(date);

        }else if(requestCode == 3 && resultCode == CommonStatusCodes.SUCCESS){
            ChamadaOrdensDoDia(returnData());
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void ChamadaOrdensDoDia(String data){

        Call<OrdensDoDia> call2 = apiService.detalheOrdemPorData(data);

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


}