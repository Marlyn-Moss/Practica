package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Jugar extends AppCompatActivity {


    public static int NumAdivinar = 0;

    Button btnAdivinar;
    EditText txtNumUsuario;

    int PuntajeUsuario = 10;
    Random Num = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        final Context context = this;
        SharedPreferences sharedPreferences = getSharedPreferences("ArchivoPuntaje", context.MODE_PRIVATE);


        // Guardar dato
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        // Extraer dato
        int NumGuardado = sharedPreferences.getInt("NumAdivinar",0);

        if (NumGuardado == 0){
            NumAdivinar = Num.nextInt(10)+1;
            editor.putInt("NumAdivinar", NumAdivinar);
            editor.commit();
            Toast.makeText(Jugar.this,"No existe ningun valor guardado", Toast.LENGTH_SHORT).show();
        }else {
            NumAdivinar = NumGuardado;
        }

        // Extraer dato
        int PuntajeActual = sharedPreferences.getInt("PuntajeActual",0);

        if (PuntajeActual == 0){
            editor.putInt("PuntajeActual", PuntajeUsuario);
            editor.commit();
        }else {
            PuntajeUsuario = PuntajeActual;
        }

        btnAdivinar = findViewById(R.id.btnAdivinar);
        txtNumUsuario = findViewById(R.id.txtNumUsuario);

        btnAdivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNumUsuario.getText().length() != 0){
                    int NumUsuario = Integer.parseInt(txtNumUsuario.getText().toString());

                    if (NumAdivinar == NumUsuario){
                        Toast.makeText(Jugar.this,"Felicidades has adivinado", Toast.LENGTH_SHORT).show();
                        PuntajeUsuario = PuntajeUsuario + 10;
                        editor.putInt("PuntajeActual", PuntajeUsuario);
                        editor.commit();
                        NumAdivinar = Num.nextInt(10)+1;
                        editor.putInt("NumAdivinar", NumAdivinar);
                        editor.commit();
                    }
                    else
                    {
                        Toast.makeText(Jugar.this,"Intentalo de nuevo", Toast.LENGTH_SHORT).show();
                        PuntajeUsuario = PuntajeUsuario - 1;
                        editor.putInt("PuntajeActual", PuntajeUsuario);
                        editor.commit();
                    }
                    txtNumUsuario.setText("");
                }
            }
        });
    }
}
