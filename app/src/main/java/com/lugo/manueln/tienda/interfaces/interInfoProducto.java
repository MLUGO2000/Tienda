package com.lugo.manueln.tienda.interfaces;

import android.app.Activity;
import android.graphics.Bitmap;

import com.lugo.manueln.tienda.modelo.producto;

public interface interInfoProducto {


    interface View{

        void showDataProduct(producto myProduct, Bitmap imageProduct);
        void showErrorProduct(String errorType);
        void addProdductCarSucces();

    }
    interface Presenter{

        void loadDataProduct(Activity activity,int id);
        void showDataProduct(producto myProduct,Bitmap imageProduct);
        void addProductCar(Activity activity,int prooductQuantity);
        void addProdductCarSucces();


        void showErrorProduct(String errorType);

    }

    interface Interactor{

        void loadDataProduct(Activity activity,int id);
        void addProductCar(Activity activity,int Quantity);


    }
}
