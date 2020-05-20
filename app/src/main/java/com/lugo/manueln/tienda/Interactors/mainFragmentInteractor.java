package com.lugo.manueln.tienda.Interactors;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lugo.manueln.tienda.clases.ConexBBDDHelper;
import com.lugo.manueln.tienda.clases.utilidadesBD;
import com.lugo.manueln.tienda.interfaces.interMainFragment;
import com.lugo.manueln.tienda.modelo.URL;
import com.lugo.manueln.tienda.modelo.VolleySingleton;
import com.lugo.manueln.tienda.modelo.orden;
import com.lugo.manueln.tienda.modelo.producto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class mainFragmentInteractor implements interMainFragment.Interactor {


    String urlCategoria;


    interMainFragment.Presenter presenter;

    public mainFragmentInteractor(interMainFragment.Presenter presenter){

        this.presenter=presenter;


    }

    @Override
    public void loadProducts(Activity activity) {

        final Context context=activity.getApplicationContext();

        String ip = URL.ip;

        String urlCategoria = ip + "/WebTienda/wsJSONConsultarCategorias.php";

        //Primera peticion para obtener las categorias Existentes

        JsonObjectRequest objectRequestCategoria = new JsonObjectRequest(Request.Method.GET, urlCategoria, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<String> listCategorias = new ArrayList<>();

                JSONArray miArrayObject = response.optJSONArray("categorias");

                for (int i = 0; i < miArrayObject.length(); i++) {
                    JSONObject jsonObject = miArrayObject.optJSONObject(i);

                    listCategorias.add(jsonObject.optString("nombre"));

                }

                loadProductsCategory(listCategorias,context);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("volley",error.getMessage());

            }
        });

        VolleySingleton.getIntanciaVolley(activity.getApplicationContext()).addToRequestQueue(objectRequestCategoria);


    }

    private void loadProductsCategory(ArrayList<String> listCategorias,Context context) {

        //Bucle Para llamar todas las listas de los productos por categoria
        for (int i=0;i<listCategorias.size();i++){
          loadProductsOfCategory(listCategorias.get(i),context);

        }


    }

    private void loadProductsOfCategory(String category, Context context) {

        //Peticion de Productos segun la Categoria Determinada
        String ip = URL.ip;
        String urlCatego=ip +"/WebTienda/wsJSONConsultarProductosCategoria.php?categoria=" + category;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlCatego, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<producto> miListaProductos = new ArrayList<producto>();
                producto miProducto = null;

                JSONArray jsonArray = response.optJSONArray("producto");
                try {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        miProducto = new producto();

                        JSONObject object = jsonArray.optJSONObject(i);

                        miProducto.setIdP(object.optInt("id"));
                        miProducto.setNombreP(object.optString("nombre"));
                        miProducto.setDescripcionP(object.optString("descripcion"));
                        miProducto.setPrecioP(object.optInt("precio"));
                        miProducto.setRutaImagenP(object.optString("ruta"));
                        miProducto.setCategoriaP(object.optString("categoria"));

                        miListaProductos.add(miProducto);

                    }

                    presenter.showProducts(miListaProductos);
                }catch (Exception eJson){

                    presenter.showError(eJson.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                presenter.showError( error.getMessage());
                Log.e("Volley",error.getMessage());
            }
        });

        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(jsonObjectRequest);



    }


    @Override
    public void loadMainCar(Context context) {

        //Carga los datos del Carrito De Compras


        ArrayList<orden> listaOrdenesCarrito=new ArrayList<>();

        ConexBBDDHelper miConexion=new ConexBBDDHelper(context,"ordenes",null,1);

        SQLiteDatabase miBBDD=miConexion.getReadableDatabase();

        Cursor miCursor=miBBDD.rawQuery("Select * FROM " + utilidadesBD.TABLA_ORDENES,null);

        if(miCursor!=null){

            orden miOrden=null;
            while (miCursor.moveToNext()){

                miOrden=new orden();

                miOrden.setRutaImagenOrden(miCursor.getString(2));
                miOrden.setCantidadOrden(miCursor.getInt(4));

                listaOrdenesCarrito.add(miOrden);
            }

            if(listaOrdenesCarrito.size()!=0){

                presenter.showMainCar(listaOrdenesCarrito);


            }
        }

    }

}
