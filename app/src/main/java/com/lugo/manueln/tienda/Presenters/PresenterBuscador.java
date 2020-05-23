package com.lugo.manueln.tienda.Presenters;

import android.app.Activity;

import com.lugo.manueln.tienda.Interactors.interactorBuscador;
import com.lugo.manueln.tienda.interfaces.interBuscador;
import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;

public class PresenterBuscador implements interBuscador.Presenter {

    interBuscador.View view;
    interBuscador.Interactor interactor;
    public PresenterBuscador(interBuscador.View view) {

        this.view=view;
        interactor=new interactorBuscador(this);

    }


    @Override
    public void loadDateProducts(Activity activity) {
        interactor.loadDateProducts(activity);
    }

    @Override
    public void sendDateProducts(ArrayList<producto> miListaProductos) {
        if(view!=null){

            view.showDateProducts(miListaProductos);
        }
    }

    @Override
    public void showError(String messageError) {

        view.showError(messageError);

    }

    @Override
    public void onDestroy() {
        view=null;

    }
}
