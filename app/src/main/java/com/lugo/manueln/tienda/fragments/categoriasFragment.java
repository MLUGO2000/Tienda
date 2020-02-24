package com.lugo.manueln.tienda.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.tienda.R;
import com.lugo.manueln.tienda.adapters.adapterCategorias;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link categoriasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link categoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class categoriasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public categoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment categoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static categoriasFragment newInstance(String param1, String param2) {
        categoriasFragment fragment = new categoriasFragment();
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
        View vista= inflater.inflate(R.layout.fragment_categorias, container, false);

        requestQueue=Volley.newRequestQueue(getContext());

        miRecylcerCategorias=vista.findViewById(R.id.recyclerCatNombres);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        miRecylcerCategorias.setLayoutManager(layoutManager);

        cargarNombresCategoria();



        return vista;
    }

    private void cargarNombresCategoria() {

        nombresCategorias=new ArrayList<>();

        String ip=getString(R.string.ip);

        String url=ip + "/WebTienda/wsJSONConsultarCategorias.php";


        objectRequest =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray miArrayObject=response.optJSONArray("categorias");

                for(int i=0;i<miArrayObject.length();i++){
                    JSONObject jsonObject=miArrayObject.optJSONObject(i);

                    nombresCategorias.add(jsonObject.optString("nombre"));

                }

                adapterCategorias miAdapteCat=new adapterCategorias(getContext(),nombresCategorias,getActivity());
                miRecylcerCategorias.setAdapter(miAdapteCat);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Error de Tipo" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(objectRequest);


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

    RecyclerView miRecylcerCategorias;
    ArrayList<String> nombresCategorias;
    JsonObjectRequest objectRequest;
    RequestQueue requestQueue;
}
