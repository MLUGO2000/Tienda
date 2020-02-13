package com.lugo.manueln.tienda.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.orden;

import java.util.ArrayList;


public class adapterCarritoPrincipal extends RecyclerView.Adapter<adapterCarritoPrincipal.MyViewHolder>{

    ArrayList<orden> miListOrdenPrincipal;
    Context context;
    RequestQueue requestCola;

    public adapterCarritoPrincipal(Context contexto, ArrayList<orden> lista, FragmentActivity actividad){

        context=contexto;
        miListOrdenPrincipal=lista;
        requestCola=Volley.newRequestQueue(context);


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito_principal,parent,false);

        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        orden orden=miListOrdenPrincipal.get(position);

        String ruta=orden.getRutaImagenOrden();

        int cantidad=orden.getCantidadOrden();

        holder.txtCantidadProducto.setText(cantidad+"");

        cargarImagen(holder,ruta);



    }

    private void cargarImagen(final MyViewHolder holder, String url) {

        String ip=context.getString(R.string.ip);

        String urlImagen=ip + "/WebTienda/" + url;

        ImageRequest imageRequestProducto=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                holder.imageProducto.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestCola.add(imageRequestProducto);
    }

    @Override
    public int getItemCount() {
        return miListOrdenPrincipal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProducto;
        TextView txtCantidadProducto;
        public MyViewHolder(View itemView) {
            super(itemView);

            txtCantidadProducto=itemView.findViewById(R.id.txtCantidadCarrito);
            imageProducto=itemView.findViewById(R.id.imgViewProducto);

        }
    }
}
