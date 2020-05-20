package com.lugo.manueln.tienda.interfaces;

import android.app.Activity;
import android.content.Context;

import com.lugo.manueln.tienda.modelo.orden;
import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;

public interface interMainFragment {

    interface View {


        void showMainCar(ArrayList<orden> listOrdenes);
        void showError(String error);
        void showProducts(ArrayList<producto> listProducts);
    }
    interface Presenter{

        void loadProducts(Activity fragmentActivity);
        void loadMainCar(Activity fragmentActivity);

        void showMainCar(ArrayList<orden> listOrdenes);
        void showProducts(ArrayList<producto> listProducts);
        void showError(String error);



        void onDestroy();

    }

    interface  Interactor{


        void loadProducts(Activity fragmentActivity);
        void loadMainCar(Context context);
    }
}
