package com.lugo.manueln.tienda.actividades;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lugo.manueln.tienda.fragments.Buscador;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.fragments.carroOrdenFragment;
import com.lugo.manueln.tienda.fragments.categoriasFragment;
import com.lugo.manueln.tienda.interfaces.interFragments;
import com.lugo.manueln.tienda.fragments.principal;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,interFragments {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment miFragment=this.getSupportFragmentManager().findFragmentById(R.id.frameAuxiliar);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(miFragment!=null){

            if(miFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(miFragment).commit();
            }else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSelect=false;
        if (id == R.id.nav_pri) {
            miFragment=new principal();
            fragmentSelect=true;

        } else if (id == R.id.nav_cat) {
            miFragment=new categoriasFragment();
            fragmentSelect=true;

        } else if (id == R.id.nav_promo) {

            miFragment=new carroOrdenFragment();
            fragmentSelect=true;

        } else if (id == R.id.nav_buscar) {

            miFragment=new Buscador();
            fragmentSelect=true;

        } else if (id == R.id.nav_ubi) {

        }

        Fragment fragmentAux=this.getSupportFragmentManager().findFragmentById(R.id.frameAuxiliar);

        if(fragmentAux!=null){
            if(fragmentAux.isVisible()){
                getSupportFragmentManager().beginTransaction().remove(fragmentAux).commit();
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.framePrincipal,miFragment).addToBackStack(null).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}