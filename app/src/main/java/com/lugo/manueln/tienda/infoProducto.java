package com.lugo.manueln.tienda;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
public class infoProducto extends Fragment implements View.OnClickListener {
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
        bAgregar=vista.findViewById(R.id.buttonAgregar);
        editTextCantidad=vista.findViewById(R.id.editCantidad);

        bAgregar.setOnClickListener(this);

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

                miProducto=new producto();

                miProducto.setIdP(object.optInt("id"));
                miProducto.setNombreP(object.optString("nombre"));
                miProducto.setDescripcionP(object.optString("descripcion"));
                miProducto.setCategoriaP(object.optString("categoria"));
                miProducto.setPrecioP(object.optInt("precio"));
                miProducto.setRutaImagenP(object.optString("ruta"));

                txtvNombre.setText(miProducto.getNombreP());
                txtvCategoria.setText(miProducto.getCategoriaP());
                txtvPrecio.setText(miProducto.getPrecioP()+"$");

                cargarImagen(miProducto.getRutaImagenP());


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

    @Override
    public void onClick(View view) {

        agregarProductoCarrito();
    }

    private void agregarProductoCarrito() {

        ConexBBDDHelper miConex = new ConexBBDDHelper(getContext(), "ordenes", null, 1);

        SQLiteDatabase miBBDD = miConex.getWritableDatabase();

        orden miOrden=verificarOrdenExistente(miProducto.getIdP());

        if(!miOrden.isOrdenExistente()) {

            ContentValues valores = new ContentValues();

            valores.put(utilidadesBD.CAMPO_ID, miProducto.getIdP());
            valores.put(utilidadesBD.CAMPO_NOMBRE, miProducto.getNombreP());
            valores.put(utilidadesBD.CAMPO_PRECIO, miProducto.getPrecioP());
            valores.put(utilidadesBD.CAMPO_URL, miProducto.getRutaImagenP());
            valores.put(utilidadesBD.CAMPO_CANTIDAD, Integer.parseInt(editTextCantidad.getText().toString()));

            miBBDD.insert(utilidadesBD.TABLA_ORDENES, null, valores);

        }else {
            ContentValues valores=new ContentValues();

            double precioTotalNuevo=miOrden.getPrecioTotalOrden()+(miProducto.getPrecioP()*Integer.parseInt(editTextCantidad.getText().toString()));
            int cantidadTotalNueva=miOrden.getCantidadOrden() + (Integer.parseInt(editTextCantidad.getText().toString()));


            valores.put(utilidadesBD.CAMPO_TOTAL,precioTotalNuevo);
            valores.put(utilidadesBD.CAMPO_CANTIDAD,cantidadTotalNueva);

            int result=miBBDD.update(utilidadesBD.TABLA_ORDENES,valores,"id = ?" ,new String[]{""+miProducto.getIdP()});

        }

        miBBDD.close();
        miConex.close();

        Toast.makeText(getContext(), "Agregado", Toast.LENGTH_SHORT).show();




    }

    private orden verificarOrdenExistente(int id) {

        orden ordenEdicion=new orden();

        ordenEdicion.setOrdenExistente(true);

        ConexBBDDHelper miConex= new ConexBBDDHelper(getContext(),"ordenes",null,1);

        SQLiteDatabase db = miConex.getReadableDatabase();

        Cursor miCursor=db.rawQuery("SELECT * FROM " + utilidadesBD.TABLA_ORDENES + " WHERE " +utilidadesBD.CAMPO_ID +  "= ?",new String[]{""+id});


        if(miCursor.moveToNext()){
            //Toast.makeText(getContext(), "Se Encontro un Registro con el mismo id", Toast.LENGTH_SHORT).show();

            int cantidadTotalActual=miCursor.getInt(4);

            double precioTotalActual=miCursor.getDouble(5);

            ordenEdicion.setPrecioTotalOrden(precioTotalActual);
            ordenEdicion.setCantidadOrden(cantidadTotalActual);
            ordenEdicion.setOrdenExistente(true);
        }else {
            //Toast.makeText(getContext(), "No hay ningun Registro con esa id", Toast.LENGTH_SHORT).show();

            ordenEdicion.setOrdenExistente(false);
        }
        miConex.close();
        db.close();

        return ordenEdicion;

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
    EditText editTextCantidad;
    Button bAgregar;
    producto miProducto;
}
