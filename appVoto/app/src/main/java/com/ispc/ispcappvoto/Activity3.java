package com.ispc.ispcappvoto;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
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


