package com.example.maptechnology.manutencaoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maptechnology.manutencaoapp.R;
import com.example.maptechnology.manutencaoapp.activities.DetalheCalendarioActivity;
import com.example.maptechnology.manutencaoapp.models.Calendario;

import java.util.ArrayList;

/**
 * Created by MAPTECHNOLOGY on 13/03/2019.
 */

public class CustomAdapter extends ArrayAdapter<Calendario> implements View.OnClickListener{

    private ArrayList<Calendario> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtDescricao;
        TextView txtComp;
        TextView txtDataManutencao;
        TextView txtManutencao;

    }

    public CustomAdapter(ArrayList<Calendario> data, Context context) {
        super(context, R.layout.row_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    public Calendario getCell(int position){

        return getItem(position);
    }


    @Override
    public void onClick(View view) {

        int position=(Integer) view.getTag();
        Object object= getItem(position);
        Calendario dataModel=(Calendario)object;



    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Calendario dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list, parent, false);
            viewHolder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
            viewHolder.txtComp = (TextView) convertView.findViewById(R.id.txtComp);
            viewHolder.txtDataManutencao = (TextView) convertView.findViewById(R.id.txtDataManutencao);
            viewHolder.txtManutencao = (TextView) convertView.findViewById(R.id.txtManutencao);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtDescricao.setText("Manutenção: "+dataModel.getManutencao());
        if(dataModel.getIdConjunto() != null) {
            viewHolder.txtComp.setText("Onde: " + dataModel.getIdConjunto());
        }else if (dataModel.getIdSubConjunto() != null){
            viewHolder.txtComp.setText("Onde: " + dataModel.getIdSubConjunto());
        }else{
            viewHolder.txtComp.setText("Onde: " + dataModel.getIdPeca());
        }
        viewHolder.txtDataManutencao.setText("Quando: " + dataModel.getDataManutencao());
        viewHolder.txtManutencao.setText("Responsavel: " + String.valueOf(dataModel.getResponsavel()));

        // Return the completed view to render on screen
        return convertView;

    }
}
