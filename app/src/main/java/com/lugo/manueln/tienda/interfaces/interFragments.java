package com.lugo.manueln.tienda.interfaces;

import com.lugo.manueln.tienda.fragments.mainFragment;
import com.lugo.manueln.tienda.fragments.marcadorMapaFragment;
import com.lugo.manueln.tienda.fragments.Buscador;
import com.lugo.manueln.tienda.fragments.carroOrdenFragment;
import com.lugo.manueln.tienda.fragments.categoriaSeleccionada;
import com.lugo.manueln.tienda.fragments.categoriasFragment;
import com.lugo.manueln.tienda.fragments.infoProducto;

public interface interFragments extends mainFragment.OnFragmentInteractionListener,infoProducto.OnFragmentInteractionListener,Buscador.OnFragmentInteractionListener,categoriasFragment.OnFragmentInteractionListener,categoriaSeleccionada.OnFragmentInteractionListener,carroOrdenFragment.OnFragmentInteractionListener, marcadorMapaFragment.OnFragmentInteractionListener {
}
