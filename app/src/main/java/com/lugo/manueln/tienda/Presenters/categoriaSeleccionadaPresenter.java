package com.lugo.manueln.tienda.Presenters;

import android.app.Activity;

import com.lugo.manueln.tienda.Interactors.categoriaSeleccionadaInteractor;
import com.lugo.manueln.tienda.interfaces.interCategoriaSeleccionada;
import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;

public class categoriaSeleccionadaPresenter implements interCategoriaSeleccionada.Presenter{

    interCategoriaSeleccionada.View view;
    interCategoriaSeleccionada.Interactor interactor;

    public categoriaSeleccionadaPresenter(interCategoriaSeleccionada.View view) {
        this.view = view;
        interactor= new categoriaSeleccionadaInteractor(this);
    }

    @Override
    public void loadDateProductsCategories(String category, Activity activity) {

        interactor.loadDateProductsCategories(category,activity);

    }

    @Override
    public void sendDateProductsCategories(ArrayList<producto> list) {

        if(view!=null){

            view.showDateProductsCategories(list);
        }

    }

    @Override
    public void sendError(String error) {

        if(view!=null){

            view.showError(error);
        }

    }

    @Override
    public void onDestroy() {
        view=null;
    }
}
