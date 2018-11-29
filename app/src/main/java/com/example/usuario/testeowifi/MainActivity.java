package com.example.usuario.testeowifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button start, stop, canciónON, canciónOFF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start= findViewById(R.id.buttonArrancar);
        stop = findViewById(R.id.buttonParar);
        canciónOFF = findViewById(R.id.buttonStop);
        canciónON = findViewById(R.id.buttonPlay);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), TesteoWifi.class));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), TesteoWifi.class));
            }
        });

        canciónON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), ServicioMusica.class));
            }
        });

        canciónOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), ServicioMusica.class));
            }
        });



    }
}
