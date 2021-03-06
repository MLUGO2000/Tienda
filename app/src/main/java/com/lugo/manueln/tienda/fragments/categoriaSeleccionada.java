package com.lugo.manueln.tienda.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lugo.manueln.tienda.Presenters.categoriaSeleccionadaPresenter;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.adapters.adapterProductoTodos;
import com.lugo.manueln.tienda.interfaces.interCategoriaSeleccionada;
import com.lugo.manueln.tienda.modelo.producto;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link categoriaSeleccionada.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link categoriaSeleccionada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class categoriaSeleccionada extends Fragment implements interCategoriaSeleccionada.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    interCategoriaSeleccionada.Presenter presenter;

    public categoriaSeleccionada() {

        presenter=new categoriaSeleccionadaPresenter(this);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment categoriaSeleccionada.
     */
    // TODO: Rename and change types and number of parameters
    public static categoriaSeleccionada newInstance(String param1, String param2) {
        categoriaSeleccionada fragment = new categoriaSeleccionada();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_categoria_seleccionada, container, false);

        buscarCategoria=vista.findViewById(R.id.editBuscadorCategoria);

        miRecyclerSelec=vista.findViewById(R.id.buscarCategoriaRecycler);

        LinearLayoutManager miManager=new LinearLayoutManager(getContext());
        miManager.setOrientation(LinearLayoutManager.VERTICAL);

        miRecyclerSelec.setLayoutManager(miManager);


        Bundle bundle=getArguments();

        String categoria=bundle.getString("categoria");


        presenter.loadDateProductsCategories(categoria,getActivity());

        return vista;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showDateProductsCategories(ArrayList<producto> listProducts) {
        final adapterProductoTodos miAdapter=new adapterProductoTodos(getContext(),listProducts,getActivity());

        miRecyclerSelec.setAdapter(miAdapter);

        buscarCategoria.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                miAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),"Se produjo un error de tipo: " + error ,Toast.LENGTH_LONG);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private RecyclerView miRecyclerSelec;
    private EditText buscarCategoria;

    private JsonObjectRequest objectRequest;
    private RequestQueue colaPeticiones;
}
