package com.lugo.manueln.tienda.interfaces;

import android.app.Activity;

import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;

public interface interCategoriaSeleccionada {

    interface View{


        void showDateProductsCategories(ArrayList<producto> list);

        void showError(String error);


    }

    interface Presenter{


        void loadDateProductsCategories(String category, Activity activity);
        void sendDateProductsCategories(ArrayList<producto> list);

        void sendError(String error);

        void onDestroy();

    }

    interface Interactor{

        void loadDateProductsCategories(String category,Activity activity);


    }

}
