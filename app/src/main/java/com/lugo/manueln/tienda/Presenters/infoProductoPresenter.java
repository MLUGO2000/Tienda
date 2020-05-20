package com.lugo.manueln.tienda.Presenters;

import android.app.Activity;
import android.graphics.Bitmap;

import com.lugo.manueln.tienda.Interactors.infoProductoInteractor;
import com.lugo.manueln.tienda.interfaces.interInfoProducto;
import com.lugo.manueln.tienda.modelo.producto;

public class infoProductoPresenter implements interInfoProducto.Presenter {

    interInfoProducto.View view;
    interInfoProducto.Interactor interactor;
    public infoProductoPresenter(interInfoProducto.View view) {

        this.view=view;
        this.interactor=new infoProductoInteractor(this);
    }

    @Override
    public void loadDataProduct(Activity activity,int id) {

        interactor.loadDataProduct(activity,id);
    }

    @Override
    public void showDataProduct(producto myProduct, Bitmap imageProduct) {

        if(view!=null){
            view.showDataProduct(myProduct,imageProduct);
        }
    }

    @Override
    public void addProductCar(Activity activity, int prooductQuantity) {

        interactor.addProductCar(activity,prooductQuantity);
    }

    @Override
    public void addProdductCarSucces() {

        if(view!=null){

            view.addProdductCarSucces();
        }

    }

    @Override
    public void showErrorProduct(String errorType) {
        if(view!=null){

            view.showErrorProduct(errorType);
        }
    }

    @Override
    public void onDestroy() {
        view=null;
    }
}
