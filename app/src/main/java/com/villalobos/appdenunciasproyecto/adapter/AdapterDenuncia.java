package com.villalobos.appdenunciasproyecto.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.villalobos.appdenunciasproyecto.R;
import com.villalobos.appdenunciasproyecto.model.Denuncias;

import java.util.List;

public class AdapterDenuncia extends RecyclerView.Adapter<AdapterDenuncia.DenunciaHolder> {
    private Activity activity;
    private int layout;
    private List<Denuncias> list;

    public AdapterDenuncia(Activity activity, int layout, List<Denuncias> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public DenunciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new DenunciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciaHolder holder, int position) {
        Denuncias denuncias = list.get(position);
        holder.titulo.setText(denuncias.getTitulo());
        holder.direccion.setText(denuncias.getDireccion());
        holder.id = denuncias.getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DenunciaHolder extends RecyclerView.ViewHolder{

        TextView titulo, direccion;
        String id;

        public DenunciaHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_denuncia_titulo);
            direccion = itemView.findViewById(R.id.item_denuncia_direccion);
        }
    }

}
