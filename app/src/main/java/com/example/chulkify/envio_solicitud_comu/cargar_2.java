package com.example.chulkify.envio_solicitud_comu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.inicio;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class cargar_2 extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien;
    private String usuario;
    private String res, nnn;


    private String fechacrea, fechacadu;
    private int  dia, mes, anio, hora, minutos, segundos;
    private String  diaS, mesS, anioS,horaS, minutosS, segundosS, minutosa, usuario2;
    private int minutos_aux;
    private int m_cadu=0;
    private String mnt="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_2);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("codigo_comu_solicitud", null);
        usuario2 =preferences.getString("cedula_usuario", null);
        Manejo_fechas fc = new Manejo_fechas();
        fechacrea=fc.fechaYhora_actual();
        fechacadu=fc.caducidad();
        comu_clien = new AsyncHttpClient();
        //enviar_solicitudes();
        datos_us();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(cargar_2.this, inicio.class);
                startActivity(intent);


            }
        },25000);

    }


    private void datos_us(){
        String cog_comu = usuario.replace(" ", "%20");
        String url_link = getString(R.string.link_buscar_usuarios);
        String url = url_link+"?codigo_comu="+cog_comu;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();
        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar_2.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            res=jsonObj.getString("dato");
                            //Toast.makeText(cargar_2.this, "entro:"+res , Toast.LENGTH_SHORT).show();
                            String[] parts = res.split("/");
                            if (parts[1] == "dato_null"){
                                Toast.makeText(cargar_2.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                                for (int i = 1; i < parts.length; i++) {
                                    nnn = parts[i];
                                    String[] parts2 = nnn.split("%%");
                                    String usuario_soli=parts2[1];
                                    String ci_soli=parts2[0];
                                    enviar_solicitudes(ci_soli);
                                    Toast.makeText(cargar_2.this, "Solicitud enviada a:" + usuario_soli, Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(getApplicationContext(), "las solicitudes fueron enviadas con exito ...!!!", Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar_2.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void enviar_solicitudes(String dato){

        String ccc= "%22"+usuario+"%22";
        String cog_comu = ccc.replace(" ", "%20");
        String ci_emisor = usuario2.replace(" ", "%20");
        String cg_cm = usuario.replace(" ", "%20");
        String ci_receptor = dato.replace(" ", "%20");
        String fecha_crea = fechacrea.replace(" ", "%20");
        String fecha_cadu = fechacadu.replace(" ", "%20");

        String url_link2 = getString(R.string.link_generar_solicitud_ing_grupo);
        String url = url_link2+"?ci_emisor="+ci_emisor+"&cg_cm="+cg_cm+"&ci_receptor="+ci_receptor+"&fecha_crea="+fecha_crea+"&fecha_cadu="+fecha_cadu;

        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar_2.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar_2.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }
}