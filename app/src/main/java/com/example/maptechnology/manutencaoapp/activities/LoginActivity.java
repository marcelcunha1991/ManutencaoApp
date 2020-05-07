package com.example.maptechnology.manutencaoapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.application.AppApplication;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.example.maptechnology.manutencaoapp.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText matricula;
    EditText senha;
    EditText edtIp;
    Button entrar;
    Gson gson;
    String url = " ";
    RetrofitClass apiService;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_semp);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();


        matricula = (EditText) findViewById(R.id.editMatricula);
        senha = (EditText) findViewById(R.id.editSenha);
        edtIp = (EditText) findViewById(R.id.edtIp);
        edtIp.setText(sharedPreferences.getString("ip", ""));

        entrar = (Button) findViewById(R.id.btnEntrar);

        entrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == entrar){

            url = "http://" + edtIp.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            apiService = retrofit.create(RetrofitClass.class);

            Call<Usuario> call2 = apiService.logar(matricula.getText().toString(),senha.getText().toString());
            call2.enqueue(new Callback<Usuario>() {

                @Override
                public void onResponse(Call<Usuario> call, retrofit2.Response<Usuario> response) {
                    int statusCode = response.code();

                    Usuario usuario = response.body();

                    if (response.message().equals("OK")) {

                        ((AppApplication) getApplication()).setTipoUsuario(usuario.getUser().getHierarquia());

                        editor.putInt( getString(R.string.user_id), usuario.getUserData().getId());
                        editor.putString( getString(R.string.matricula), usuario.getUserData().getEmail());
                        editor.putString( getString(R.string.hierarquia), usuario.getUser().getHierarquia());
                        editor.putString( getString(R.string.horahomen), usuario.getUser().getHomemHora());
                        editor.putString(getString(R.string.ip),edtIp.getText().toString());
                        editor.putInt(getString(R.string.statusLogin),1 );
                        editor.commit();

                        Intent i = new Intent(getApplicationContext(), OrdensDoDiaActivity.class);
                        startActivity(i);

                        Toast.makeText(getApplicationContext(),"Seja Bem Vindo(a) " + usuario.getUser().getNome(),Toast.LENGTH_SHORT).show();

    

                    }else{

                        Toast.makeText(getApplicationContext(),"Erro de Login. Verifique Matrícula e Senha e tente outra vez.",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    // Log error here since request failed
                    Log.d("error", t.toString());
                }
            });


        }

    }
}
