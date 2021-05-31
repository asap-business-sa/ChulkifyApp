package com.example.chulkify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chulkify.login.Login;
import com.example.chulkify.login.cargar3;
import com.example.chulkify.login.cargar_admin_Activity;
import com.example.chulkify.login.cargar_1;
import com.example.chulkify.login.menu_inicio;
import com.example.chulkify.menus_usuarios.menu_no_comunidad;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class inicio extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien,usuario_clien;

    private AsyncHttpClient buscar_version;
    private  String  resp_2;

    private String usuario;
    private int m_cadu=0;
    private String mnt="0";
    ProgressBar pb1;

    //variables para clave y usuario
    private String us, pw;
    private String codigo1;
    String cdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("cedula_usuario", null);

        comu_clien = new AsyncHttpClient();

        SharedPreferences.Editor editor=preferences.edit();

        editor.putString("version", getString(R.string.version));
        editor.putString("taza", getString(R.string.taza_transaccion));
        editor.putString("maximo", getString(R.string.maximo));
        editor.apply();
        pb1=(ProgressBar)findViewById(R.id.pgbar_1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                verificar_sesion();
                //inicio.this.finish();
            }
        },012000);

    }





    private void verificar_sesion(){
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        if ((preferences.getString("nombre_usuario", null) != null)&&(preferences.getString("pass", null) != null)){
            if((preferences.getInt("anio_cadu", 0) != 0)&&(preferences.getInt("mes_cadu", 0) != 0)&&(preferences.getInt("dia_cadu", 0) != 0)){
                cdd="CADUCADO";
                Manejo_fechas mf = new Manejo_fechas();
                int aaa=mf.anio_actual();
                int mmm=mf.mes_actual();
                int ddd=mf.dia_actual();
                int hhh=mf.hora_actual();
                int mnt=mf.minuto_actual();
                int sss=mf.segundo_actual();
                preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
                int aux_anio = preferences.getInt("anio_cadu", 0);
                int aux_mes = preferences.getInt("mes_cadu", 0);
                int aux_dia = preferences.getInt("dia_cadu", 0);
                int aux_hora = preferences.getInt("hora_cadu", 0);
                int aux_mnt = preferences.getInt("minuto_cadu", 0);
                if (aaa >= aux_anio){
                    if (mmm >= aux_mes){
                        if (ddd >= aux_dia){
                            if (hhh >= aux_hora){
                                if (mnt >= aux_mnt){
                                    preferences.edit().clear().apply();
                                    cdd="CADUCADO";
                                }
                                else{
                                    cdd="NO_CADUCADO";
                                }
                            }
                            else{
                                cdd="NO_CADUCADO";
                            }
                        }
                        else{
                            cdd="NO_CADUCADO";
                        }
                    }
                    else{
                        cdd="NO_CADUCADO";
                    }
                }

                else{
                    cdd="NO_CADUCADO";
                }
                if (cdd.equals("CADUCADO")){
                    preferences.edit().clear().apply();
                    Toast.makeText(inicio.this, "La session ah caducado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(inicio.this, menu_inicio.class));
                }else{
                    us=preferences.getString("nombre_usuario", null);
                    pw=preferences.getString("pass", null);
                    usuario_clien = new AsyncHttpClient();
                    ini_seccion();
                }
            }
            else{
                us=preferences.getString("nombre_usuario", null);
                pw=preferences.getString("pass", null);
                usuario_clien = new AsyncHttpClient();
                ini_seccion();
            }
        }
        else {
            startActivity(new Intent(inicio.this, menu_inicio.class));
           }
    }
    private void ini_seccion() {
        String usuario = us.replace(" ", "%20");
        String password =   pw.replace(" ", "%20");
        String l_lg= getString(R.string.link_login);
        String url = l_lg+"?usuario_us="+usuario+"&contrasena_us="+password;
        usuario_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(inicio.this, "Error De Usuario y/o Contraseña!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(inicio.this, menu_inicio.class));
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            String n_tp= jsonObj.getString("tipo_us");
                            Manejo_fechas cd = new Manejo_fechas();
                            String cadd=cd.caducidad();
                            String[] pt = cadd.split("/");
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("fecha_actual",cd.fecha_actual() );
                            editor.putInt("anio_cadu", Integer.parseInt(pt[2]));
                            editor.putInt("mes_cadu", Integer.parseInt(pt[1]));
                            editor.putInt("dia_cadu", Integer.parseInt(pt[0]));
                            editor.putInt("hora_cadu", Integer.parseInt(pt[3]));
                            editor.putInt("minuto_cadu", Integer.parseInt(pt[4]));
                            editor.putInt("segundo_cadu", Integer.parseInt(pt[5]));
                            editor.putString("cedula_usuario", jsonObj.getString("cedula_us"));
                            editor.putString("nombre_usuario", jsonObj.getString("usuario_us"));
                            editor.putInt("id", jsonObj.getInt("id_us"));
                            editor.putString("comunidad", jsonObj.getString("grupo_us"));
                            codigo1 = jsonObj.getString("grupo_us");
                            editor.putString("fondos_us", jsonObj.getString("fondos_us"));
                            editor.putString("tipo", jsonObj.getString("tipo_us"));
                            editor.putString("fecha_ini", jsonObj.getString("fecha_inicio_us"));
                            editor.putString("pass", jsonObj.getString("contrasena_us"));
                            editor.putString("fecha_union_grupo", jsonObj.getString("fecha_union_comu_us"));
                            editor.putString("estado_usuario", jsonObj.getString("estado_gru_us"));
                            editor.putString("linea_ap",jsonObj.getString("histo_aport_us"));
                            editor.apply();
                            Intent intent = null;
                            if(n_tp.equals("NO_COMU")){
                                intent= new Intent(inicio.this, menu_no_comunidad.class);
                            }else if(n_tp.equals("US_COMU")){
                                intent= new Intent(inicio.this, cargar_1.class);
                            }else if(n_tp.equals("ADMIN_COMU")){
                                intent= new Intent(inicio.this, cargar_1.class);
                            }else if(n_tp.equals("US_ESPERA")){
                                intent= new Intent(inicio.this, cargar3.class);
                            }else if(n_tp.equals("SUPER_US")){
                                intent= new Intent(inicio.this, cargar_admin_Activity.class);
                            }



                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(inicio.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(inicio.this, menu_inicio.class));
            }


        });



    }

}