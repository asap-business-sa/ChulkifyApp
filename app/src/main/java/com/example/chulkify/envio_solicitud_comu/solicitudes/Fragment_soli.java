package com.example.chulkify.envio_solicitud_comu.solicitudes;

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

public class Fragment_soli extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    private RecyclerView rv;
    private SolicitudesAdapter adapter;
    private List<Solicitudes> listSolicitudes;
    private LinearLayout layoutSinSolicitudes;
    //private EventBus bus = EventBus.getDefault();

    //ArrayList<Solicitudes> listasolicitudes;
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
       View v=inflater.inflate(R.layout.fragment_soli, container, false);
       listSolicitudes = new ArrayList<>();

       rv = (RecyclerView) v.findViewById(R.id.rcw_solicitudes);
       layoutSinSolicitudes = (LinearLayout)v.findViewById(R.id.layout_vacio_vicivility);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        adapter = new SolicitudesAdapter(listSolicitudes,getContext(),this);
        rv.setAdapter(adapter);

        preferences = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("cedula_usuario", null);
        n_comu = preferences.getString("nombre_comu", null);
        request= Volley.newRequestQueue(getContext());
        buscar_usuario_bd =new AsyncHttpClient();


        cargarservice();

        verificar_solicitudes();

       return v;
    }
    public void cargarservice(){
        cargar_solicitudes();
    }

    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No hay solicitudes" , Toast.LENGTH_LONG).show();

    }

    public void onResponse(JSONObject response) {}

    public void cargar_solicitudes(){

        AsyncHttpClient cargar_soli  = new AsyncHttpClient();
        String cog_comu = usuario.replace(" ", "%20");
        String nmb_comu = n_comu.replace(" ", "_");

        String l_crg_soli =getString(R.string.link_captura_solicitud);
        String url=l_crg_soli+"?ci_us="+cog_comu+"&n_comu="+nmb_comu;
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

                                Solicitudes centrousu=new Solicitudes();

                                int  id_us=Integer.parseInt(rpt2[0]);


                                String[] pt2 = rpt2[1].split("/");
                                //Toast.makeText(getContext(), "soli==>   "+pt2[0], Toast.LENGTH_LONG).show();
                                String fecha=pt2[0]+"/"+pt2[1]+"/"+pt2[2]+"   "+pt2[3]+":"+pt2[4]+":"+pt2[5];
                                String dt=rpt2[2];
                                String aux="("+dt+")";
                                String nnn=rpt2[3];
                                String aaa=rpt2[4];

                                String t_solicitud= rpt2[5];
                                String tipo_solicitud= null;
                                String valor_prestar= rpt2[6];
                                String plazo_prestar= rpt2[7];
                                String msj1=getString(R.string.v_prestamo);
                                String msj2=getString(R.string.d_prestamo);


                                if (t_solicitud.equals("SOLICITUD_UNION")){
                                    tipo_solicitud= "UNION A GRUPO";
                                }
                                else if (t_solicitud.equals("SOLICITUD_PRESTAMO")){
                                    tipo_solicitud= "PRESTAMO";
                                }
                                else {
                                    tipo_solicitud= "ERROR";
                                }

                                String mensaje1="";
                                String mensaje2="";

                                if (!valor_prestar.equals("NULO")){
                                    mensaje1="$ "+valor_prestar;
                                }
                                if (!plazo_prestar.equals("NULO")){
                                    mensaje2=plazo_prestar+"  MESES";
                                }

                                //Toast.makeText(getContext(), "soli==>   "+ nnn+"soli==> "+aaa+"soli==> "+aux+"soli==> "+fecha+"soli==> ", Toast.LENGTH_LONG).show();

                                //Toast.makeText(getContext(), id_usuario, Toast.LENGTH_SHORT).show();
                                agregarTarjetasDeSolicitud(nnn,aaa,aux,fecha,id_us,tipo_solicitud,mensaje1,mensaje2,msj1,msj2);

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

    public  void verificar_solicitudes(){
        if (listSolicitudes.isEmpty()){
            layoutSinSolicitudes.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }
        else {
            layoutSinSolicitudes.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }

    public void agregarTarjetasDeSolicitud(String nombre_usu, String apellidi_usu, String usuario_usu, String fecha_crea,int id_us, String t_s, String v_p, String p_p, String msj1, String msj2){

        Solicitudes solicitudes = new Solicitudes();
        solicitudes.setNombre_us(nombre_usu);
        solicitudes.setApellido_us(apellidi_usu);
        solicitudes.setUsuario_us(usuario_usu);
        solicitudes.setFecha_crea_notif(fecha_crea);
        solicitudes.setId_notif(id_us);
        solicitudes.setTipo_s(t_s);
        solicitudes.setValor_p(v_p);
        solicitudes.setPlazo_p(p_p);
        solicitudes.setMsj1(msj1);
        solicitudes.setMsj2(msj2);
        listSolicitudes.add(0,solicitudes);
        actualizarTarjetas();
    }

    public void agregarTarjetasDeSolicitud(Solicitudes solicitudes){
        listSolicitudes.add(0,solicitudes);
        actualizarTarjetas();
    }

    public void actualizarTarjetas(){
        adapter.notifyDataSetChanged();
        verificar_solicitudes();
    }

    public void eliminarTarjeta(int id){
        //int id_t= Integer.p
        for(int i=0;i<listSolicitudes.size();i++){
            if(listSolicitudes.get(i).getId_notif()==id){
                listSolicitudes.remove(i);
                actualizarTarjetas();
            }
        }
    }

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

}