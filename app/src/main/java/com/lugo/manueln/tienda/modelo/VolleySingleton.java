package com.lugo.manueln.tienda.modelo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton intanciaVolley;
    private RequestQueue mRequestQueue;
    private static Context contexto;

    private VolleySingleton(Context context){
        contexto=context;
        mRequestQueue=getRequestQueue();
    }



    public static synchronized VolleySingleton getIntanciaVolley(Context context) {
        if(intanciaVolley==null){
            intanciaVolley=new VolleySingleton(context);
        }
        return intanciaVolley;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue==null){
            mRequestQueue=Volley.newRequestQueue(contexto.getApplicationContext());
        }
        return  mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
