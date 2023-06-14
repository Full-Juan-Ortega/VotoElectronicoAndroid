package com.ispc.ispcappvoto;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ispc.ispcappvoto.controllers.VotacionController;
import com.ispc.ispcappvoto.models.Votacion;


public class activity6 extends AppCompatActivity {

    private EditText etEditarNombre, etEditarDescripcion, etEditarValoracion;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Votacion votacion;//La votacion que vamos a estar editando
    private VotacionController votacionController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);


        //METODOS DEL FOOTER

        ImageView buttonActivity2 = findViewById(R.id.buttonActivity2);
        buttonActivity2.setOnClickListener(v -> {
            Intent intent = new Intent(activity6.this, Activity2.class);
            startActivity(intent);
        });

        ImageView buttonActivity3 = findViewById(R.id.buttonActivity3);
        buttonActivity3.setOnClickListener(v -> {
            Intent intent = new Intent(activity6.this, Activity3.class);
            startActivity(intent);
        });

        ImageView buttonActivity5 = findViewById(R.id.buttonActivity5);
        buttonActivity5.setOnClickListener(v -> {
            Intent intent = new Intent(activity6.this, Activity5.class);
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
 // Posible error llave arriba
        // Recuperar datos que enviaron 
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        // Instanciar el controlador de las mascotas
        votacionController = new VotacionController(activity6.this);

        // Rearmar la votacion
        // Nota: igualmente solamente podríamos mandar el id y recuperar la votacion de la BD
        long idVotacion = extras.getLong("idVotacion");
        String nombreVotacion = extras.getString("nombreVotacion");
        String descripcionVotacion = extras.getString("descripcionVotacion");
        int valoracionVotacion = extras.getInt("valoracionVotacion");
        votacion = new Votacion(nombreVotacion, descripcionVotacion, valoracionVotacion, idVotacion);


        // Ahora declaramos las vistas
        etEditarValoracion = findViewById(R.id.input_valotacion);
        etEditarNombre = findViewById(R.id.input_titulo);
        etEditarDescripcion = findViewById(R.id.input_descripcion);
        //btnCancelarEdicion = findViewById(R.id.btnCancelarEdicionVotacion);
        btnGuardarCambios = findViewById(R.id.buttonCrear);


        // Rellenar los EditText con los datos de la votacion
        etEditarValoracion.setText(String.valueOf(votacion.getValoracion()));
        etEditarNombre.setText(votacion.getNombre());
        etEditarDescripcion.setText(votacion.getDescripcion());

        // Listener del click del botón para salir, simplemente cierra la actividad
       // btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   finish();
            //}
        //});

        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                etEditarNombre.setError(null);
                etEditarDescripcion.setError(null);
                etEditarValoracion.setError(null);
                // Crear la votacion con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoNombre = etEditarNombre.getText().toString();
                String nuevaDescripcion = etEditarDescripcion.getText().toString();
                String posibleNuevaValoracion = etEditarValoracion.getText().toString();
                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError("Escribe el nombre de la votación");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (nuevaDescripcion.isEmpty()) {
                    etEditarNombre.setError("Escribe la nueva descripción");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (posibleNuevaValoracion.isEmpty()) {
                    etEditarValoracion.setError("Escribe la valoración");
                    etEditarValoracion.requestFocus();
                    return;
                }
                // Si no es entero, igualmente marcar error
                int nuevaValoracion;
                try {
                    nuevaValoracion = Integer.parseInt(posibleNuevaValoracion);
                } catch (NumberFormatException e) {
                    etEditarValoracion.setError("Escribe un número");
                    etEditarValoracion.requestFocus();
                    return;
                }
                // Si llegamos hasta aquí es porque los datos ya están validados
                Votacion votaciónConNuevosCambios = new Votacion(nuevoNombre, nuevaDescripcion, nuevaValoracion, votacion.getId());
                int filasModificadas = votacionController.guardarCambios(votaciónConNuevosCambios);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(activity6.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });

    }

    public void showAlertDialog(View view) {
        Log.i(TAG, "showAlertDialog: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿ Editar votacion ?")
                .setMessage("Confirme si quiere editar la votacion.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acción a realizar cuando se hace clic en el botón Aceptar

                        // FLOR ACA AGREGAR EL INSERT A LA BD
                        Intent intent = new Intent(activity6.this, Activity5.class);
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

