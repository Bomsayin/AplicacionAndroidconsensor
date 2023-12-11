package com.intercambiophoto.aplicacionandroi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class inicioactivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    private static ArrayList<Integer> removedItems;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter); // Debes establecer el adaptador en el RecyclerView
    }
  /*  public void btnPhoto(View view) {
        Intent i = new Intent(this, Intercambios.class);
        startActivity(i);


    }
    public void Accesorios(View view) {
        Intent i = new Intent(this, Accesorios.class);
        startActivity(i);


    }
    public void compraventa(View view) {
        Intent i = new Intent(this, CompraVenta.class);
        startActivity(i);


    }
    public void regalos(View view) {
        Intent i = new Intent(this, Regalos.class);
        startActivity(i);


    }
    public void mapa(View view) {
        Intent i = new Intent(this, Mapa.class);
        startActivity(i);


    }
    public void sensor(View view) {
        Intent i = new Intent(this, Gyroscope.class);
        startActivity(i);


    }*/
  public void Agregarp(View view) {
      Intent i = new Intent(this, Agregar.class);
      startActivity(i);


  }
}

