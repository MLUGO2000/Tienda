package com.lugo.manueln.tienda.interfaces;

import android.app.Activity;

import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;

public interface interBuscador {

    interface View{


        void showDateProducts(ArrayList<producto> miListaProductos);

        void showError(String messageError);
    }

    interface Presenter{

        void loadDateProducts(Activity activity);

        void sendDateProducts(ArrayList<producto> miListaProductos);
        void showError(String messageError);

        
        void onDestroy();



    }

    interface Interactor{


        void loadDateProducts(Activity activity);
    }
}
