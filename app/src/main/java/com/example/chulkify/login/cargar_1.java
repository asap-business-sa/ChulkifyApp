package com.example.chulkify.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.menus_usuarios.menu_comunidad;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class cargar_1 extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien, consult_estd;
    private String usuario;
    String nombre_comu, c_comu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_1);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("comunidad", null);
        //nombre_comu = preferences.getString("nombre_comu", null);
       //c_comu = preferences.getString("grupo_us", null);

        comu_clien = new AsyncHttpClient();
        consult_estd= new AsyncHttpClient();
        cargar_datos();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(cargar_1.this, menu_comunidad.class);
                startActivity(intent);
                cargar_1.this.finish();
            }
        },07000);
    }

    private void cargar_datos(){
        String cog_comu = usuario.replace(" ", "%20");
        String l_c_comunidad=getString(R.string.link_consultar_comunidad);
        String url = l_c_comunidad+"?codigo_comu="+cog_comu;

        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar_1.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            SharedPreferences.Editor editor=preferences.edit();

                            String aux1="dt1"+jsonObj.getInt("id_comu")+"dt2"+jsonObj.getString("nombre_comu")+"dt3"+jsonObj.getString("codigo_comu")+"dt4"+ jsonObj.getInt("total_usuario_comu");
                            editor.putInt("id_comu", jsonObj.getInt("id_comu"));
                            editor.putString("nombre_comu", jsonObj.getString("nombre_comu"));
                            editor.putString("codigo_comu", jsonObj.getString("codigo_comu"));
                            editor.putInt("total_usuario_comu", jsonObj.getInt("total_usuario_comu"));
                            //Toast.makeText(cargar_1.this, "ENTRO 4545    -"+aux1, Toast.LENGTH_SHORT).show();

                           // actualizar_estado();

                            editor.apply();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar_1.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void actualizar_estado(){
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("comunidad", null);
        nombre_comu = preferences.getString("nombre_comu", null);
        c_comu = preferences.getString("grupo_us", null);


        Manejo_fechas mf=new Manejo_fechas();
        String f_ini=mf.extremos_ini();
        String f_fin=mf.extremos_fin();
        String cog_comu = usuario.replace(" ", "%20");
        String n_comu = nombre_comu.replace(" ", "_");
        String l_udt_estados=getString(R.string.link_udt_estados);
        String url = l_udt_estados+"?c_comu="+cog_comu+"&n_comu="+n_comu+"&f_ini="+f_ini+"&f_fin="+f_fin;
        Toast.makeText(cargar_1.this, url , Toast.LENGTH_SHORT).show();
/*
        consult_estd.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar_1.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            // si recibe una respuesta personalizada por liquidacion este es el punto en que se podria enviar notificaciones....
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar_1.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });

 */
    }
}