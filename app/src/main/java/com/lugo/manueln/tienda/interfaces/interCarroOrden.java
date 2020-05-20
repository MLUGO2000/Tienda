package com.lugo.manueln.tienda.interfaces;

import android.app.Activity;

import com.lugo.manueln.tienda.modelo.orden;

import java.util.ArrayList;

public interface interCarroOrden {

    interface View {

        void showCarList(ArrayList<orden> miListOrden,double priceTotal);

        void showUpdatePriceTotal(double priceTotal);


    }

    interface Presenter {

        void loadCarList(Activity activity);
        void showCarList(ArrayList<orden> miListOrden,double priceTotal);

        void updatePriceTotal(ArrayList<orden> listOrden);
        void showUpdatePriceTotal(double priceTotal);

        void onDestroy();

    }

    interface Interactor{

        void loadCardList(Activity activity);

        void updatePriceTotal(ArrayList<orden> listOrden);

    }
}
