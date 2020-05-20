package com.lugo.manueln.tienda.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
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
import com.lugo.manueln.tienda.fragments.carroOrdenFragment;
import com.lugo.manueln.tienda.modelo.URL;
import com.lugo.manueln.tienda.modelo.VolleySingleton;
import com.lugo.manueln.tienda.modelo.orden;

import java.util.ArrayList;


public class adapterCarritoPrincipal extends RecyclerView.Adapter<adapterCarritoPrincipal.MyViewHolder>{

    ArrayList<orden> miListOrdenPrincipal;
    Context context;
    RequestQueue requestCola;
    FragmentActivity actividadProveniente;

    public adapterCarritoPrincipal(Context contexto, ArrayList<orden> lista, FragmentActivity actividad){

        context=contexto;
        miListOrdenPrincipal=lista;
        requestCola=Volley.newRequestQueue(context);
        actividadProveniente=actividad;


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

        holder.cardProductoCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carroOrdenFragment miFragmentCarro=new carroOrdenFragment();

                actividadProveniente.getSupportFragmentManager().beginTransaction().replace(R.id.framePrincipal,miFragmentCarro).addToBackStack(null).commit();
            }
        });

    }

    private void cargarImagen(final MyViewHolder holder, String url) {

        String ip=URL.ip;

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

        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequestProducto);
        //requestCola.add(imageRequestProducto);
    }

    @Override
    public int getItemCount() {
        return miListOrdenPrincipal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProducto;
        TextView txtCantidadProducto;
        CardView cardProductoCarrito;
        public MyViewHolder(View itemView) {
            super(itemView);

            cardProductoCarrito=itemView.findViewById(R.id.cardCarritoP);
            txtCantidadProducto=itemView.findViewById(R.id.txtCantidadCarrito);
            imageProducto=itemView.findViewById(R.id.imgViewProducto);

        }
    }
}
