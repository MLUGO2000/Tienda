package com.lugo.manueln.tienda.Interactors;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lugo.manueln.tienda.clases.ConexBBDDHelper;
import com.lugo.manueln.tienda.clases.utilidadesBD;
import com.lugo.manueln.tienda.interfaces.interInfoProducto;
import com.lugo.manueln.tienda.modelo.URL;
import com.lugo.manueln.tienda.modelo.VolleySingleton;
import com.lugo.manueln.tienda.modelo.orden;
import com.lugo.manueln.tienda.modelo.producto;

import org.json.JSONArray;
import org.json.JSONObject;

public class infoProductoInteractor implements interInfoProducto.Interactor {

    interInfoProducto.Presenter presenter;
    producto myProduct;
    public infoProductoInteractor(interInfoProducto.Presenter presenter) {
        this.presenter=presenter;

    }

    @Override
    public void loadDataProduct(Activity activity, int id) {

        final Context context=activity.getApplicationContext();

        String ip=URL.ip;
        String url=ip + "/WebTienda/wsJSONConsultarProducto.php?idProducto=" +id;

        JsonObjectRequest objectProducto=new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray=response.optJSONArray("producto");

                JSONObject object=jsonArray.optJSONObject(0);

                myProduct=new producto();

                myProduct.setIdP(object.optInt("id"));
                myProduct.setNombreP(object.optString("nombre"));
                myProduct.setDescripcionP(object.optString("descripcion"));
                myProduct.setCategoriaP(object.optString("categoria"));
                myProduct.setPrecioP(object.optInt("precio"));
                myProduct.setRutaImagenP(object.optString("ruta"));


                loadImageProduct(myProduct,context);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                presenter.showErrorProduct(error.getMessage());
            }
        });

        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(objectProducto);
        //colaRequest.add(objectProducto);

    }

    private void loadImageProduct(final producto myProduct, Context context) {

        String ip=URL.ip;
        String urlImagen=ip+ "/WebTienda/" + myProduct.getRutaImagenP();

        ImageRequest image=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                presenter.showDataProduct(myProduct,response);


            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                presenter.showErrorProduct(error.getMessage());
            }
        });

        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(image);

    }


    @Override
    public void addProductCar(Activity activity,int quantity) {

       Context context=activity.getApplicationContext();
        ConexBBDDHelper miConex = new ConexBBDDHelper(context, "ordenes", null, 1);

        SQLiteDatabase miBBDD = miConex.getWritableDatabase();

        orden miOrden=verificarOrdenExistente(myProduct.getIdP(),context);

        if(!miOrden.isOrdenExistente()) {

            ContentValues valores = new ContentValues();

            valores.put(utilidadesBD.CAMPO_ID, myProduct.getIdP());
            valores.put(utilidadesBD.CAMPO_NOMBRE, myProduct.getNombreP());
            valores.put(utilidadesBD.CAMPO_PRECIO, myProduct.getPrecioP());
            valores.put(utilidadesBD.CAMPO_URL, myProduct.getRutaImagenP());
            valores.put(utilidadesBD.CAMPO_CANTIDAD, quantity);

            miBBDD.insert(utilidadesBD.TABLA_ORDENES, null, valores);

        }else {
            ContentValues valores=new ContentValues();

            double precioTotalNuevo=miOrden.getPrecioTotalOrden()+(myProduct.getPrecioP()*(quantity));
            int cantidadTotalNueva=miOrden.getCantidadOrden() + (quantity);


            valores.put(utilidadesBD.CAMPO_TOTAL,precioTotalNuevo);
            valores.put(utilidadesBD.CAMPO_CANTIDAD,cantidadTotalNueva);

            int result=miBBDD.update(utilidadesBD.TABLA_ORDENES,valores,"id = ?" ,new String[]{""+ myProduct.getIdP()});

        }

        miBBDD.close();
        miConex.close();

        presenter.addProdductCarSucces();




    }



    private orden verificarOrdenExistente(int id,Context context) {

        orden ordenEdicion=new orden();

        ordenEdicion.setOrdenExistente(true);

        ConexBBDDHelper miConex= new ConexBBDDHelper(context,"ordenes",null,1);

        SQLiteDatabase db = miConex.getReadableDatabase();

        Cursor miCursor=db.rawQuery("SELECT * FROM " + utilidadesBD.TABLA_ORDENES + " WHERE " +utilidadesBD.CAMPO_ID +  "= ?",new String[]{""+id});


        if(miCursor.moveToNext()){
            //Toast.makeText(getContext(), "Se Encontro un Registro con el mismo id", Toast.LENGTH_SHORT).show();

            int cantidadTotalActual=miCursor.getInt(4);

            double precioTotalActual=miCursor.getDouble(5);

            ordenEdicion.setPrecioTotalOrden(precioTotalActual);
            ordenEdicion.setCantidadOrden(cantidadTotalActual);
            ordenEdicion.setOrdenExistente(true);
        }else {
            //Toast.makeText(getContext(), "No hay ningun Registro con esa id", Toast.LENGTH_SHORT).show();

            ordenEdicion.setOrdenExistente(false);
        }
        miConex.close();
        db.close();

        return ordenEdicion;

    }
}
