package com.lugo.manueln.tienda.Presenters;


import android.app.Activity;

import com.lugo.manueln.tienda.Interactors.carroOrdenInteractor;
import com.lugo.manueln.tienda.interfaces.interCarroOrden;
import com.lugo.manueln.tienda.modelo.orden;

import java.util.ArrayList;

public class carroOrdenPresenter implements interCarroOrden.Presenter {
    interCarroOrden.View view;
    interCarroOrden.Interactor interactor;
    public carroOrdenPresenter(interCarroOrden.View view) {

        this.view=view;
        interactor=new carroOrdenInteractor(this);

    }

    @Override
    public void loadCarList(Activity activity) {

        interactor.loadCardList(activity);
    }

    @Override
    public void showCarList(ArrayList<orden> miListOrden, double priceTotal) {

        if(view!=null){

            view.showCarList(miListOrden,priceTotal);
        }

    }

    @Override
    public void updatePriceTotal(ArrayList<orden> listOrden) {
        interactor.updatePriceTotal(listOrden);


    }

    @Override
    public void showUpdatePriceTotal(double priceTotal) {

        if (view != null) {

            view.showUpdatePriceTotal(priceTotal);
        }
    }

    @Override
    public void onDestroy() {
        view=null;
    }
}
