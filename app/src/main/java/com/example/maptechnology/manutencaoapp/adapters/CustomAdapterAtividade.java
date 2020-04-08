package com.example.maptechnology.manutencaoapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.activities.AtividadesPorOrdemActivity;
import com.example.maptechnology.manutencaoapp.models.Atividade;
import com.example.maptechnology.manutencaoapp.models.Atividades;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by MAPTECHNOLOGY on 13/03/2019.
 */

public class CustomAdapterAtividade extends ArrayAdapter<Atividade> implements View.OnClickListener{

    private ArrayList<Atividade> dataSet;
    Context mContext;
    Gson gson;
    Retrofit retrofit;
    RetrofitClass apiService;
    AtividadesPorOrdemActivity activity;
    ViewHolder viewHolder;
    int status = 0;
    int atividadesAbertas = 0;

    // View lookup cache
        private static class ViewHolder {
            TextView txtDescricao;
            TextView txtComp;
            TextView txtDataManutencao;
            TextView txtManutencao;
            ImageView txtStatus;
            ImageView imgStop;
        }

    public CustomAdapterAtividade(ArrayList<Atividade> data, Context context,Gson gson,RetrofitClass apiService,Retrofit retrofit,AtividadesPorOrdemActivity activity) {
        super(context, R.layout.row_list, data);
        this.dataSet = data;
        this.mContext=context;
        this.gson = gson;
        this.retrofit = retrofit;
        this.apiService = apiService;
        this.activity = activity;


    }

    public Atividade getCell(int position){

        return getItem(position);
    }


    @Override
    public void onClick(View view) {

        int position=(Integer) view.getTag();
        Object object= getItem(position);
        Atividade dataModel=(Atividade)object;
    }

    public int getAtividadesAbertas(){

            return atividadesAbertas;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Atividade dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            status = dataModel.getStatus();
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list, parent, false);
            viewHolder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
            viewHolder.txtComp = (TextView) convertView.findViewById(R.id.txtDataCriacao);
            viewHolder.txtDataManutencao = (TextView) convertView.findViewById(R.id.txtFalha);
            viewHolder.txtManutencao = (TextView) convertView.findViewById(R.id.txtResponsavel);
            viewHolder.txtStatus = (ImageView) convertView.findViewById(R.id.imgStatus);
            viewHolder.imgStop = (ImageView) convertView.findViewById(R.id.imgStop);


            viewHolder.txtStatus.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,"Teste "+ String.valueOf(position),Toast.LENGTH_SHORT).show();

                    alteraStatus(dataModel,1,position);

                }
            });

            viewHolder.imgStop.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,"Teste " +String.valueOf(position),Toast.LENGTH_SHORT).show();
                    alteraStatus(dataModel,2,position);



                }
            });


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtDescricao.setText("Manutenção: "+dataModel.getManutencao().getDescricao());
        if(dataModel.getIdConjunto() != null) {
            viewHolder.txtComp.setText("Onde: " + dataModel.getIdConjunto().getNome());
        }else if (dataModel.getIdSubConjunto() != null){
            viewHolder.txtComp.setText("Onde: " + dataModel.getIdSubConjunto().getNome());
        }else{
            viewHolder.txtComp.setText("Onde: " + dataModel.getIdPeca().getNome());
        }

        viewHolder.txtDataManutencao.setText("Quando: " + dataModel.getDataManutencao());
        viewHolder.txtManutencao.setText("Responsavel: " + String.valueOf(dataModel.getResponsavel().getEmail()));

        switch (dataModel.getStatus()){

            case 1:
                atividadesAbertas =+ 1;
                viewHolder.txtStatus.setImageResource(R.mipmap.play);
                viewHolder.imgStop.setImageResource(R.mipmap.stopdesable);
                break;
            case 2:
                atividadesAbertas =+ 1;
                viewHolder.txtStatus.setImageResource(R.mipmap.pause);
                viewHolder.imgStop.setImageResource(R.mipmap.stop);
                break;
            case 3:
                atividadesAbertas =+ 1;
                viewHolder.txtStatus.setImageResource(R.mipmap.play);
                viewHolder.imgStop.setImageResource(R.mipmap.stop);
                break;
            case 4:
                atividadesAbertas =- 1;
                viewHolder.txtStatus.setImageResource(R.mipmap.playdisable);
                viewHolder.imgStop.setImageResource(R.mipmap.stopdesable);
                break;
        }
        // Return the completed view to render on screen
        return convertView;

    }


    public void alteraStatus(Atividade atividade, int botao, int position){
        // botao = 1 = play
        // botao = 2 = stop

        Date currentTime = Calendar.getInstance().getTime();

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);



        Call<Atividades> call2 = null;
        if(atividade.getStatus() == 1 && botao == 1) {
            atividade.setStatus(2);
            call2 = apiService.alteraStatusAtividade(atividade.getId(), 2, simpleDateFormat.format(currentTime), null,null,null);

        }else if(atividade.getStatus() == 2 && botao == 1){
            atividade.setStatus(3);
            call2 = apiService.alteraStatusAtividade(atividade.getId(), 3, null, simpleDateFormat.format(currentTime),null,null);

        }else if(atividade.getStatus() == 3 && botao == 1){
            atividade.setStatus(2);
             call2 = apiService.alteraStatusAtividade(atividade.getId(), 2,     null, null, simpleDateFormat.format(currentTime),null);

        }else if(botao == 2){
            atividade.setStatus(4);
             call2 = apiService.alteraStatusAtividade(atividade.getId(), 4, null, null, null,simpleDateFormat.format(currentTime));

        }

            call2.enqueue(new Callback<Atividades>() {

                @Override
                public void onResponse(Call<Atividades> call, retrofit2.Response<Atividades> response) {
                    int statusCode = response.code();
                    Log.d("Retrofit Code: ", String.valueOf(statusCode));

                    Log.d("Retrofit Message: ", response.message());
                    if (response.message().equals("200")) {

                        Toast.makeText(mContext, "Atividade Atualizada", Toast.LENGTH_SHORT).show();
                        activity.chamadaAtividadesPorordem();




                    } else {

                        Toast.makeText(mContext, "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Atividades> call, Throwable t) {
                    // Log error here since request failed
                    Log.d("error", t.toString());
                }
            });


    }


}
