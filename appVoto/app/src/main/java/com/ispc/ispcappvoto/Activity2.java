package com.ispc.ispcappvoto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Inicializar el reproductor multimedia una vez que la superficie esté lista
                initializeMediaPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // No es necesario implementar este método
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // Liberar recursos del reproductor multimedia cuando la superficie sea destruida
                releaseMediaPlayer();
            }
        });

        //METODOS DEL FOOTER

        ImageView buttonActivity2 = findViewById(R.id.buttonActivity2);
        buttonActivity2.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, Activity2.class);
            startActivity(intent);
        });

        ImageView buttonActivity3 = findViewById(R.id.buttonActivity3);
        buttonActivity3.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, Activity3.class);
            startActivity(intent);
        });

        ImageView buttonActivity5 = findViewById(R.id.buttonActivity5);
        buttonActivity5.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, Activity5.class);
            startActivity(intent);
        });

        ImageView btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> auth0Service.logout(this));

    }

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.video);
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.setLooping(true); // Repetir el video continuamente
        mediaPlayer.start();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    //METODOS DEL BODY

}