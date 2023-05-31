package com.ispc.ispcappvoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //funcion para desloguear
        // auth0Service.logout(this);
        // funcion para loguear
       //auth0Service.loginWithBrowser(this);


    }
}