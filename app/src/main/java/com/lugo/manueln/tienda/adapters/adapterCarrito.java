package com.lugo.manueln.tienda.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.orden;
import com.lugo.manueln.tienda.producto;

import java.util.ArrayList;
import java.util.List;

public class adapterCarrito extends RecyclerView.Adapter<adapterCarrito.MyViewHolder> {

    ArrayList<orden> miListOrden;
    Context context;
    RequestQueue requestCola;

    FragmentActivity miActividad;
    public adapterCarrito(Context contexto, ArrayList<orden> lista, FragmentActivity actividad){
        miListOrden=lista;
        context=contexto;
        requestCola=Volley.newRequestQueue(context);

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orden,parent,false);

        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        orden miOrden=miListOrden.get(position);

        cargarImagen(holder,miOrden.getRutaImagenOrden());

        holder.nombreOrden.setText(miOrden.getNombreOrden());
        holder.cantidadOrden.setText("Cantidad:" + miOrden.getCantidadOrden());

        double precio=miOrden.getPrecioProductoOrden();

        holder.totalOrden.setText(miOrden.getCantidadOrden()*precio+"");
    }

    private void cargarImagen(final MyViewHolder holder, String rutaImagenP) {

        String ip=context.getString(R.string.ip);
        String urlImagen=ip + "/WebTienda/"+ rutaImagenP;
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                holder.imageOrden.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error de tipo " + error, Toast.LENGTH_SHORT).show();

            }
        });

        requestCola.add(imageRequest);
    }

    @Override
    public int getItemCount() {
        return miListOrden.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombreOrden,cantidadOrden,totalOrden;
        ImageView imageOrden;
        public MyViewHolder(View itemView) {
            super(itemView);

            nombreOrden=itemView.findViewById(R.id.nombreOrden);
            cantidadOrden=itemView.findViewById(R.id.cantidadOrden);
            totalOrden=itemView.findViewById(R.id.totalOrden);

            imageOrden=itemView.findViewById(R.id.imageOrden);
        }
    }
}
