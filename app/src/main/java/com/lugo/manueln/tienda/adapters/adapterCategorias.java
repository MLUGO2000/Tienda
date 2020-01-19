package com.lugo.manueln.tienda.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lugo.manueln.tienda.R;

import java.net.ConnectException;
import java.util.ArrayList;

public class adapterCategorias extends RecyclerView.Adapter<adapterCategorias.MyAdapterCat> {

    ArrayList<String>listaCategoria;
    Context miContexto;
    public adapterCategorias(Context context, ArrayList<String> miLista){
        miContexto=context;
        listaCategoria=miLista;

    }
    @NonNull
    @Override
    public adapterCategorias.MyAdapterCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria,parent,false);


        return new adapterCategorias.MyAdapterCat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterCategorias.MyAdapterCat holder, int position) {

        holder.nombreCategoria.setText(listaCategoria.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    public class MyAdapterCat extends RecyclerView.ViewHolder {
        TextView nombreCategoria;
        public MyAdapterCat(View itemView) {
            super(itemView);

            nombreCategoria=itemView.findViewById(R.id.nombreCat);

        }
    }
}
