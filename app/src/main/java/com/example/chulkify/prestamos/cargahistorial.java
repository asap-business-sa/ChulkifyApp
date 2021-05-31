package com.example.chulkify.prestamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.cargar_2;
import com.example.chulkify.envio_solicitud_comu.solicitudes.Soli_notifi;
import com.example.chulkify.inicio;
import com.example.chulkify.login.Logear_usuario;
import com.example.chulkify.menus_usuarios.menu_comunidad;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class cargahistorial extends AppCompatActivity {
    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien, comu_clien2, comu_clien3;
    private String usuario;
    private String  nnn;
    private
    String cedula_us, nomb_comu,cg_gp;

    private String fechacrea, fechacadu;
    private int  dia, mes, anio, hora, minutos, segundos;
    private String  diaS, mesS, anioS,horaS, minutosS, segundosS, minutosa, usuario2;
    private int minutos_aux;
    private int m_cadu=0;
    private String mnt="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargahistorial);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        nomb_comu = preferences.getString("nombre_comu", null);
        cedula_us =preferences.getString("cedula_usuario", null);
        cg_gp =preferences.getString("comunidad", null);

        comu_clien = new AsyncHttpClient();
        comu_clien2 = new AsyncHttpClient();
        comu_clien3 = new AsyncHttpClient();
        datos_us();
        //enviar_solicitudes();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(cargahistorial.this, Activity_menu_prestamos.class));
                cargahistorial.this.finish();
            }
        },7000);

    }
    private void datos_us(){
        String cedula = cedula_us.replace(" ", "%20");
        String n_comu = nomb_comu.replace(" ", "_");
        String url_link = getString(R.string.link_consultar_prestamos);
        String url = url_link+"?n_comu="+n_comu+"&ci_us="+cedula;
        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargahistorial.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res_unido=jsonObj.getString("dato");
                            String [] res_sp = res_unido.split("/");
                            String res=res_sp[1];
                            Consulta_fondos();
                            Toast.makeText(cargahistorial.this, res, Toast.LENGTH_SHORT).show();
                                cconsulta_sol_pres();
                                SharedPreferences.Editor editor= preferences.edit();
                                editor.putString("estado_prestamos",res);
                                editor.apply();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargahistorial.this, "1Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cconsulta_sol_pres(){
        String cedula = cedula_us.replace(" ", "%20");
        String n_comu = nomb_comu.replace(" ", "_");
        String url_link2 = getString(R.string.link_estado_solicitud_prestamos);
        String url = url_link2+"?ci_us="+cedula+"&n_comu="+n_comu;
        comu_clien2.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargahistorial.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res_unido2=jsonObj.getString("dato");
                            String [] res_sp2 = res_unido2.split("/");
                            String res2=res_sp2[1];
                           Toast.makeText(cargahistorial.this, res2, Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor= preferences.edit();
                                editor.putString("estado_soli_pres",res2);
                                editor.apply();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargahistorial.this, "2Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Consulta_fondos(){
        String cedula = cedula_us.replace(" ", "%20");
        String codigo_gp = cg_gp.replace(" ", "%20");
        String n_comu = nomb_comu.replace(" ", "_");
        String url_link2 = getString(R.string.link_consulta_fondos_us_comu);
        String url = url_link2+"?ci_us="+cedula+"&cg_gp="+codigo_gp+"&n_comu="+n_comu;
        comu_clien3.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargahistorial.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res_unido3=jsonObj.getString("dato");
                            String [] res_sp3 = res_unido3.split("%%");

                            Toast.makeText(cargahistorial.this, "$"+res_sp3[0]+"----$"+res_sp3[1], Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor= preferences.edit();
                            editor.putString("fondos_usuario",res_sp3[0]);
                            editor.putString("fondos_comunidad",res_sp3[1]);
                            editor.apply();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargahistorial.this, "2Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }




}