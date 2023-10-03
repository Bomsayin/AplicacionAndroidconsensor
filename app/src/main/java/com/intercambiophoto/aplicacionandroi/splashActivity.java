package com.intercambiophoto.aplicacionandroi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(splashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);
        /*TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this, inicioactivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);

    }*/
    }
}