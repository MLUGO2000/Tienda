package com.lugo.manueln.tienda.Presenters;

import android.content.Context;

import com.lugo.manueln.tienda.Interactors.categoriasInteractor;
import com.lugo.manueln.tienda.interfaces.interCategorias;

import java.util.ArrayList;

public class categoriasPresenter implements interCategorias.Presenter {

    interCategorias.View view;
    interCategorias.Interactor interactor;
    public categoriasPresenter(interCategorias.View view) {

        this.view=view;

        interactor=new categoriasInteractor(this);


    }

    @Override
    public void loadNameCategories(Context context) {

        interactor.loadNameCategories(context);

    }

    @Override
    public void showNameCategories(ArrayList<String> nameCategories) {

        if(view!=null){

            view.showNameCategories(nameCategories);
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
