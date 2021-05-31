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

public class cargar_admin_Activity extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien;
    private String usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_admin);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("comunidad", null);
        comu_clien = new AsyncHttpClient();
        //cargar_datos();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(cargar_admin_Activity.this, menu_super_us_Activity.class);
                startActivity(intent);
                //cargar3.this.finish();
            }
        },07000);
    }

}