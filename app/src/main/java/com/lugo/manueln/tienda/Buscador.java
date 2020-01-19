package com.lugo.manueln.tienda;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.tienda.adapters.adapterProductoTodos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Buscador.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Buscador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buscador extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Buscador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buscador.
     */
    // TODO: Rename and change types and number of parameters
    public static Buscador newInstance(String param1, String param2) {
        Buscador fragment = new Buscador();
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
        View vista= inflater.inflate(R.layout.fragment_buscador, container, false);

        colaPeticiones=Volley.newRequestQueue(getContext());

        editBuscar=vista.findViewById(R.id.editBuscador);

        recyclerBuscador=vista.findViewById(R.id.buscarRecycler);

        LinearLayoutManager miManager=new LinearLayoutManager(getContext());
        miManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerBuscador.setLayoutManager(miManager);

        cargarDatosProductos();

        editBuscar=vista.findViewById(R.id.editBuscador);




        return vista;
    }

    private void cargarDatosProductos() {

        String ip=getString(R.string.ip);

        String url=ip + "/WebTienda/wsJSONConsultarProductosTodos.php";

        objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<producto> miListaProductos = new ArrayList<producto>();
                producto miProducto = null;

                JSONArray jsonArray = response.optJSONArray("producto");

                try {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        miProducto = new producto();

                        JSONObject object = jsonArray.optJSONObject(i);

                        miProducto.setIdP(object.optInt("id"));
                        miProducto.setNombreP(object.optString("nombre"));
                        miProducto.setCategoriaP(object.optString("categoria"));
                        miProducto.setPrecioP(object.optInt("precio"));
                        miProducto.setRutaImagenP(object.optString("ruta"));

                        miListaProductos.add(miProducto);

                    }

                    final adapterProductoTodos miAdapter=new adapterProductoTodos(getContext(),miListaProductos,getActivity());

                    recyclerBuscador.setAdapter(miAdapter);

                    editBuscar.addTextChangedListener(new TextWatcher() {
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

                } catch (Exception ejson) {

                    ejson.printStackTrace();
                    Toast.makeText(getContext(), "No se ha podido establecer conexion con el servidor" +
                            " " + response, Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error Web","Error Volley de Tipo :"  + error);
            }
        });


        colaPeticiones.add(objectRequest);
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


    private EditText editBuscar;
    private RecyclerView recyclerBuscador;
    private JsonObjectRequest objectRequest;
    private RequestQueue colaPeticiones;
}
