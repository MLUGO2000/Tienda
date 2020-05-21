package com.lugo.manueln.tienda.Interactors;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lugo.manueln.tienda.adapters.adapterCategorias;
import com.lugo.manueln.tienda.interfaces.interCategorias;
import com.lugo.manueln.tienda.modelo.URL;
import com.lugo.manueln.tienda.modelo.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class categoriasInteractor implements interCategorias.Interactor {

    interCategorias.Presenter presenter;
    public categoriasInteractor(interCategorias.Presenter presenter) {

        this.presenter=presenter;

    }

    @Override
    public void loadNameCategories(Context context) {

        final ArrayList<String> nameCategories=new ArrayList<>();

        String ip=URL.ip;

        String url=ip + "/WebTienda/wsJSONConsultarCategorias.php";


        JsonObjectRequest objectRequest =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray miArrayObject=response.optJSONArray("categorias");

                for(int i=0;i<miArrayObject.length();i++){
                    JSONObject jsonObject=miArrayObject.optJSONObject(i);

                    nameCategories.add(jsonObject.optString("nombre"));

                }

                presenter.showNameCategories(nameCategories);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               presenter.showError(error.getMessage());
            }
        });


        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(objectRequest);



    }
}
