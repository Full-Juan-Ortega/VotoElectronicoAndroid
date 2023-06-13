package com.ispc.ispcappvoto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //METODOS DEL FOOTER

        ImageView buttonActivity2 = findViewById(R.id.buttonActivity2);
        buttonActivity2.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, Activity2.class);
            startActivity(intent);
        });

//        ImageView buttonActivity3 = findViewById(R.id.buttonActivity3);
//        buttonActivity3.setOnClickListener(v -> {
//            Intent intent = new Intent(Activity2.this, Activity3.class);
//            startActivity(intent);
//        });
//
//        ImageView buttonActivity5 = findViewById(R.id.buttonActivity5);
//        buttonActivity5.setOnClickListener(v -> {
//            Intent intent = new Intent(Activity2.this, Activity5.class);
//            startActivity(intent);
//        });

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