package com.lugo.manueln.tienda.Interactors;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lugo.manueln.tienda.interfaces.interCategoriaSeleccionada;
import com.lugo.manueln.tienda.modelo.URL;
import com.lugo.manueln.tienda.modelo.VolleySingleton;
import com.lugo.manueln.tienda.modelo.producto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class categoriaSeleccionadaInteractor implements interCategoriaSeleccionada.Interactor {

    interCategoriaSeleccionada.Presenter presenter;
    public categoriaSeleccionadaInteractor(interCategoriaSeleccionada.Presenter presenter) {

        this.presenter=presenter;

    }

    @Override
    public void loadDateProductsCategories(String category, Activity activity) {

        Context context=activity.getApplicationContext();

        String ip=URL.ip;

        String url=ip + "/WebTienda/wsJSONConsultarProductosCategoria.php?categoria=" + category;

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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
                        miProducto.setCategoriaP(object.optString("categoria"));
                        miProducto.setPrecioP(object.optInt("precio"));
                        miProducto.setRutaImagenP(object.optString("ruta"));

                        miListaProductos.add(miProducto);

                    }

                    presenter.sendDateProductsCategories(miListaProductos);




                } catch (Exception ejson) {

                    presenter.sendError(ejson.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                presenter.sendError(error.getMessage());
                Log.e("Error Web","Error Volley de Tipo :"  + error);
            }
        });


        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(objectRequest);

    }
}
