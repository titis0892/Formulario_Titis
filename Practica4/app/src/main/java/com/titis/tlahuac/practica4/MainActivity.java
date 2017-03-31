package com.titis.tlahuac.practica4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button fecha,enviar;
    EditText lineaone,Date,lineatwo,lineatree;
    private int dia,mes,año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineaone = (EditText) findViewById(R.id.lineaone);
        lineatwo = (EditText) findViewById(R.id.lineatwo);
        lineatree = (EditText) findViewById(R.id.lineatree);

        fecha = (Button) findViewById(R.id.fecha);
        Date = (EditText) findViewById(R.id.Date);
        fecha.setOnClickListener(this);

        enviar = (Button) findViewById(R.id.aceptar);
        enviar.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {//ver si contiene datos
            String datoNombre = (String) extras.get("nombre");
            String datoTelefono = (String) extras.get("telefono");
            String datoDireccion = (String) extras.get("direccion");
            String datoFecha = (String) extras.get("fecha");

            lineatree.setText(datoNombre);
            lineatwo.setText(datoTelefono);
            lineatree.setText(datoDireccion);
            fecha.setText(datoFecha);


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if(view==enviar){
            final Calendar c = Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            año=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
                    fecha.setText(dayOfMonth+"/"+(monthOfyear+1)+"/"+year);
                }
            }
                    ,dia,mes,año);
            datePickerDialog.show();
        }else if(view == fecha){
            Intent explicit_intent;

            explicit_intent = new Intent(this, registro_datos.class);
            String auxEdiNombre = lineaone.getText().toString();
            String auxEdiTelefono = lineatwo.getText().toString();
            String auxEdiDireccion = lineatree.getText().toString();
            String auxEdiFecha = fecha.getText().toString();

            explicit_intent.putExtra("nombre", auxEdiNombre);
            explicit_intent.putExtra("telefono", auxEdiTelefono);
            explicit_intent.putExtra("direccion", auxEdiDireccion);
            explicit_intent.putExtra("fecha",auxEdiFecha);
            startActivity(explicit_intent);


        }
    }

    }
