package com.lugo.manueln.tienda.interfaces;

import android.app.Activity;

public interface Interlogin {

    interface View{

        void validateUser();
        void showIncorrectUserOrPass();
        void showErrorLogin(String error);
    }

    interface Presenter{

        void validateUserPresenter(String user,String password,Activity miActivity);
        void showIncorrectUserOrPassPresenter();
        void showErrorLogin(String error);
    }


    interface Interactor{

        void validateUserInteractor(String user,String password,Activity myActivity);


    }

}
