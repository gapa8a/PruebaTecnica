package com.example.pruebatecnica;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.pruebatecnica.includes.toolbar;

import java.util.Calendar;

public class ejercicio1 extends AppCompatActivity {
    private Button btn1;
    private EditText edtPartida, edtRegreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);
        toolbar.show(this,"Calculadora de Tiquetes de avión",true);
        btn1= findViewById(R.id.btn1);
        edtPartida=findViewById(R.id.edtPartida);
        edtRegreso=findViewById(R.id.edtRegreso);
        edtPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(1,"Fecha de Partida");

            }
        });
        edtRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(2,"Fecha de Regreso");

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePickerDialog(final int campoFecha, final String msg) {
        final Calendar c = Calendar.getInstance();

        final int dia, mes, anio;
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);


        DatePickerDialog newDialog = new DatePickerDialog(ejercicio1.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.DAY_OF_MONTH,dayOfMonth);


                switch (campoFecha){
                    case 1:edtPartida.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case 2:edtRegreso.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                }

            }
        }, anio, mes, dia);
        newDialog.setMessage(msg);
        newDialog.show();
    }
}
