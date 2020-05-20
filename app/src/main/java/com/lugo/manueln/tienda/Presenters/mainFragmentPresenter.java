package com.lugo.manueln.tienda.Presenters;

import android.app.Activity;

import com.lugo.manueln.tienda.Interactors.mainFragmentInteractor;
import com.lugo.manueln.tienda.interfaces.interMainFragment;
import com.lugo.manueln.tienda.modelo.orden;
import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;

public class mainFragmentPresenter implements interMainFragment.Presenter {

    interMainFragment.View view;
    interMainFragment.Interactor interactor;

    public mainFragmentPresenter(interMainFragment.View view){

        this.view=view;
        interactor=new mainFragmentInteractor(this);


        }

    @Override
    public void loadProducts(Activity activity) {

        interactor.loadProducts(activity);

    }

    @Override
    public void loadMainCar(Activity activity) {

        interactor.loadMainCar(activity.getApplicationContext());
    }

    @Override
    public void showMainCar(ArrayList<orden> listOrdenes) {
        if(view!=null){
            view.showMainCar(listOrdenes);
        }
    }

    @Override
    public void showProducts(ArrayList<producto>listProducts) {

        if(view!=null) {
            view.showProducts(listProducts);

        }
    }

    @Override
    public void showError(String error) {

        if(view!=null){

            view.showError(error);
        }
    }

    @Override
    public void onDestroy() {
        view=null;
    }
}
