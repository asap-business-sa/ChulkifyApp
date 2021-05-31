package com.example.chulkify.menus_usuarios;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chulkify.R;
import com.example.chulkify.inicio;
import com.example.chulkify.nueva_comunidad.nueva_comunidad;
import com.example.chulkify.envio_solicitud_comu.unir_comunidad;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class menu_no_comunidad extends AppCompatActivity {


    private static final int PERMISSION_STORAGE_CODE = 1000;
    private SharedPreferences preferences;
    private AsyncHttpClient buscar_url;

    private TextView tv1;
    private ImageButton new_comu, uni_comu;
    private String version, url="null", resp,res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=findViewById(R.id.txt_mostrar);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        version = preferences.getString("version", null);
        String usuario_us = preferences.getString("nombre_usuario", null);
        buscar_url =new AsyncHttpClient();
        String aux =(buscar_url(version));
        if (usuario_us != null){
            tv1.setText("Bienvenido "+usuario_us);
        }

        new_comu = (ImageButton) findViewById(R.id.btn_new_comu);
        uni_comu = (ImageButton) findViewById(R.id.btn_uni_comu);
        new_comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu_no_comunidad.this, nueva_comunidad.class));

            }
        });
        uni_comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu_no_comunidad.this, unir_comunidad.class));

            }
        });



    }

    // menu superoir derecho
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_of, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_iten_c_sesion){
            preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
            preferences.edit().clear().apply();
            startActivity(new Intent(menu_no_comunidad.this, inicio.class));
            //Intent intent= new Intent(menu_comunidad.this, Login.class);
        }
        else if (id == R.id.menu_actualizacion){
            String msj1=("ult_vs");
            String msj2=("error");

            url =(buscar_url(version));
            //buscar_url();
            //Toast.makeText(espera_soli.this, url, Toast.LENGTH_SHORT).show();

            if(url.equals("ult_vs")){
                Toast.makeText(menu_no_comunidad.this, "la version "+version+" es la mas reciente", Toast.LENGTH_SHORT).show();
            }else if (url.equals("error")){
                Toast.makeText(menu_no_comunidad.this, "Hay un error desconocido", Toast.LENGTH_SHORT).show();
            }else{
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    }else {
                        StartDownloading();
                    }
                }else {StartDownloading();}



            }





        }
        return super.onOptionsItemSelected(item);
    }
    private void StartDownloading() {
        url =(buscar_url(version));
        String url_d= url.trim();

        File file = new File(getExternalFilesDir(null), "chulkify.apk");
        Toast.makeText(menu_no_comunidad.this, "Se inicio la descarga", Toast.LENGTH_SHORT).show();

        DownloadManager.Request request =null;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            request = new DownloadManager.Request(Uri.parse(url_d));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setTitle("Download");
            request.setDescription("Downloading apk...");
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationUri(Uri.fromFile(file));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "chulkify.apk");
            request.setRequiresCharging(false);
            request.setAllowedOverMetered(true);
            request.setAllowedOverRoaming(true);
        }else{
            request = new DownloadManager.Request(Uri.parse(url_d));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setTitle("Download");
            request.setDescription("Downloading apk...");
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationUri(Uri.fromFile(file));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "chulkyfy.apk");
            request.setAllowedOverRoaming(true);

        }
        //

        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

        manager.enqueue(request);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    StartDownloading();
                }
                else {
                    Toast.makeText(menu_no_comunidad.this, "Permiso denegado...!!!", Toast.LENGTH_SHORT).show();

                }
            }

        }
    }
    public String buscar_url(String dato) {


        String ci_usuario = dato.replace(" ", "%20");
        String l_lvs= getString(R.string.link_actualizar);
        String url = l_lvs+"?url_ac="+ci_usuario;
        //Toast.makeText(Login.this, url, Toast.LENGTH_SHORT).show();

        buscar_url.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(menu_no_comunidad.this, "Error al  buscar actualizaciones", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            resp = jsonObj.getString("dato");
                            //Toast.makeText(espera_soli.this, resp, Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(menu_no_comunidad.this, "Error Desconocido. Intentelo De Nuevo!!" + responseBody, Toast.LENGTH_SHORT).show();
            }


        });
        return resp;
    }



}