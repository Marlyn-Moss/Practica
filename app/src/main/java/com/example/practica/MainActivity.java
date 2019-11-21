package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnJugar, btnPuntaje, btnRespuesta, btnDatos;

    int NumeroGuardado = 0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJugar = findViewById(R.id.btnJugar);
        btnPuntaje = findViewById(R.id.btnPuntaje);
        btnRespuesta = findViewById(R.id.btnRespuesta);
        btnDatos = findViewById(R.id.btnDatos);

        btnJugar.setOnClickListener(this);
        btnPuntaje.setOnClickListener(this);
        btnRespuesta.setOnClickListener(this);
        btnDatos.setOnClickListener(this);

        sharedPreferences = this.getSharedPreferences("ArchivoPuntaje", this.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnJugar){
            startActivity(new Intent(MainActivity.this, Jugar.class));
        }
        else if (v.getId() == R.id.btnPuntaje){
            startActivity(new Intent(this, Puntaje.class));


        }
        else if (v.getId() == R.id.btnRespuesta){
            // Extraer dato
            NumeroGuardado = sharedPreferences.getInt("NumAdivinar",0);
            if (NumeroGuardado != 0){
                Toast.makeText(this,"La respuesta es: " + NumeroGuardado, Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Primero inicie el juego", Toast.LENGTH_LONG).show();
            }

        }
        else if (v.getId() == R.id.btnDatos){
            startActivity(new Intent(this, Datos.class));
        }
    }

}
