package com.lugo.manueln.tienda;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class adapterProductoTodos extends RecyclerView.Adapter<adapterProductoTodos.MyAdapterProducto> implements Filterable {


    ArrayList<producto> miListProducto;
    ArrayList<producto> miListProductoFiltrada;
    CustomFilter mFilter;

    Context context;
    RequestQueue queue;

    FragmentActivity miActividad;
    public adapterProductoTodos(Context contexto, ArrayList<producto> lista, FragmentActivity actividad){

        miActividad=actividad;
        context=contexto;
        miListProducto=lista;
        queue=Volley.newRequestQueue(contexto);
        miListProductoFiltrada=new ArrayList<>();
        miListProductoFiltrada.addAll(lista);
        mFilter=new CustomFilter(adapterProductoTodos.this);


    }
    @NonNull
    @Override
    public adapterProductoTodos.MyAdapterProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buscar,parent,false);

        return new adapterProductoTodos.MyAdapterProducto(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapterProductoTodos.MyAdapterProducto holder, final int position) {


        holder.tituloProducto.setText(miListProductoFiltrada.get(position).getNombreP());
        holder.categoriaProducto.setText(miListProductoFiltrada.get(position).getCategoriaP());
        holder.precioProducto.setText(miListProductoFiltrada.get(position).getPrecioP()+" $");



        cargarImagen(holder,miListProductoFiltrada.get(position).getRutaImagenP());



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
        return miListProductoFiltrada.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public static class MyAdapterProducto extends RecyclerView.ViewHolder {
        ImageView imageProducto;
        TextView tituloProducto,categoriaProducto,precioProducto;

        public MyAdapterProducto(View itemView) {
            super(itemView);

            imageProducto=itemView.findViewById(R.id.imageItemBus);
            tituloProducto=itemView.findViewById(R.id.txtNombreBus);
            categoriaProducto=itemView.findViewById(R.id.txtCategoriaBus);
            precioProducto=itemView.findViewById(R.id.txtPrecioBus);

        }
    }

    public class CustomFilter extends Filter {
        private adapterProductoTodos listAdapter;

        private CustomFilter(adapterProductoTodos listAdapter) {
            super();
            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            miListProductoFiltrada.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                miListProductoFiltrada.addAll(miListProducto);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final producto itemProducto : miListProducto) {
                    if (itemProducto.getNombreP().toLowerCase().contains(filterPattern)) {
                        miListProductoFiltrada.add(itemProducto);
                    }
                }
            }
            results.values = miListProductoFiltrada;
            results.count = miListProductoFiltrada.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          this.listAdapter.notifyDataSetChanged();
        }
    }

}
