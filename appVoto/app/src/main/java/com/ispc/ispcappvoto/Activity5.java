package com.ispc.ispcappvoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        //METODOS DEL FOOTER

        ImageView buttonActivity2 = findViewById(R.id.buttonActivity2);
        buttonActivity2.setOnClickListener(v -> {
            Intent intent = new Intent(Activity5.this, Activity2.class);
            startActivity(intent);
        });

        ImageView buttonActivity3 = findViewById(R.id.buttonActivity3);
        buttonActivity3.setOnClickListener(v -> {
            Intent intent = new Intent(Activity5.this, Activity3.class);
            startActivity(intent);
        });

        ImageView buttonActivity5 = findViewById(R.id.buttonActivity5);
        buttonActivity5.setOnClickListener(v -> {
            Intent intent = new Intent(Activity5.this, Activity5.class);
            startActivity(intent);
        });
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
}