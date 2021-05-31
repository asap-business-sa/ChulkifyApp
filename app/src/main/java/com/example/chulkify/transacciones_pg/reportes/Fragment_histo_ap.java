package com.example.chulkify.transacciones_pg.reportes;

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
import com.example.chulkify.R;
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_histo_ap  extends Fragment  implements Response.ErrorListener, Response.Listener<JSONObject> {

private RecyclerView rv;
private AportesAdapter adapter;
private List<Transacciones> listTransacciones;
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


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_histo_ap, container, false);
        listTransacciones = new ArrayList<>();

        rv = (RecyclerView) v.findViewById(R.id.rcw_transacciones);
        layoutSinSolicitudes = (LinearLayout)v.findViewById(R.id.layout_vacio_vicivility);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        adapter = new AportesAdapter(listTransacciones,getContext(),this);
        rv.setAdapter(adapter);

        preferences = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("cedula_usuario", null);
        request= Volley.newRequestQueue(getContext());
        buscar_usuario_bd =new AsyncHttpClient();
        cargarservice();

        verificar_solicitudes();

        return v;
        }
public void cargarservice(){
        String cog_comu = usuario.replace(" ", "%20");
        String url="http://www.marlonmym.tk/chulki/consulta_transaciones/c_aportes.php?ci_us="+cog_comu;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null, this,this);
        request.add(jsonObjectRequest);}

public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No hay aportes" , Toast.LENGTH_LONG).show();

        }

public void onResponse(JSONObject response)
        {
        Transacciones centrousu=null;

        JSONArray json=response.optJSONArray("transaccion");
        try {

        for (int i=0;i<json.length();i++){
        centrousu=new Transacciones();
        JSONObject jsonObject=null;
        jsonObject=json.getJSONObject(i);

        String tipo_tt=jsonObject.optString("tipo_tran");
        String val=jsonObject.optString("valor_tran");
        String fech=jsonObject.optString("fecha_tran");
        String hor=jsonObject.optString("hora");
        String tp="null";

        if(tipo_tt.equals("APORTE")){tp="+";}
        else if(tipo_tt.equals("RETIRO")){tp="-";}


        //Toast.makeText(getContext(), id_usuario, Toast.LENGTH_SHORT).show();
        agregarTarjetasDeSolicitud(tp,val,fech,hor);
        }


        } catch (JSONException e) {
        e.printStackTrace();
        Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
        " "+response, Toast.LENGTH_LONG).show();
        }

        }



public  void verificar_solicitudes(){
        if (listTransacciones.isEmpty()){
        layoutSinSolicitudes.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
        }
        else {
        layoutSinSolicitudes.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
        }
        }

public void agregarTarjetasDeSolicitud(String tipo, String valor, String fecha, String hora){

        Transacciones transacciones = new Transacciones();
        transacciones.setTipo_tran(tipo);
        transacciones.setValor_tran(valor);
        transacciones.setFecha_tran(fecha);
        transacciones.setHora_tran(hora);
        listTransacciones.add(0,transacciones);



        actualizarTarjetas();
        }
public void agregarTarjetasDeSolicitud(Transacciones transacciones){
        listTransacciones.add(0,transacciones);
        actualizarTarjetas();
        }


public void actualizarTarjetas(){
        adapter.notifyDataSetChanged();
        verificar_solicitudes();
        }

public void eliminarTarjeta(int id){
        //int id_t= Integer.p
        for(int i=0;i<listTransacciones.size();i++){
        if(listTransacciones.get(i).getId_transaccion()==id){
        listTransacciones.remove(i);
        actualizarTarjetas();
        }
        }
        }



        }