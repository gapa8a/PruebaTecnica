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
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pruebatecnica.includes.toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ejercicio1 extends AppCompatActivity {
    private Button btn1;
    private EditText edtPartida, edtRegreso,edtDistancia;
    private String distancia;
    private Date partida, regreso;
    private int dias,precio, descuento,precioF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);
        toolbar.show(this,"Calculadora de Tiquetes de avión",true);
        btn1= findViewById(R.id.btn1);
        edtPartida=findViewById(R.id.edtPartida);
        edtRegreso=findViewById(R.id.edtRegreso);
        edtDistancia=findViewById(R.id.edtDistancia);
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
                distancia=edtDistancia.getText().toString();
                try {
                    partida =deStringADate(edtPartida.getText().toString());
                    regreso = deStringADate(edtRegreso.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    dias = getDays(partida, regreso);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(!distancia.isEmpty() && !partida.toString().isEmpty() &&!regreso.toString().isEmpty()){
                    if(regreso.after(partida)) {
                        int distance = Integer.parseInt(distancia);
                        if( dias>7 && distance>1000){
                            precio=  (distance*35);
                            descuento = (int) (precio*0.3);
                            precioF= precio-descuento;
                            Toast.makeText(ejercicio1.this, "El precio con descuento es de $"+precioF, Toast.LENGTH_SHORT).show();
                        }else{
                            precio=  (distance*35);
                            Toast.makeText(ejercicio1.this, "El precio es $"+precio, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ejercicio1.this, "La fecha de regreso debe ser posterior a la de partida.", Toast.LENGTH_LONG).show();}
                }else{
                    Toast.makeText(ejercicio1.this, "Completa todos los campos.", Toast.LENGTH_SHORT).show();
                }


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
    public static Date deStringADate(String fechaString) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = formatoFecha.parse(fechaString);
        return fechaDate;

    }
    public int getDays(Date inicio, Date fin) throws ParseException {
        long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;


        long Inicio=inicio.getTime();
        long Fin =fin.getTime();
        long dife = (Fin - Inicio) / (MILLIS_PER_DAY);
        return (int) dife;
    }

}
