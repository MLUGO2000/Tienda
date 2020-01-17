package com.lugo.manueln.tienda;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infoProducto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infoProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoProducto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idProducto";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParamId;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public infoProducto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static infoProducto newInstance(String param1, String param2) {
        infoProducto fragment = new infoProducto();
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
            mParamId = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_info_producto, container, false);

        colaRequest=Volley.newRequestQueue(getContext());

        imagenProducto=vista.findViewById(R.id.imageProductoInfo);
        txtvNombre=vista.findViewById(R.id.txtNombreInfo);
        txtvCategoria=vista.findViewById(R.id.txtCategoriaInfo);
        txtvPrecio=vista.findViewById(R.id.txtPrecioInfo);

        int id=mParamId;

        cargarDatosProducto(id);
        return vista;
    }

    private void cargarDatosProducto(int id) {

        String ip=getString(R.string.ip);
        String url=ip + "/WebTienda/wsJSONConsultarProducto.php?idProducto=" +id;

        JsonObjectRequest objectProducto=new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray=response.optJSONArray("producto");

                JSONObject object=jsonArray.optJSONObject(0);

                txtvNombre.setText(object.optString("nombre"));
                txtvCategoria.setText(object.optString("categoria"));
                txtvPrecio.setText(object.optInt("precio")+"$");

                cargarImagen(object.optString("ruta"));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        colaRequest.add(objectProducto);
    }

    private void cargarImagen(String ruta) {

        String ip=getString(R.string.ip);
        String urlImagen=ip+ "/WebTienda/" + ruta;

        ImageRequest image=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                imagenProducto.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        colaRequest.add(image);

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


    RequestQueue colaRequest;
    ImageView imagenProducto;
    TextView txtvNombre,txtvCategoria,txtvPrecio;
}
