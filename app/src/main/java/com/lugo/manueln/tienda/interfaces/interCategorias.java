package com.lugo.manueln.tienda.interfaces;

import android.content.Context;

import java.util.ArrayList;

public interface interCategorias {

    interface View{

        void showNameCategories(ArrayList<String> nameCategories);
        void showError(String error);
    }

    interface Presenter{

        void loadNameCategories(Context context);
        void showNameCategories(ArrayList<String> nameCategories);
        void showError(String error);

        void onDestroy();
    }

    interface Interactor{

        void loadNameCategories(Context context);

    }
}
