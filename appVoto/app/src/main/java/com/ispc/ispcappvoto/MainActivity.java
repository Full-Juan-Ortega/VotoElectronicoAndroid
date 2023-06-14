package com.ispc.ispcappvoto;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//credencial ya registrada
//carlos@gmail.com
//carlos123-4
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public  void botonClick(View view){
        auth0Service.loginWithBrowser(this);
    }

//    public void botonClick(View view){
//            Log.i(TAG, "showAlertDialog: ");
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Marcos tragasable")
//                    .setMessage("Con la cierva lucas")
//                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Acción a realizar cuando se hace clic en el botón Aceptar
//                        }
//                    })
//                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Acción a realizar cuando se hace clic en el botón Cancelar
//                            dialog.dismiss();
//                        }
//                    });
//
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//    }

}