package com.lugo.manueln.tienda.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lugo.manueln.tienda.Presenters.infoProductoPresenter;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.interfaces.interInfoProducto;
import com.lugo.manueln.tienda.modelo.producto;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infoProducto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infoProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoProducto extends Fragment implements interInfoProducto.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idProducto";


    // TODO: Rename and change types of parameters
    private int mParamId;
    private OnFragmentInteractionListener mListener;


    interInfoProducto.Presenter presenter;

    public infoProducto() {

        presenter=new infoProductoPresenter(this);


    }


    // TODO: Rename and change types and number of parameters
    public static infoProducto newInstance(int paramId) {
        infoProducto fragment = new infoProducto();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, paramId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_info_producto, container, false);



        imagenProducto=vista.findViewById(R.id.imageProductoInfo);
        txtvNombre=vista.findViewById(R.id.txtNombreInfo);
        txtvCategoria=vista.findViewById(R.id.txtCategoriaInfo);
        txtvPrecio=vista.findViewById(R.id.txtPrecioInfo);
        bAgregar=vista.findViewById(R.id.buttonAgregar);
        editTextCantidad=vista.findViewById(R.id.editCantidad);

        bAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addProductCar(getActivity(),Integer.parseInt(editTextCantidad.getText().toString()));
            }
        });



        presenter.loadDataProduct(getActivity(),mParamId);
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
    public void showDataProduct(producto myProduct, Bitmap imageProduct) {

        txtvNombre.setText(myProduct.getNombreP());
        txtvCategoria.setText(myProduct.getCategoriaP());
        txtvPrecio.setText(myProduct.getPrecioP()+"$");

        imagenProducto.setImageBitmap(imageProduct);
    }

    @Override
    public void showErrorProduct(String errorType) {
        Toast.makeText(getContext(),"error de tipo:" + errorType,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void addProdductCarSucces() {
        Toast.makeText(getContext(), "Agregado", Toast.LENGTH_SHORT).show();

        mainFragment fragmentMainFragment =null;
        try {
            fragmentMainFragment = (mainFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.framePrincipal);
        }catch (Exception e){
            e.printStackTrace();
        }



        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();//Remueve el Fragment info

        if(fragmentMainFragment !=null){
            fragmentMainFragment.refreshMainCar();//Refresca Carrito en Fragment Principal
        }
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



    ImageView imagenProducto;
    TextView txtvNombre,txtvCategoria,txtvPrecio;
    EditText editTextCantidad;
    Button bAgregar;
    producto myProduct;
}
