package com.example.maptechnology.manutencaoapp.activities;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.example.maptechnology.manutencaoapp.R;
import com.google.android.gms.common.api.CommonStatusCodes;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("dia e mes", String.valueOf(dayOfMonth) + " " + String.valueOf(month));

                String dia= String.valueOf(dayOfMonth);
                String mes = "";

                Intent intent=new Intent();
                if ((month+1) < 10) {
                    mes = "0" + String.valueOf(month + 1);
                } else {
                    mes = String.valueOf(month + 1);
                }
                intent.putExtra("data",dia+ "/"+mes+"/"+year);
                setResult(Activity.RESULT_OK,intent);
                //encerra activity
                finish();
            }
        });



    }
}
