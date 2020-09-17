package com.example.maptechnology.manutencaoapp.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.models.AllUsers;
import com.example.maptechnology.manutencaoapp.models.Atividade;
import com.example.maptechnology.manutencaoapp.models.Atividades;
import com.example.maptechnology.manutencaoapp.models.Conjunto;
import com.example.maptechnology.manutencaoapp.models.Conjuntos;
import com.example.maptechnology.manutencaoapp.models.Peca;
import com.example.maptechnology.manutencaoapp.models.Pecas;
import com.example.maptechnology.manutencaoapp.models.SubConjunto;
import com.example.maptechnology.manutencaoapp.models.Manutencao;
import com.example.maptechnology.manutencaoapp.models.Manutencoes;
import com.example.maptechnology.manutencaoapp.models.SubConjuntos;
import com.example.maptechnology.manutencaoapp.models.User;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsereAtividadesNaOrdemActivity extends AppCompatActivity {

    int idOrdem;
    int conjunto;
    String descricaoOrdem;
    ListView listView;
    String url;
    Gson gson;
    Manutencoes manutencoes;
    Conjuntos conjuntos;
    Pecas pecas;
    SubConjuntos subConjuntos;
    Atividades atividades;
    AllUsers listaUsuarios;
    Retrofit retrofit;
    RetrofitClass apiService;

    ArrayAdapter<String> dataAdapterOnde;

    EditText edtDate;

    static final int PICK_CONTACT_REQUEST = 2;

    Spinner spnManutencao;
    Spinner spnOnde;
    Spinner spnResponsavel;
//    RadioButton rdbConjuntos;
    RadioButton rdbSubConjuntos;
    RadioButton rdbPecas;
    EditText edtHoraInicio;
    EditText edtHoraTermino;
    Switch swtJaRealizado;

    int tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_atividades_na_ordem);

        listView = (ListView) findViewById(R.id.list);
        spnManutencao = (Spinner) findViewById(R.id.spnManutencao);
        spnOnde = (Spinner) findViewById(R.id.spnItem);
        spnResponsavel = (Spinner) findViewById(R.id.spnResponsavel);
