package com.lugo.manueln.tienda;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lugo.manueln.tienda.adapters.adapterCarrito;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link carroOrdenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link carroOrdenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carroOrdenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public carroOrdenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment carroOrdenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static carroOrdenFragment newInstance(String param1, String param2) {
        carroOrdenFragment fragment = new carroOrdenFragment();
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
        View vista= inflater.inflate(R.layout.fragment_carro_orden, container, false);

        textViewTotal=vista.findViewById(R.id.txtTotalOrden);
        recyclerCarrito=vista.findViewById(R.id.recyclerCarro);

        LinearLayoutManager miManager=new LinearLayoutManager(getContext());

        miManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerCarrito.setLayoutManager(miManager);

        cargarListaCarrito();

        return vista;
    }

    private void cargarListaCarrito() {

        double precioTotalOrden=0.0;
        ConexBBDDHelper miConexion=new ConexBBDDHelper(getContext(),"ordenes",null,1);

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

                precioTotalOrden+=(miOrden.getPrecioProductoOrden()*miOrden.getCantidadOrden());
                miListOrden.add(miOrden);
            }

        }

        if(miListOrden.size()!=0){
            adapterCarrito miAdapterCarrito=new adapterCarrito(getContext(),miListOrden,getActivity());

            recyclerCarrito.setAdapter(miAdapterCarrito);

        }


        textViewTotal.setText("Total: " + precioTotalOrden);



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


    RecyclerView recyclerCarrito;
    TextView textViewTotal;

}
