package com.intercambiophoto.aplicacionandroi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescripcion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.txtPhotocards);
            this.textViewDescripcion = itemView.findViewById(R.id.txtventaPH);
            this.imageViewIcon = itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_elemental, parent, false);
        view.setOnClickListener(MainActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;
        TextView textViewDescripcion = holder.textViewDescripcion; // Corregido aquí
        ImageView imageView = holder.imageViewIcon;
        textViewName.setText(dataSet.get(listPosition).getName());
        textViewDescripcion.setText(dataSet.get(listPosition).getDescripcion());
        imageView.setImageResource(dataSet.get(listPosition).getImage());

        final int finalistPosition = listPosition;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalistPosition >= 0 && finalistPosition < MyData.cardviewsArray.length){
                    Class<?> targetActivity = MyData.cardviewsArray[finalistPosition];

                    Intent i = new Intent(view.getContext(),targetActivity);
                    view.getContext().startActivity(i);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
