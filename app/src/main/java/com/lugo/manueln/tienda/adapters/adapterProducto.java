package com.lugo.manueln.tienda.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.infoProducto;
import com.lugo.manueln.tienda.producto;

import java.util.List;

public class adapterProducto extends RecyclerView.Adapter<adapterProducto.MyAdapterProducto> {

    List<producto> miListProducto;
    Context context;
    RequestQueue queue;

    FragmentActivity miActividad;
    public adapterProducto(Context contexto, List<producto> lista, FragmentActivity actividad){


        miActividad=actividad;
        context=contexto;
        miListProducto=lista;
        queue=Volley.newRequestQueue(contexto);

    }
    @NonNull
    @Override
    public adapterProducto.MyAdapterProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);

        return new adapterProducto.MyAdapterProducto(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapterProducto.MyAdapterProducto holder, final int position) {


       // holder.tituloProducto.setText(miListProducto.get(position).getNombreP());
        holder.imageProducto.setImageResource(R.drawable.image);


        cargarImagen(holder,miListProducto.get(position).getRutaImagenP());

        holder.cardProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoProducto miProducto=new infoProducto();
                

                Bundle infoBundle=new Bundle();

                infoBundle.putInt("idProducto",miListProducto.get(position).getIdP());

                miProducto.setArguments(infoBundle);

                miActividad.getSupportFragmentManager().beginTransaction().replace(R.id.frameAuxiliar,miProducto).addToBackStack(null).commit();





            }
        });

    }

    private void cargarImagen(final MyAdapterProducto holder, String rutaImagenP) {

        String ip=context.getString(R.string.ip);
        String urlImagen=ip + "/WebTienda/"+ rutaImagenP;
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                holder.imageProducto.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error de tipo " + error, Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(imageRequest);
    }

    @Override
    public int getItemCount() {
        return miListProducto.size();
    }

    public static class MyAdapterProducto extends RecyclerView.ViewHolder {
        ImageView imageProducto;
        TextView tituloProducto;
        CardView cardProducto;
        public MyAdapterProducto(View itemView) {
            super(itemView);

            imageProducto=itemView.findViewById(R.id.imageP);
            //tituloProducto=itemView.findViewById(R.id.tituloP);
            cardProducto=itemView.findViewById(R.id.cardP);
        }
    }
}
