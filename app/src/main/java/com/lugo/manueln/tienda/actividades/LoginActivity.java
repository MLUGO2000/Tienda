package com.lugo.manueln.tienda.actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lugo.manueln.tienda.Presenters.LoginPresenter;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.interfaces.Interlogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,Interlogin.View {

    private Interlogin.Presenter myPresenter;
    private String user,pass;
    private EditText editUser,editPassword;
    private Button buttonEntrar;

    public LoginActivity(){

        myPresenter=new LoginPresenter(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUser=findViewById(R.id.editUser);
        editPassword=findViewById(R.id.editPass);

        buttonEntrar=findViewById(R.id.bEntrar);


        buttonEntrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        user=editUser.getText().toString();
        pass=editPassword.getText().toString();

        if(myPresenter!=null){
            myPresenter.validateUserPresenter(user,pass,this);
        }



    }

  /*  private void revisarUsuario(String url) {

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    guardarPreferenciasCliente();
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
    }*/




    @Override
    public void validateUser() {

        if(myPresenter!=null){

           // myPresenter.validateUserPresenter(user,pass,get);
        }
    }


    @Override
    public void showIncorrectUserOrPass() {

        Toast.makeText(this,"Correo o Contraseña Incorrecta",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showErrorLogin(String error) {

    }
}
