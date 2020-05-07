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

import androidx.annotation.NonNull;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.activities.AceitaSolicitacoesAcitivity;
import com.example.maptechnology.manutencaoapp.models.Atividades;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CustomAdapterAceitaAtividades extends ArrayAdapter<IdOrdem> implements View.OnClickListener {

    private ArrayList<IdOrdem> dataSet;
    Context mContext;
    Gson gson;
    Retrofit retrofit;
    RetrofitClass apiService;
    int status = 0;
    ViewHolder viewHolder;
    AceitaSolicitacoesAcitivity activity;

    private static class ViewHolder {
        TextView txtDescricao;
        TextView txtFalha;
        TextView txtDataCriacao;
        TextView txtResponsavel;
        ImageView imgAceito;
        ImageView imgStop;
    }

    public CustomAdapterAceitaAtividades(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public CustomAdapterAceitaAtividades(ArrayList<IdOrdem> data, Context context, Gson gson, RetrofitClass apiService, Retrofit retrofit, AceitaSolicitacoesAcitivity activity) {
        super(context, R.layout.row_list_aceite_ordens, data);
        this.dataSet = data;
        this.mContext=context;
        this.gson = gson;
        this.retrofit = retrofit;
        this.apiService = apiService;
        this.activity = activity;



    }

    public IdOrdem getCell(int position){

        return getItem(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final IdOrdem dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            status = dataModel.getStatus();
            viewHolder = new CustomAdapterAceitaAtividades.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list_aceite_ordens, parent, false);
            viewHolder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
            viewHolder.txtFalha = (TextView) convertView.findViewById(R.id.txtFalha);
            viewHolder.txtDataCriacao = (TextView) convertView.findViewById(R.id.txtDataCriacao);
            viewHolder.txtResponsavel = (TextView) convertView.findViewById(R.id.txtResponsavel);
            viewHolder.imgAceito = (ImageView) convertView.findViewById(R.id.imgStatus);
            viewHolder.imgStop = (ImageView) convertView.findViewById(R.id.imgStop);


            viewHolder.imgAceito.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    AlteraStatus(dataModel);

                }
            });

            viewHolder.imgStop.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    DeleteSolitacao(dataModel);
                }
            });


            result=convertView;

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (CustomAdapterAceitaAtividades.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtDescricao.setText("Descrição: "+dataModel.getDescricao());
        viewHolder.txtFalha.setText("Falha: " + dataModel.getFalha());
        viewHolder.txtDataCriacao.setText("Criado em: " + dataModel.getDataCriacao());
        viewHolder.txtResponsavel.setText("Responsavel: " + String.valueOf(dataModel.getResponsavelCriacao().getUsername()));


        // Return the completed view to render on screen
        return convertView;

    }




    @Override
    public void onClick(View v) {

    }

    public void AlteraStatus(IdOrdem ordem){

        Call<Void> call2 = apiService.alteraOrdemStatus(ordem.getId(),2);

        call2.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                int statusCode = response.code();
                Log.d("Retrofit Code: ", String.valueOf(statusCode));

                Log.d("Retrofit Message: ", response.message());
                if (response.message().equals("OK")) {

                    Toast.makeText(mContext, "Ordem Aceita", Toast.LENGTH_SHORT).show();
                    activity.AceitaSolicitacaoDeOrdem();

                } else {

                    Toast.makeText(mContext, "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log error here since request failed
                Log.d("error", t.toString());
            }
        });
    }


    public void DeleteSolitacao(IdOrdem ordem){

        Call<Void> call2 = apiService.removeOrdem(ordem.getId());

        call2.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                int statusCode = response.code();
                Log.d("Retrofit Code: ", String.valueOf(statusCode));

                Log.d("Retrofit Message: ", response.message());

                if (response.message().equals("OK")) {

                    Toast.makeText(mContext, "Solicitação removida", Toast.LENGTH_SHORT).show();
                    activity.AceitaSolicitacaoDeOrdem();

                } else {

                    Toast.makeText(mContext, "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
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
