package com.ispc.ispcappvoto;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ispc.ispcappvoto.controllers.VotacionController;
import com.ispc.ispcappvoto.models.Votacion;

import java.util.ArrayList;
import java.util.List;

public class Activity5 extends AppCompatActivity {

    private List<Votacion> listaDeVotaciones;
    private RecyclerView recyclerView;
    private VotacionAdapter adaptadorVotaciones;
    private VotacionController votacionesController;

    public void showAlertDialog(View view) {
        Log.i(TAG, "showAlertDialog: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Eliminar?")
                .setMessage("Confirme si quiere eliminar.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
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

        // Método para manejar el evento de clic en el botón "Volver"
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(v ->{
            Intent intent = new Intent(Activity5.this, Activity2.class);
            startActivity(intent);
        });

        votacionesController = new VotacionController(Activity5.this);

        // Instanciar vistas
        recyclerView = findViewById(R.id.recyclerViewVotaciones);


        // Por defecto es una lista vacía,
        // se la ponemos al adaptador y configuramos el recyclerView
        listaDeVotaciones = new ArrayList<>();
        adaptadorVotaciones = new VotacionAdapter(listaDeVotaciones);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorVotaciones);

        // Una vez que ya configuramos el RecyclerView le ponemos los datos de la BD
        refrescarListaDeVotaciones();

        // Listener de los clicks en la lista, o sea el RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad de edicion
                Votacion votacionSeleccionada = listaDeVotaciones.get(position);
                Intent intent = new Intent(Activity5.this, activity6.class);
                intent.putExtra("idVotacion", votacionSeleccionada.getId());
                intent.putExtra("nombreVotacion", votacionSeleccionada.getNombre());
                intent.putExtra("descripcionVotacion", votacionSeleccionada.getDescripcion());
                intent.putExtra("valoracionVotacion", votacionSeleccionada.getValoracion());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Votacion votacionParaEliminar = listaDeVotaciones.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(Activity5.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                votacionesController.eliminarVotacion(votacionParaEliminar);
                                refrescarListaDeVotaciones();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la votación " + votacionParaEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));



    }

    //METODOS DEL HEADER(TOOLBAR)

    // Método para manejar el evento de clic en el botón "Cerrar sesión"
    public void logout(View view){
        auth0Service.logout(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeVotaciones();
    }

    public void refrescarListaDeVotaciones() {
        /*
         * ==========
         * Justo aquí obtenemos la lista de la BD
         * y se la ponemos al RecyclerView
         * ============
         *
         * */
        if (adaptadorVotaciones == null) return;
        listaDeVotaciones = votacionesController.obtenerVotaciones();
        adaptadorVotaciones.setListaDeVotaciones(listaDeVotaciones);
        adaptadorVotaciones.notifyDataSetChanged();

}}

