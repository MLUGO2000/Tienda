package com.lugo.manueln.tienda.interfaces;

import com.lugo.manueln.tienda.fragments.Buscador;
import com.lugo.manueln.tienda.fragments.carroOrdenFragment;
import com.lugo.manueln.tienda.fragments.categoriaSeleccionada;
import com.lugo.manueln.tienda.fragments.categoriasFragment;
import com.lugo.manueln.tienda.fragments.infoProducto;
import com.lugo.manueln.tienda.fragments.principal;

public interface interFragments extends principal.OnFragmentInteractionListener,infoProducto.OnFragmentInteractionListener,Buscador.OnFragmentInteractionListener,categoriasFragment.OnFragmentInteractionListener,categoriaSeleccionada.OnFragmentInteractionListener,carroOrdenFragment.OnFragmentInteractionListener {
}
