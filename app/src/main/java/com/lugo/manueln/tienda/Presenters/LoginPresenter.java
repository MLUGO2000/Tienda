package com.lugo.manueln.tienda.Presenters;


import android.app.Activity;

import com.lugo.manueln.tienda.Interactors.LoginModule;
import com.lugo.manueln.tienda.interfaces.Interlogin;


public class LoginPresenter implements Interlogin.Presenter {

    Interlogin.View myView;
    Interlogin.Interactor myInteractor;

    public LoginPresenter(Interlogin.View myView){

        this.myView=myView;
        myInteractor=new LoginModule(this);

    }


    @Override
    public void validateUserPresenter(String user,String password,Activity myActivity) {

        if(myInteractor!=null){

            myInteractor.validateUserInteractor(user,password,myActivity);
        }
    }


    @Override
    public void showIncorrectUserOrPassPresenter() {

        if(myView!=null){
            myView.showIncorrectUserOrPass();
        }

    }

    @Override
    public void showErrorLogin(String error) {

    }

}