//        rdbConjuntos = (RadioButton) findViewById(R.id.rdbConjuntos);
        rdbSubConjuntos = (RadioButton) findViewById(R.id.rdbSubConjuntos);
        rdbPecas = (RadioButton) findViewById(R.id.rdbPecas);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtHoraInicio = (EditText) findViewById(R.id.edtHoraInicio);
        edtHoraTermino = (EditText) findViewById(R.id.edtHoraTermino);
        swtJaRealizado = (Switch) findViewById(R.id.swtJaRealizada);

        Intent intent = getIntent();

        idOrdem = intent.getIntExtra("idOrdem",0);
        tipo = intent.getIntExtra("tipo",0);
        conjunto = intent.getIntExtra("conjunto",0);

        descricaoOrdem = intent.getStringExtra("descricaoOrdem");

        setTitle(descricaoOrdem);

        rdbSubConjuntos.setChecked(true);

        swtJaRealizado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    edtHoraInicio.setEnabled(true);
                    edtHoraTermino.setEnabled(true);
                }else{
                    edtHoraInicio.setEnabled(false);
                    edtHoraTermino.setEnabled(false);
                }
            }
        });

        edtHoraInicio.setEnabled(false);


        edtHoraInicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(InsereAtividadesNaOrdemActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            edtHoraInicio.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            }
        });

        edtHoraTermino.setEnabled(false);

        edtHoraTermino.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(InsereAtividadesNaOrdemActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            edtHoraTermino.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            }
        });
        edtDate.requestFocus();



        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivityForResult(intent, 2);
            }
        });



        rdbSubConjuntos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    rdbSubConjuntos.setChecked(true);

                    carregaSubConjuntos();
                }
            }
        });

        rdbPecas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    rdbPecas.setChecked(true);
                    carregaPecas();
                }
            }
        });

        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.pref_key), 0); // 0 - for private mode

        url = "http://" + pref.getString("ip", "") + "/";

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(RetrofitClass.class);

        carregaAtividadesOrdem();




        ArrayList<String> listaParalavras = new ArrayList<String>();

        Toast.makeText(this, "idOrdem: "+ String.valueOf(idOrdem),Toast.LENGTH_SHORT).show();

        populaSpinners();

    }






    private void populaSpinners() {

        if (tipo == 2){


        Call<Manutencoes> call2 = apiService.detalheManutencaoCorretiva();

        call2.enqueue(new Callback<Manutencoes>() {

            @Override
            public void onResponse(Call<Manutencoes> call, retrofit2.Response<Manutencoes> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                manutencoes = response.body();

                if (response.message().equals("OK")) {
                    List<String> nomesManutencoes = new ArrayList<String>();
                    for(Manutencao manutencao:manutencoes.getManutencao()){

                        nomesManutencoes.add(manutencao.getDescricao());

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, nomesManutencoes);


                    spnManutencao.setAdapter(dataAdapter);


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Manutencoes> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

        }else{

            Call<Manutencoes> call2 = apiService.detalheManutencao();

            call2.enqueue(new Callback<Manutencoes>() {

                @Override
                public void onResponse(Call<Manutencoes> call, retrofit2.Response<Manutencoes> response) {
                    int statusCode = response.code();
                    Log.d("Retrofit ", String.valueOf(statusCode));
                    manutencoes = response.body();

                    if (response.message().equals("OK")) {
                        List<String> nomesManutencoes = new ArrayList<String>();
                        for(Manutencao manutencao:manutencoes.getManutencao()){

                            nomesManutencoes.add(manutencao.getDescricao());

                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.spinner_layout, nomesManutencoes);


                        spnManutencao.setAdapter(dataAdapter);


                    } else {

                        Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Manutencoes> call, Throwable t) {
                    // Log error here since request failed
                    Log.d("error", t.toString());
                }
            });

        }

        carregaSubConjuntos();


        Call<AllUsers> callResponsavel = apiService.listaUsuarios();

        callResponsavel.enqueue(new Callback<AllUsers>() {

            @Override
            public void onResponse(Call<AllUsers> call, retrofit2.Response<AllUsers> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                listaUsuarios = response.body();

                if (response.message().equals("OK")) {
                    List<String> nomesConjuntos = new ArrayList<String>();
                    for(User manutencao:listaUsuarios.getAllUsers()){

                        nomesConjuntos.add(manutencao.getNome());

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, nomesConjuntos);

                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnResponsavel.setAdapter(dataAdapter);


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllUsers> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });


    }

    private void carregaConjuntos(){

        Call<Conjuntos> call3 = apiService.detalheConjuntos();

        call3.enqueue(new Callback<Conjuntos>() {

            @Override
            public void onResponse(Call<Conjuntos> call, retrofit2.Response<Conjuntos> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                conjuntos = response.body();

                if (response.message().equals("OK")) {
                    List<String> nomesConjuntos = new ArrayList<String>();
                    for(Conjunto manutencao:conjuntos.getConjuntos()){

                        nomesConjuntos.add(manutencao.getNome());

                    }

                    dataAdapterOnde = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, nomesConjuntos);


                    spnOnde.setAdapter(dataAdapterOnde);


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Conjuntos> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

    }

    private void carregaPecas(){

        Call<Pecas> callPecas = apiService.detalhePecasPorConjunto(conjunto);

        callPecas.enqueue(new Callback<Pecas>() {

            @Override
            public void onResponse(Call<Pecas> call, retrofit2.Response<Pecas> response) {
                int statusCode = response.code();
                Log.d("Retrofit ", String.valueOf(statusCode));
                pecas = response.body();

                if (response.message().equals("OK")) {
                    List<String> nomesPecas = new ArrayList<String>();
                    for(Peca peca:pecas.getPecas()){

                        nomesPecas.add(peca.getNome());

                    }

                    dataAdapterOnde = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, nomesPecas);


                    spnOnde.setAdapter(dataAdapterOnde);


                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pecas> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

    }

    private void carregaSubConjuntos(){

        Call<SubConjuntos> callSubConjuntos = apiService.detalheSubConjuntosPorConjunto(conjunto);

        callSubConjuntos.enqueue(new Callback<SubConjuntos>() {

            @Override
            public void onResponse(Call<SubConjuntos> call, retrofit2.Response<SubConjuntos> response) {
                String statusCode = response.message();
                Log.d("Retrofit ", statusCode);
                subConjuntos = response.body();

                if (response.message().equals("OK")) {
                    List<String> nomesSubConjuntos = new ArrayList<String>();
                    for(SubConjunto manutencao:subConjuntos.getSubconjuntos()){

                        nomesSubConjuntos.add(manutencao.getNome().toString());

                    }

                    dataAdapterOnde = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout, nomesSubConjuntos);


                    dataAdapterOnde.notifyDataSetChanged();
                    spnOnde.setAdapter(dataAdapterOnde);



                } else {

                    Toast.makeText(getApplicationContext(), "Não foi possível acessar as Ordens de Manutenção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubConjuntos> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });

    }


    private void carregaAtividadesOrdem(){

        Call<Atividades> callSubConjuntos = apiService.atividadesPorOrdem(idOrdem);

        callSubConjuntos.enqueue(new Callback<Atividades>() {

            @Override
            public void onResponse(Call<Atividades> call, retrofit2.Response<Atividades> response) {
                String statusCode = response.message();
                Log.d("Retrofit ", statusCode);
                atividades = response.body();

                if (response.message().equals("OK")) {
                    List<String> nomesSubConjuntos = new ArrayList<String>();
                    for(Atividade atividade:atividades.getAtividades()){

                        if (tipo == 2){
                            nomesSubConjuntos.add(atividade.getManutencaoCorretiva().getDescricao() + "   Data: " + atividade.getDataManutencao() );
                        }else{
                            nomesSubConjuntos.add(atividade.getManutencao().getDescricao() + "   Data: " + atividade.getDataManutencao() );
                        }


                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, nomesSubConjuntos){

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {

                            View view = super.getView(position, convertView, parent);
                            TextView text = (TextView) view.findViewById(android.R.id.text1);


                            text.setTextColor(Color.BLACK);


                            return view;
                        }
                    };

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


    private void salvaAtividade(){

        String horaInicio = null;
        String horatermino = null;

        Call<Atividade> callSubConjuntos = null;

        if(swtJaRealizado.isChecked()) {

            Date currentTimeInicio = Calendar.getInstance().getTime();
            Date currentTimeTermino = Calendar.getInstance().getTime();

            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormatInicio = new SimpleDateFormat(pattern);

            currentTimeInicio.setHours(Integer.valueOf(edtHoraInicio.getText().toString().split(":")[0]));
            currentTimeInicio.setMinutes(Integer.valueOf(edtHoraInicio.getText().toString().split(":")[1]));

            horaInicio = simpleDateFormatInicio.format(currentTimeInicio);

            currentTimeTermino.setHours(Integer.valueOf(edtHoraTermino.getText().toString().split(":")[0]));
            currentTimeTermino.setMinutes(Integer.valueOf(edtHoraTermino.getText().toString().split(":")[1]));

            horatermino = simpleDateFormatInicio.format(currentTimeTermino);
        }

        if(rdbPecas.isChecked()){


        if(!swtJaRealizado.isChecked()){
            Log.d("Id da Manutencao: ", String.valueOf(manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId()));

            if (tipo == 2){
                callSubConjuntos = apiService.criarAtividadeAgendada(
                        edtDate.getText().toString(),
                        0,
                        listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                        idOrdem,
                        String.valueOf(pecas.getPecas().get(spnOnde.getSelectedItemPosition()).getId()),
                        null,
                        null,
                        manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getDescricao(),
                        manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId());
            }else{

                callSubConjuntos = apiService.criarAtividadeAgendada(
                        edtDate.getText().toString(),
                        manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId(),
                        listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                        idOrdem,
                        String.valueOf(pecas.getPecas().get(spnOnde.getSelectedItemPosition()).getId()),
                        null,
                        null,
                        spnManutencao.getSelectedItem().toString(),
                        0);

            }



        }else{

            if (tipo == 2){
                callSubConjuntos = apiService.criarAtividadeRealizada(
                        edtDate.getText().toString(),
                        0,
                        listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                        idOrdem,
                        String.valueOf(pecas.getPecas().get(spnOnde.getSelectedItemPosition()).getId()),
                        null,
                        null,
                        horaInicio,
                        horatermino,
                        4,
                        manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getDescricao(),
                        manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId()

                );

            }else{

                callSubConjuntos = apiService.criarAtividadeRealizada(
                        edtDate.getText().toString(),
                        manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId(),
                        listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                        idOrdem,
                        String.valueOf(pecas.getPecas().get(spnOnde.getSelectedItemPosition()).getId()),
                        null,
                        null,
                        horaInicio,
                        horatermino,
                        4,
                        spnManutencao.getSelectedItem().toString(),
                        0

                );

            }

        }

        }else if (rdbSubConjuntos.isChecked()) {

            Log.d("Id da Manutencao: ", String.valueOf(manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId()));

            if(!swtJaRealizado.isChecked()){

                if (tipo == 2){

                    callSubConjuntos = apiService.criarAtividadeAgendada(
                            edtDate.getText().toString(),
                            0,
                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                            idOrdem,
                            null,
                            null,
                            String.valueOf(subConjuntos.getSubconjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getDescricao(),
                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId()

                    );

                }else{
                    callSubConjuntos = apiService.criarAtividadeAgendada(
                            edtDate.getText().toString(),
                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId(),
                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                            idOrdem,
                            null,
                            null,
                            String.valueOf(subConjuntos.getSubconjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
                            spnManutencao.getSelectedItem().toString(),
                            0

                    );
                }

            }else{

                if (tipo == 2){
                    callSubConjuntos = apiService.criarAtividadeRealizada(
                            edtDate.getText().toString(),
                            0,
                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                            idOrdem,
                            null,
                            null,
                            String.valueOf(subConjuntos.getSubconjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
                            horaInicio,
                            horatermino,
                            4,
                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getDescricao(),
                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId()

                    );


                }else{
                    callSubConjuntos = apiService.criarAtividadeRealizada(
                            edtDate.getText().toString(),
                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId(),
                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
                            idOrdem,
                            null,
                            null,
                            String.valueOf(subConjuntos.getSubconjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
                            horaInicio,
                            horatermino,
                            4,
                            spnManutencao.getSelectedItem().toString(),
                            0

                    );
                }

            }


        }
//        else if (rdbConjuntos.isChecked() == true){
//
//            if(!swtJaRealizado.isChecked()){
//
//                if (tipo == 2){
//                    callSubConjuntos = apiService.criarAtividadeAgendada(
//                            edtDate.getText().toString(),
//                            0,
//                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
//                            idOrdem,
//                            null,
//                            String.valueOf(conjuntos.getConjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
//                            null,
//                            edtManutencao.getText().toString(),
//                            edtManutencao.getText().toString()
//
//                    );
//
//                }else{
//                    callSubConjuntos = apiService.criarAtividadeAgendada(
//                            edtDate.getText().toString(),
//                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId(),
//                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
//                            idOrdem,
//                            null,
//                            String.valueOf(conjuntos.getConjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
//                            null,
//                            spnManutencao.getSelectedItem().toString(),
//                            null
//
//                    );
//                }
//
//
//            }else{
//                if (tipo == 2){
//                    callSubConjuntos = apiService.criarAtividadeRealizada(
//                            edtDate.getText().toString(),
//                            0,
//                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
//                            idOrdem,
//                            null,
//                            String.valueOf(conjuntos.getConjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
//                            null,
//                            horaInicio,
//                            horatermino,
//                            4,
//                            edtManutencao.getText().toString(),
//                            edtManutencao.getText().toString()
//
//                    );
//
//                }else{
//                    callSubConjuntos = apiService.criarAtividadeRealizada(
//                            edtDate.getText().toString(),
//                            manutencoes.getManutencao().get(spnManutencao.getSelectedItemPosition()).getId(),
//                            listaUsuarios.getAllUsers().get(spnResponsavel.getSelectedItemPosition()).getId(),
//                            idOrdem,
//                            null,
//                            String.valueOf(conjuntos.getConjuntos().get(spnOnde.getSelectedItemPosition()).getId()),
//                            null,
//                            horaInicio,
//                            horatermino,
//                            4,
//                            spnManutencao.getSelectedItem().toString(),
//                            null
//
//                    );
//                }
//
//            }
//
//
//        }


        callSubConjuntos.enqueue(new Callback<Atividade>() {

            @Override
            public void onResponse(Call<Atividade> call, retrofit2.Response<Atividade> response) {
                String statusCode = response.message();
                Log.d("Retrofit ", statusCode);

                if (response.message().equals("OK")) {
                    Toast.makeText(getApplicationContext(),"Atividade Inserida com Sucesso",Toast.LENGTH_SHORT).show();
                    carregaAtividadesOrdem();

                } else {

                    Toast.makeText(getApplicationContext(),"Atividade não Inserida, tente novamente :(",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Atividade> call, Throwable t) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                String result = data.getStringExtra("data");

                edtDate.setText(result);

                // Do something with the contact here (bigger example below)
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.inserir) {
            salvaAtividade();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
