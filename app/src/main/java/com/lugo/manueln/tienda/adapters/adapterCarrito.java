package com.lugo.manueln.tienda.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.tienda.clases.ConexBBDDHelper;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.fragments.carroOrdenFragment;
import com.lugo.manueln.tienda.modelo.orden;
import com.lugo.manueln.tienda.clases.utilidadesBD;

import java.util.ArrayList;

public class adapterCarrito extends RecyclerView.Adapter<adapterCarrito.MyViewHolder> {

    ArrayList<orden> miListOrden;
    Context context;
    RequestQueue requestCola;

    FragmentActivity miActividad;
    public adapterCarrito(Context contexto, ArrayList<orden> lista, FragmentActivity actividad){
        miListOrden=lista;
        context=contexto;
        requestCola=Volley.newRequestQueue(context);
        miActividad=actividad;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orden,parent,false);

        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        orden miOrden=miListOrden.get(position);

        cargarImagen(holder,miOrden.getRutaImagenOrden());

        holder.nombreOrden.setText(miOrden.getNombreOrden());
        holder.cantidadOrden.setText("Cantidad:" + miOrden.getCantidadOrden());



        holder.totalOrden.setText(miOrden.getPrecioTotalOrden()+"");


        holder.cardOrden.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Toast.makeText(context, "Seleccionando Nota Con nombre" + notaList.get(position).getTitulo(), Toast.LENGTH_SHORT).show();


                AlertDialog.Builder miAlert=new AlertDialog.Builder(context);

                miAlert.setTitle("¿Eliminar Orden Seleccionada?");

                miAlert.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int posicion=0;
                        if(miListOrden.size()==1){
                            posicion=0;
                        }else {
                            posicion=position;
                        }
                        removerOrden(miListOrden.get(posicion).getIdProductoOrden(),posicion);

                    }
                });

                miAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


                miAlert.show();


                return true;
            }
        });
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
        CardView cardOrden;
        public MyViewHolder(View itemView) {
            super(itemView);

            cardOrden=itemView.findViewById(R.id.cardOrden);
            nombreOrden=itemView.findViewById(R.id.nombreOrden);
            cantidadOrden=itemView.findViewById(R.id.cantidadOrden);
            totalOrden=itemView.findViewById(R.id.totalOrden);

            imageOrden=itemView.findViewById(R.id.imageOrden);
        }
    }

    private void removerOrden(int id,int p_posicion) {

        ConexBBDDHelper miConex=new ConexBBDDHelper(context,"ordenes",null,1);

        SQLiteDatabase miBD=miConex.getWritableDatabase();

        miBD.delete(utilidadesBD.TABLA_ORDENES,utilidadesBD.CAMPO_ID+ "=" +id,null);

        miListOrden.remove(p_posicion);
        //notifyItemRemoved(p_posicion);
        notifyDataSetChanged();

        carroOrdenFragment carroFragment=(carroOrdenFragment) miActividad.getSupportFragmentManager().findFragmentById(R.id.framePrincipal);

        carroFragment.reActualizaPrecio(miListOrden);

        miConex.close();

    }
}
