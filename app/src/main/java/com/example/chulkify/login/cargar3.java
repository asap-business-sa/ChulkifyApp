package com.example.chulkify.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.espera_soli;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class cargar3 extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar3);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("comunidad", null);
        comu_clien = new AsyncHttpClient();
        cargar_datos();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(cargar3.this, espera_soli.class);
                startActivity(intent);
                //cargar3.this.finish();
            }
        },07000);
    }
    private void cargar_datos(){
        Toast.makeText(cargar3.this, "entro", Toast.LENGTH_SHORT).show();

        String cog_comu = usuario.replace(" ", "%20");
        String l_c_comu = getString(R.string.link_consultar_comunidad);
        String url = l_c_comu+"?codigo_comu="+cog_comu;
        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar3.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("codigo_comu_espera", jsonObj.getString("codigo_comu"));
                            editor.putString("nombre_comu", jsonObj.getString("nombre_comu"));
                            editor.apply();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar3.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }
}