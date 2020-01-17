package com.lugo.manueln.tienda;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link principal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link principal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class principal extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    JsonObjectRequest jsonObjectRequestUrl;
    RequestQueue requestQueue;
    private List<producto> listaProductos;

    public principal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment principal.
     */
    // TODO: Rename and change types and number of parameters
    public static principal newInstance(String param1, String param2) {
        principal fragment = new principal();
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
        View vista= inflater.inflate(R.layout.fragment_principal, container, false);

        listaProductos=new ArrayList<>();

        requestQueue=Volley.newRequestQueue(getContext());


        miRecyclerBebidas=vista.findViewById(R.id.recyclerBebidas);
        miRecyclerFerre=vista.findViewById(R.id.recyclerFerreteria);
        miRecyclerAli=vista.findViewById(R.id.recyclerAlimentos);
        miRecyclerDulces=vista.findViewById(R.id.recyclerDulces);
        miRecyclerLicores=vista.findViewById(R.id.recyclerLicores);

        txtBebidas=vista.findViewById(R.id.txtBebidas);
        txtFerreteria=vista.findViewById(R.id.txtFerreteria);
        txtAlimentos=vista.findViewById(R.id.txtAlimentos);
        txtDulces=vista.findViewById(R.id.txtDulces);
        txtLicores=vista.findViewById(R.id.txtLicores);



        List<producto> miLista=new ArrayList<>();


        LinearLayoutManager miManagerBebidas=new LinearLayoutManager(getContext());
        miManagerBebidas.setOrientation(LinearLayoutManager.HORIZONTAL);


        LinearLayoutManager miManagerFerre=new LinearLayoutManager(getContext());
        miManagerFerre.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager miManagerAli=new LinearLayoutManager(getContext());
        miManagerAli.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager miManagerDulces=new LinearLayoutManager(getContext());
        miManagerDulces.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager miManagerLicores=new LinearLayoutManager(getContext());
        miManagerLicores.setOrientation(LinearLayoutManager.HORIZONTAL);

        miRecyclerBebidas.setLayoutManager(miManagerBebidas);
        miRecyclerFerre.setLayoutManager(miManagerFerre);
        miRecyclerAli.setLayoutManager(miManagerAli);
        miRecyclerDulces.setLayoutManager(miManagerDulces);
        miRecyclerLicores.setLayoutManager(miManagerLicores);


        txtBebidas.setText("Bebidas");
        cargarWebServiceUrl("Bebidas");

        txtFerreteria.setText("Ferreteria");
        cargarWebServiceUrl("Ferreteria");

        txtAlimentos.setText("Alimentos");
        cargarWebServiceUrl("Alimentos");

        txtDulces.setText("Dulces");
        cargarWebServiceUrl("Dulces");

        txtLicores.setText("Licores");
        cargarWebServiceUrl("Licores");


















        return vista;
    }



    private void cargarWebServiceUrl(final String categoria) {

        String ip=getString(R.string.ip);
        String url=ip + "/WebTienda/wsJSONConsultarProductosCategoria.php?categoria=" + categoria;

        jsonObjectRequestUrl=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                List<producto> miListaProductos = new ArrayList<producto>();
                producto miProducto = null;

                JSONArray jsonArray = response.optJSONArray("producto");

                try {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        miProducto = new producto();

                        JSONObject object = jsonArray.optJSONObject(i);

                        miProducto.setIdP(object.optInt("id"));
                        miProducto.setNombreP(object.optString("nombre"));
                        miProducto.setPrecioP(object.optInt("precio"));
                        miProducto.setRutaImagenP(object.optString("ruta"));

                        miListaProductos.add(miProducto);

                    }


                    switch (categoria) {
                        case "Bebidas":

                            adapterProducto miAdapterBebidas = new adapterProducto(getContext(), miListaProductos, getActivity());
                            miRecyclerBebidas.setAdapter(miAdapterBebidas);

                            break;

                        case "Alimentos":

                            adapterProducto miAdapterAlimentos = new adapterProducto(getContext(), miListaProductos, getActivity());
                            miRecyclerAli.setAdapter(miAdapterAlimentos);

                            break;

                        case "Ferreteria":

                            adapterProducto miAdapterFerreteria = new adapterProducto(getContext(), miListaProductos, getActivity());
                            miRecyclerFerre.setAdapter(miAdapterFerreteria);
                            break;

                        case "Dulces":

                            adapterProducto miAdapterDulces = new adapterProducto(getContext(), miListaProductos, getActivity());
                            miRecyclerDulces.setAdapter(miAdapterDulces);
                            break;

                        case "Licores":

                            adapterProducto miAdapterLicores = new adapterProducto(getContext(), miListaProductos, getActivity());
                            miRecyclerLicores.setAdapter(miAdapterLicores);
                            break;

                    }


                } catch (Exception ejson) {

                    ejson.printStackTrace();
                    Toast.makeText(getContext(), "No se ha podido establecer conexion con el servidor" +
                            " " + response, Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se puede Conectar" + error.toString(),Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ",error.toString());
            }
        });


        requestQueue.add(jsonObjectRequestUrl);


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

    RecyclerView miRecyclerBebidas,miRecyclerFerre,miRecyclerAli,miRecyclerDulces,miRecyclerLicores;

    TextView txtBebidas,txtFerreteria,txtAlimentos,txtDulces,txtLicores;
}
