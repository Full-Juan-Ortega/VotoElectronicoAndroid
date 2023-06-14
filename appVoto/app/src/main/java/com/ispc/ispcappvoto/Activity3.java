package com.ispc.ispcappvoto;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ispc.ispcappvoto.controllers.VotacionController;
import com.ispc.ispcappvoto.models.Votacion;
import android.util.Log;


public class Activity3 extends AppCompatActivity {

    private Button btnAgregarVotacion;
    private EditText input_titulo, etDescripcion, input_valoracion;
    private VotacionController votacionesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        //METODOS DEL FOOTER

        ImageView buttonActivity2 = findViewById(R.id.buttonActivity2);
        buttonActivity2.setOnClickListener(v -> {
            Intent intent = new Intent(Activity3.this, Activity2.class);
            startActivity(intent);
        });

        ImageView buttonActivity3 = findViewById(R.id.buttonActivity3);
        buttonActivity3.setOnClickListener(v -> {
            Intent intent = new Intent(Activity3.this, Activity3.class);
            startActivity(intent);
        });

        ImageView buttonActivity5 = findViewById(R.id.buttonActivity5);
        buttonActivity5.setOnClickListener(v -> {
            Intent intent = new Intent(Activity3.this, Activity5.class);
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

        // Instanciar vistas
        input_titulo = findViewById(R.id.input_titulo);
        etDescripcion = findViewById(R.id.input_descripcion);
        input_valoracion= findViewById(R.id.input_valoracion);
        btnAgregarVotacion = findViewById(R.id.buttonCrear);

        // Crear el controlador
        votacionesController = new VotacionController(Activity3.this);

        // Agregar listener del botón de guardar
        btnAgregarVotacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                input_titulo.setError(null);
                etDescripcion.setError(null);
                input_valoracion.setError(null);
                String nombre = input_titulo.getText().toString();
                String descripcion=etDescripcion.getText().toString();
                String valoracion = input_valoracion.getText().toString();
                if ("".equals(nombre)) {
                    input_titulo.setError("Escribe el nombre de la votación");
                    input_titulo.requestFocus();
                    return;
                }
                if ("".equals(descripcion)) {
                    etDescripcion.setError("Escribe la descripcion");
                    etDescripcion.requestFocus();
                    return;
                }
                if ("".equals(valoracion)) {
                    input_valoracion.setError("Indica tu valoración");
                    input_valoracion.requestFocus();
                    return;
                }

                // Ver si es un entero
                int valoracion_dada;
                try {
                    valoracion_dada = Integer.parseInt(input_valoracion.getText().toString());
                } catch (NumberFormatException e) {
                    input_valoracion.setError("Escribe un número");
                    input_valoracion.requestFocus();
                    return;
                }
                // Ya pasó la validación
                Votacion nuevaVotacion = new Votacion(nombre, descripcion, valoracion_dada);
                long id = votacionesController.nuevaVotacion(nuevaVotacion);
                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(Activity3.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    finish();
                }
            }
        });


    }

    public void showAlertDialog(View view) {
    Log.i(TAG, "showAlertDialog: ");
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("¿Crear votacion?")
            .setMessage("Confirme si quiere crear una votacion.")
            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Acción a realizar cuando se hace clic en el botón Aceptar

                    // FLOR ACA AGREGAR EL INSERT A LA BD
                    Intent intent = new Intent(Activity3.this, Activity2.class);
                    startActivity(intent);
                }
            })
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Acción a realizar cuando se hace clic en el botón Cancelar
                    dialog.dismiss();
                }
            });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();

    }
}


