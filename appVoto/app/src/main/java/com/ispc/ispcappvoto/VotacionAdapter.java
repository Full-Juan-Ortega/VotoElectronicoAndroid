package com.ispc.ispcappvoto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ispc.ispcappvoto.models.Votacion;

import java.util.List;

public class VotacionAdapter extends RecyclerView.Adapter<VotacionAdapter.MyViewHolder>{
    private List<Votacion> listaDeVotaciones;

    public void setListaDeVotaciones(List<Votacion> listaDeMascotas) {
        this.listaDeVotaciones = listaDeMascotas;
    }

    public VotacionAdapter(List<Votacion> votaciones) {
        this.listaDeVotaciones = votaciones;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaMascota = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_votacion, viewGroup, false);
        return new MyViewHolder(filaMascota);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Obtener la votacion
        // de nuestra lista gracias al índice i
        Votacion votacion = listaDeVotaciones.get(i);

        // Obtener los datos de la lista
        String nombreVotacion = votacion.getNombre();
        String descripcion = votacion.getDescripcion();
        int valoracion = votacion.getValoracion();
        // Y poner a los TextView los datos con setText
        myViewHolder.nombre.setText(nombreVotacion);
        myViewHolder.descripcion.setText(descripcion);
        myViewHolder.valoracion.setText(String.valueOf(valoracion));
    }

    @Override
    public int getItemCount() {
        return listaDeVotaciones.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, valoracion;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvNombre);
            this.descripcion = itemView.findViewById(R.id.tvDescripcion);
            this.valoracion = itemView.findViewById(R.id.tvValoracion);
        }
    }
}
