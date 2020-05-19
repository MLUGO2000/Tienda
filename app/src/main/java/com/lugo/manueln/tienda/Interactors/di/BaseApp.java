package com.lugo.manueln.tienda.Interactors.di;

import android.app.Application;

public class BaseApp extends Application {

    static componentApi componentApi;

    @Override
    public void onCreate() {
        super.onCreate();

        componentApi=DaggercomponentApi.builder().moduleApi(new moduleApi()).build();
    }

    public componentApi getComponentApi(){
        return componentApi;
    }
}
