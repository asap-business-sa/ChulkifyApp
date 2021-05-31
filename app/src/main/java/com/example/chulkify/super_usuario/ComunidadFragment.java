package com.example.chulkify.super_usuario;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.cargar_2;
import com.example.chulkify.envio_solicitud_comu.solicitudes.Solicitudes;
import com.example.chulkify.envio_solicitud_comu.solicitudes.SolicitudesAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.content.Context.MODE_PRIVATE;

public class ComunidadFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    private RecyclerView rv;
    private Adapter_listar_comu adapter;
    private List<Comunidades> listComunidades;
    private LinearLayout layoutSinSolicitudes;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    ProgressDialog dialog;

    public SharedPreferences preferences;
    private String n_usuario="null";
    private String usuario, usu_soli;
    private AsyncHttpClient buscar_usuario_bd;



    private String fechacrea, fechacadu;
    private int  dia, mes, anio, hora, minutos, segundos;
    private String  diaS, mesS, anioS,horaS, minutosS, segundosS, minutosa, usuario2;
    private int minutos_aux;
    String n_comu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_comunidad, container, false);
        listComunidades = new ArrayList<>();

        rv = (RecyclerView) v.findViewById(R.id.rcw_solicitudes);
        layoutSinSolicitudes = (LinearLayout)v.findViewById(R.id.layout_vacio_vicivility);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        adapter = new Adapter_listar_comu(listComunidades,getContext(),this);
        rv.setAdapter(adapter);

        preferences = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        //usuario = preferences.getString("cedula_usuario", null);
        ///n_comu = preferences.getString("nombre_comu", null);
        request= Volley.newRequestQueue(getContext());
        buscar_usuario_bd =new AsyncHttpClient();
        cargarservice();

        verificar_solicitudes();

        return v;
    }

    public void cargarservice(){
        cargar_solicitudes();
    }

    public  void verificar_solicitudes(){
        if (listComunidades.isEmpty()){
            layoutSinSolicitudes.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }
        else {
            layoutSinSolicitudes.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }
    public void cargar_solicitudes(){

        AsyncHttpClient cargar_soli  = new AsyncHttpClient();



        String l_crg_soli =getString(R.string.link_consultar_grupo);
        String url=l_crg_soli;
        cargar_soli.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(getContext(), "Error...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res=jsonObj.getString("dato");

                            String[] parts = res.split("//");

                            for (int i = 1; i < parts.length; i++) {
                                String res2=parts[i];
                                String[] rpt2 = res2.split("%");

                                Usuarios centrousu=new Usuarios();

                                int  id_us=Integer.parseInt(rpt2[0]);

                                String n_comu=rpt2[1];
                                String cdg_comu=rpt2[2];
                                String ciudad_comu= rpt2[3];


                                agregarTarjetasDeSolicitud(id_us, n_comu, cdg_comu, ciudad_comu);

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();


            }


        });


    }



    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No hay solicitudes" , Toast.LENGTH_LONG).show();

    }

    public void onResponse(JSONObject response) {}

    public void agregarTarjetasDeSolicitud(int id_us, String n_comu, String cdg_comu, String ciudad_comu){

        Comunidades comunidades = new Comunidades();
        comunidades.setId_comu(id_us);
        comunidades.setNombre_comu(n_comu);
        comunidades.setCodigo_comu(cdg_comu);
        comunidades.setCiudad(ciudad_comu);

         listComunidades.add(0,comunidades);
        actualizarTarjetas();
    }

    public void agregarTarjetasDeSolicitud(Comunidades comunidades){
        listComunidades.add(0,comunidades);
        actualizarTarjetas();
    }

    public void actualizarTarjetas(){
        adapter.notifyDataSetChanged();
        verificar_solicitudes();
    }

   /*
    public void aceptarSolicitud(final int identificador, final String usuario_soli){

        Manejo_fechas nf = new Manejo_fechas();
        fechacrea = nf.fechaYhora_actual();

        AsyncHttpClient aceptar  = new AsyncHttpClient();
        final int ident=identificador;
        String nmb_comu = n_comu.replace(" ", "_");
        String idt=String.valueOf(ident);
        String l_acep_soli=getString(R.string.link_aceptar_solicitudes);
        String url = l_acep_soli+"?id="+ident+"&fe="+fechacrea+"&n_comu="+nmb_comu;
        aceptar.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(getContext(), "Error...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String resp = jsonObj.getString("dato");
                            if (resp.equals("en_espera")){
                                Toast.makeText(getContext(), "Has aceptado la solicitud del usuario "+usuario_soli+", el usuario aun tiene solicitudes pendientes", Toast.LENGTH_SHORT).show();
                            }
                            else if (resp.equals("aceptado")){
                                Toast.makeText(getContext(), "Has aceptado la solicitud del usuario "+usuario_soli+", el usuario ya es integraante de tu comunidad...!", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getContext(), "Error...!", Toast.LENGTH_SHORT).show();

                            }
                            eliminarTarjeta(ident);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cancelarSolicitud(final int identificador, final String usuario_soli){
        AsyncHttpClient rechazar  = new AsyncHttpClient();
        final int ident=identificador;
        String nmb_comu = n_comu.replace(" ", "_");
        String idt=String.valueOf(ident);
        String l_recha_soli=getString(R.string.link_rechazar_solicitud);
        String url = l_recha_soli+"?id="+ident+"&n_comu="+nmb_comu;
        rechazar.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(getContext(), "Error...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            String resp = jsonObj.getString("dato");
                            if (resp.equals("rechazado")){
                                Toast.makeText(getContext(), "Has rechazado la solicitud del usuario "+usuario_soli+" de tu comunidad", Toast.LENGTH_SHORT).show();
                            }
                            else if (resp.equals("error")){
                                Toast.makeText(getContext(), "Error...!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Error...!", Toast.LENGTH_SHORT).show();
                            }
                            eliminarTarjeta(ident);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } } } }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }}); }

    */

}