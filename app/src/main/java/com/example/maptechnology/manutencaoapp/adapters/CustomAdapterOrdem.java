package com.example.maptechnology.manutencaoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;

import java.util.ArrayList;

/**
 * Created by MAPTECHNOLOGY on 13/03/2019.
 */

public class CustomAdapterOrdem extends ArrayAdapter<IdOrdem> implements View.OnClickListener{

    private ArrayList<IdOrdem> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtDescricao;
        TextView txtDataCriacao;
        TextView txtDataManutencao;
        TextView txtResponsavel;
        TextView txtStatus;

    }

    public CustomAdapterOrdem(ArrayList<IdOrdem> data, Context context) {
        super(context, R.layout.row_list_ordem, data);
        this.dataSet = data;
        this.mContext=context;

    }

    public IdOrdem getCell(int position){

        return getItem(position);
    }


    @Override
    public void onClick(View view) {

        int position=(Integer) view.getTag();
        Object object= getItem(position);
        IdOrdem dataModel=(IdOrdem)object;



    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        IdOrdem dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list_ordem, parent, false);
            viewHolder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
            viewHolder.txtDataCriacao = (TextView) convertView.findViewById(R.id.txtDataCriacao);
            viewHolder.txtDataManutencao = (TextView) convertView.findViewById(R.id.txtFalha);
            viewHolder.txtResponsavel = (TextView) convertView.findViewById(R.id.txtResponsavelCriacao);
            viewHolder.txtStatus = (TextView) convertView.findViewById(R.id.txtStatus);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtDescricao.setText("Descrição: "+dataModel.getDescricao());
        viewHolder.txtDataCriacao.setText("Data Criação:" + dataModel.getDataCriacao() );
        viewHolder.txtDataManutencao.setText("codigo: " + dataModel.getCodigo());
        viewHolder.txtResponsavel.setText("Responsavel: " + String.valueOf(dataModel.getResponsavelCriacao().getUsername()));
        viewHolder.txtStatus.setText("Status: " + returnStatusOrdem(dataModel.getStatus()));
        // Return the completed view to render on screen
        return convertView;

    }

    public String returnStatusOrdem(int i){

        if(i == 1){
           return "Para Aprovação";
       }else if(i == 2){
           return "Á iniciar";
       }else if(i == 3){
           return "Executando";
       }else{
           return "Finalizado";
       }


    }
}
