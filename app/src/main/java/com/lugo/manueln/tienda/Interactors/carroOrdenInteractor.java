package com.lugo.manueln.tienda.Interactors;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lugo.manueln.tienda.clases.ConexBBDDHelper;
import com.lugo.manueln.tienda.clases.utilidadesBD;
import com.lugo.manueln.tienda.interfaces.interCarroOrden;
import com.lugo.manueln.tienda.modelo.orden;

import java.util.ArrayList;

public class carroOrdenInteractor implements interCarroOrden.Interactor{

    interCarroOrden.Presenter presenter;
    public carroOrdenInteractor(interCarroOrden.Presenter presenter) {

        this.presenter=presenter;

    }

    @Override
    public void loadCardList(Activity activity) {

        Context context=activity.getApplicationContext();


        double precioTotalOrden=0.0;
        ConexBBDDHelper miConexion=new ConexBBDDHelper(context,"ordenes",null,1);

        SQLiteDatabase miBBDD=miConexion.getReadableDatabase();

        Cursor miCursor=miBBDD.rawQuery("SELECT * FROM " + utilidadesBD.TABLA_ORDENES,null);

        ArrayList<orden> miListOrden=new ArrayList<>();
        if(miCursor!=null){

            orden miOrden=null;

            while (miCursor.moveToNext()){

                miOrden=new orden();



                miOrden.setIdProductoOrden(miCursor.getInt(0));
                miOrden.setNombreOrden(miCursor.getString(1));
                miOrden.setRutaImagenOrden(miCursor.getString(2));
                miOrden.setPrecioProductoOrden(miCursor.getDouble(3));
                miOrden.setCantidadOrden(miCursor.getInt(4));

                double precioOrden=miOrden.getPrecioProductoOrden()*miOrden.getCantidadOrden();
                miOrden.setPrecioTotalOrden(precioOrden);

                precioTotalOrden+=precioOrden;
                miListOrden.add(miOrden);
            }

        }

        if(miListOrden.size()!=0){

            presenter.showCarList(miListOrden,precioTotalOrden);


        }

    }

    @Override
    public void updatePriceTotal(ArrayList<orden> listOrden) {

        double priceTotal=0;
        for(int i=0;i<listOrden.size();i++){

            priceTotal+=listOrden.get(i).getPrecioTotalOrden();
        }

        presenter.showUpdatePriceTotal(priceTotal);
    }
}
