package com.lugo.manueln.tienda.actividades;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.fragments.marcadorMapaFragment;
import com.lugo.manueln.tienda.interfaces.interFragments;

import java.io.Console;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,interFragments {

    private GoogleMap mMap;

    @Override
    public void onBackPressed() {

        Fragment miFragment=this.getSupportFragmentManager().findFragmentById(R.id.frameMarcador);

        if(miFragment!=null){
            if(miFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(miFragment).commit();
            }else {
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng tienda1 = new LatLng(4.707551, -74.072809);
        LatLng tienda2 = new LatLng(4.705589, -74.071805);
        LatLng tienda3 = new LatLng(4.706043, -74.074096);


        MarkerOptions markerOptionsTienda1 = new MarkerOptions();
        MarkerOptions markerOptionsTienda2 = new MarkerOptions();
        MarkerOptions markerOptionsTienda3 = new MarkerOptions();


        markerOptionsTienda1.position(tienda1);
        markerOptionsTienda1.title("Tienda 1");
        markerOptionsTienda1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        markerOptionsTienda2.position(tienda2);
        markerOptionsTienda2.title("Tienda 2");
        markerOptionsTienda2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        markerOptionsTienda3.position(tienda3);
        markerOptionsTienda3.title("Tienda 3");
        markerOptionsTienda3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));


        mMap.addMarker(markerOptionsTienda1);
        mMap.addMarker(markerOptionsTienda2);
        mMap.addMarker(markerOptionsTienda3);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Fragment miFragment=new marcadorMapaFragment();
                Bundle datosTienda=null;
                switch (marker.getId()){

                    case "m0":


                        datosTienda=new Bundle();
                        datosTienda.putString("ID",marker.getId());
                        datosTienda.putString("nombre","Tienda 1");

                        break;

                    case "m1":

                        datosTienda=new Bundle();
                        datosTienda.putString("ID",marker.getId());
                        datosTienda.putString("nombre","Tienda 2");

                        break;

                    case "m2":

                        datosTienda=new Bundle();
                        datosTienda.putString("ID",marker.getId());
                        datosTienda.putString("nombre","Tienda 3");

                        break;

                        }

                       if(miFragment!=null) {
                           miFragment.setArguments(datosTienda);

                           getSupportFragmentManager().beginTransaction().replace(R.id.frameMarcador,miFragment).commit();
                       }


                return false;
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda1, 16));
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
