package com.example.maptechnology.manutencaoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;
import com.example.maptechnology.manutencaoapp.rest.RetrofitClass;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class CustomAdapterAceitaAtividades extends ArrayAdapter<IdOrdem> implements View.OnClickListener {

    private ArrayList<IdOrdem> dataSet;
    Context mContext;
    Gson gson;
    Retrofit retrofit;
    RetrofitClass apiService;
    int status = 0;
    ViewHolder viewHolder;

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

    public CustomAdapterAceitaAtividades(ArrayList<IdOrdem> data, Context context, Gson gson, RetrofitClass apiService, Retrofit retrofit) {
        super(context, R.layout.row_list_aceite_ordens, data);
        this.dataSet = data;
        this.mContext=context;
        this.gson = gson;
        this.retrofit = retrofit;
        this.apiService = apiService;



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

                    Toast.makeText(mContext,"Teste "+ String.valueOf(position),Toast.LENGTH_SHORT).show();

//                    alteraStatus(dataModel,1,position);

                }
            });

            viewHolder.imgStop.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,"Teste " +String.valueOf(position),Toast.LENGTH_SHORT).show();
//                    alteraStatus(dataModel,2,position);



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
        viewHolder.txtFalha.setText("Falha: " + dataModel.getFalha().getDescricao());
        viewHolder.txtDataCriacao.setText("Criado em: " + dataModel.getDataCriacao());
        viewHolder.txtResponsavel.setText("Responsavel: " + String.valueOf(dataModel.getResponsavelCriacao().getUsername()));


        // Return the completed view to render on screen
        return convertView;

    }




    @Override
    public void onClick(View v) {

    }
}
