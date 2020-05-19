package com.lugo.manueln.tienda.Interactors.di;

import com.lugo.manueln.tienda.modelo.URL;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class moduleApi {

    @Provides
    @Singleton
    String urlLogin(){
        return URL.LOGIN;
    }

}
