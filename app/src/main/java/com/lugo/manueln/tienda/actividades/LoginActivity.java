package com.lugo.manueln.tienda.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.modelo.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUser=findViewById(R.id.editUser);
        editPassword=findViewById(R.id.editPass);

        buttonEntrar=findViewById(R.id.bEntrar);


        buttonEntrar.setOnClickListener(this);

    }




    EditText editUser,editPassword;
    Button buttonEntrar;
    String user,pass;

    @Override
    public void onClick(View view) {

        user=editUser.getText().toString();
        pass=editPassword.getText().toString();

        if(!user.isEmpty() & !pass.isEmpty()){

            String ip=getString(R.string.ip);

            String url=ip + "/WebTienda/wsJSONInicioSesion.php";
            
            revisarUsuario(url);

        }else {
            Toast.makeText(this,"Por Favor Introducir Datos",Toast.LENGTH_SHORT).show();
        }

    }

    private void revisarUsuario(String url) {

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Usuario o Contraseña Incorrecta",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();

                parametros.put("usuario",user);
                parametros.put("password",pass);
                return parametros;
            }
        };

        VolleySingleton.getIntanciaVolley(this).addToRequestQueue(request);
    }
}
