package com.lugo.manueln.tienda.Presenters;

import com.lugo.manueln.tienda.actividades.MainActivity;
import com.lugo.manueln.tienda.interfaces.interMain;

public class MainPresenter implements interMain.Presenter {

    MainActivity view;
    public MainPresenter(MainActivity mainActivity) {

        view=mainActivity;
    }


}
