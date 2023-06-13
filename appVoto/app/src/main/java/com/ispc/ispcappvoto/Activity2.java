package com.ispc.ispcappvoto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //METODOS DEL FOOTER

//        Revisar esto
        @SuppressLint("WrongViewCast") Button buttonActivity2 = findViewById(R.id.buttonActivity2);
//        Button buttonActivity3 = findViewById(R.id.buttonActivity3);
//        Button buttonActivity5 = findViewById(R.id.buttonActivity5);

        buttonActivity2.setOnClickListener(v -> {
            //Dentro del Intent modificar su primer parametro dependiendo donde este situada la activity y el segundo queda como esta
            Intent intent = new Intent(Activity2.this, Activity2.class);
            startActivity(intent);
        });

        /*buttonActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Dentro del Intent modificar su primer parametro dependiendo donde este situada la activity y el segundo queda como esta
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
            }
        });

        buttonActivity5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Dentro del Intent modificar su primer parametro dependiendo donde este situada la activity y el segundo queda como esta
                Intent intent = new Intent(Activity2.this, Activity5.class);
                startActivity(intent);
            }
        });*/

    }

    //METODOS DEL HEADER(TOOLBAR)

    // Método para manejar el evento de clic en el botón "Volver"
    public void onBackPressed(View view) {
        // Volver a la actividad anterior
        onBackPressed();
    }

    // Método para manejar el evento de clic en el botón "Cerrar sesión"
    public void logout(View view){
        auth0Service.logout(this);
    }

    //METODOS DEL BODY

}