package com.lugo.manueln.tienda.Interactors;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lugo.manueln.tienda.Interactors.di.BaseApp;
import com.lugo.manueln.tienda.actividades.MainActivity;
import com.lugo.manueln.tienda.interfaces.Interlogin;
import com.lugo.manueln.tienda.modelo.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


public class LoginModule implements Interlogin.Interactor {

    Interlogin.Presenter myPresenter;

    @Inject
    String urlLogin;

    public LoginModule(Interlogin.Presenter myPresenter){

        this.myPresenter=myPresenter;


    }

    @Override
    public void validateUserInteractor(final String user, final String password, final Activity myActivity) {

        setupDagger(myActivity);

        StringRequest request=new StringRequest(Request.Method.POST, urlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){

                    loadMainActivity(myActivity);
                }else{

                    myPresenter.showIncorrectUserOrPassPresenter();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                myPresenter.showErrorLogin(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();

                parametros.put("usuario",user);
                parametros.put("password",password);
                return parametros;
            }
        };

        VolleySingleton.getIntanciaVolley(myActivity).addToRequestQueue(request);

    }

    private void setupDagger(Activity myActivity) {

        ((BaseApp)myActivity.getApplication()).getComponentApi().inject(this);
    }


    private void loadMainActivity(Activity activity){

        Intent intent=new Intent(activity,MainActivity.class);

        activity.startActivity(intent);


    }

    /*
    public void recuperarPreferenciasClienteInteractor() {

        SharedPreferences preferences=getSharedPreferences("PreferencesLogin",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();

        editor.putString("User",user);
        editor.putString("Pass",pass);
        editor.putBoolean("sesion",true);

        editor.commit();

    }*/
}
