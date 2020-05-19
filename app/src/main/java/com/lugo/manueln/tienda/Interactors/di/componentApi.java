package com.lugo.manueln.tienda.Interactors.di;

import com.lugo.manueln.tienda.Interactors.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = moduleApi.class)
public interface componentApi {

    void inject(LoginModule loginModule);


}
