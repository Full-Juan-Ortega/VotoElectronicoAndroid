package com.ispc.ispcappvoto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
}