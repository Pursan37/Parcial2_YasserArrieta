package com.example.parcial_2_yasserarrieta;
import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,nombre,estrato,salario,nivel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.txtcedula);
            nombre= itemView.findViewById(R.id.txtnombre);
            estrato= itemView.findViewById(R.id.txtestrato);
            salario= itemView.findViewById(R.id.txtsalario);
            nivel= itemView.findViewById(R.id.txtnivel);
        }
    }
    public List<Persona> usuariosList;

    public Adapter(List<Persona> usuariosList){
        this.usuariosList = usuariosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(usuariosList.get(position).getCedula());
        holder.nombre.setText(usuariosList.get(position).getNombre());
        holder.estrato.setText(usuariosList.get(position).getEstrato());
        holder.salario.setText(usuariosList.get(position).getSalario());
        holder.nivel.setText(usuariosList.get(position).getNivel());

    }



    @Override
    public int getItemCount() {
        return usuariosList.size();
    }
}
