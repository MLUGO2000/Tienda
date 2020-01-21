package com.lugo.manueln.tienda.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.categoriaSeleccionada;

import java.net.ConnectException;
import java.util.ArrayList;

public class adapterCategorias extends RecyclerView.Adapter<adapterCategorias.MyAdapterCat> {

    ArrayList<String>listaCategoria;
    Context miContexto;
    FragmentActivity miActividad;
    public adapterCategorias(Context context, ArrayList<String> miLista, FragmentActivity miActivity){
        miContexto=context;
        listaCategoria=miLista;
        miActividad=miActivity;

    }
    @NonNull
    @Override
    public adapterCategorias.MyAdapterCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria,parent,false);


        return new adapterCategorias.MyAdapterCat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterCategorias.MyAdapterCat holder, final int position) {

        holder.nombreCategoria.setText(listaCategoria.get(position));

        holder.cardCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoriaSeleccionada miFragmentSelec;

                Bundle miBundle=new Bundle();

                miBundle.putString("categoria",listaCategoria.get(position));

                miFragmentSelec=new categoriaSeleccionada();

                miFragmentSelec.setArguments(miBundle);

                miActividad.getSupportFragmentManager().beginTransaction().replace(R.id.framePrincipal,miFragmentSelec).addToBackStack(null).commit();



            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    public class MyAdapterCat extends RecyclerView.ViewHolder {
        TextView nombreCategoria;
        CardView cardCategoria;
        public MyAdapterCat(View itemView) {
            super(itemView);

            cardCategoria=itemView.findViewById(R.id.cardCategoria);
            nombreCategoria=itemView.findViewById(R.id.nombreCat);

        }
    }
}
