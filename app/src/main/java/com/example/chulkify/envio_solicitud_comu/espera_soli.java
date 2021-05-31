package com.example.chulkify.envio_solicitud_comu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.R;
import com.example.chulkify.inicio;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class espera_soli extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    private SharedPreferences preferences;
    private AsyncHttpClient buscar_soli, buscar_url, buscar_cadu, cancelar_htt;
    private Button cancelar;
    private TextView tv_nombre_comu, tv_aceptadas, tv_espera, tv_negadas, tv_fecha, tv_hora, tv_mensaje;
    private String nombre_comu, aceptadas, espera, negadas, usuario, resp,res, comunidad;
    private int n_aceptadas, n_espera, n_negadas;
    private String version, url="null";
    String n_comu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_soli);

        cancelar = (Button) findViewById(R.id.btn_env_soli_comu);
        tv_nombre_comu = (TextView) findViewById(R.id.tv_nombre_comu);
        tv_aceptadas = (TextView) findViewById(R.id.tv_n_i_comu);
        tv_espera = (TextView) findViewById(R.id.tv_ubi_comu);
        tv_negadas = (TextView) findViewById(R.id.tv_admin_comu);
        tv_fecha=(TextView) findViewById(R.id.fecha_cadu);
        tv_hora=(TextView) findViewById(R.id.hora_cadu);
        tv_mensaje=(TextView) findViewById(R.id.tv_mensaje);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("cedula_usuario", null);
        comunidad = preferences.getString("nombre_comu", null);
        n_comu=preferences.getString("nomb_comu_solicitud", null);
        version = preferences.getString("version", null);
        //Toast.makeText(espera_soli.this, usuario, Toast.LENGTH_SHORT).show();

        buscar_soli = new AsyncHttpClient();

        buscar_url =new AsyncHttpClient();
        buscar_cadu =new AsyncHttpClient();
        String aux =(buscar_url(version));
        tv_nombre_comu.setText("-");
        tv_aceptadas.setText("-");
        tv_espera.setText("-");
        tv_negadas.setText("-");
        //tv_fecha.setText("0000");
        //tv_hora.setText("1111");
        cargar_caducidad();
        cargar_datos();
        cargar_caducidad();
        //Toast.makeText(espera_soli.this, version, Toast.LENGTH_SHORT).show();
        //buscar_url();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cancelar_soli(usuario, n_comu);
            }
        });


    }

    private void cargar_datos(){
        String codigo_comunidad = usuario.replace(" ", "%20");
        String n_comunidad = n_comu.replace(" ", "_");
        String link_consult=getString(R.string.link_consultar_estd_soli_union);
        String url = link_consult+"?ci_us="+codigo_comunidad+"&n_comu="+n_comunidad;
        buscar_soli.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(espera_soli.this, "codigo no encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            res=jsonObj.getString("dato");
                            String[] parts = res.split("/");
                            if (parts[1] == "dato_null"){
                                Toast.makeText(espera_soli.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                                n_aceptadas=0;
                                n_espera=0;
                                n_negadas=0;
                                for (int i = 1; i < parts.length; i++) {
                                    String aux =parts[i];
                                    if (aux.equals("ESPERA")){n_espera++;}
                                    else if (aux.equals("NEGADA")){n_negadas++;
                                        tv_mensaje.setVisibility(View.VISIBLE);}
                                    else if (aux.equals("ACEPTADA")){n_aceptadas++;}
                                }
                            }
                            tv_nombre_comu.setText(comunidad);
                            tv_aceptadas.setText(String.valueOf(n_aceptadas));
                            tv_espera.setText(String.valueOf(n_espera));
                            tv_negadas.setText(String.valueOf(n_negadas));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(espera_soli.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }


        });





    }


    private void cancelar_soli(String usu, String comu){
        AsyncHttpClient rechazar  = new AsyncHttpClient();
        String n_comunidad = comu.replace(" ", "_");
        String link_cancelar=getString(R.string.link_btn_cancelar_soli);
        String url = link_cancelar+"?id="+usu+"&n_comu="+n_comunidad;
        //Toast.makeText(espera_soli.this, "Entro"+ n_comunidad, Toast.LENGTH_SHORT).show();
        rechazar.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(espera_soli.this, "Error...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String resp = jsonObj.getString("dato");

                            if (resp.equals("cancelar")){
                                Toast.makeText(espera_soli.this, "Has cancelado la colicitud de union a comunidad", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(espera_soli.this, inicio.class));
                            } else {
                                Toast.makeText(espera_soli.this, "Error...!", Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(espera_soli.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();

            }

        });


    }



    private void cargar_caducidad(){

        String codigo_comunidad = usuario.replace(" ", "%20");
        String url = "http://www.marlonmym.tk/chulki/consulta_notif_cadu.php?ci_us="+codigo_comunidad;
        //Toast.makeText(Login.this, url, Toast.LENGTH_SHORT).show();

        buscar_cadu.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(espera_soli.this, "codigo no encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            res=jsonObj.getString("dato");
                            String[] parts = res.split("-");
                            if (parts[1] == "dato_null"){
                                Toast.makeText(espera_soli.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();

                            }else {

                                String[] fech_cadu = parts[1].split("/");
                                String fecha_caducidad=fech_cadu[0]+"/"+fech_cadu[1]+"/"+fech_cadu[2];
                                String hora_caducidad=fech_cadu[3]+":"+fech_cadu[4]+":"+fech_cadu[5];

                                //Toast.makeText(espera_soli.this, fecha_caducidad+" - "+ hora_caducidad, Toast.LENGTH_SHORT).show();
                                tv_hora.setText(hora_caducidad);
                                tv_fecha.setText(fecha_caducidad);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(espera_soli.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }


        });





    }








    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_of, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_iten_c_sesion){
            preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
            preferences.edit().clear().apply();
            startActivity(new Intent(espera_soli.this, inicio.class));
            //Intent intent= new Intent(menu_comunidad.this, Login.class);
        }
        else if (id == R.id.menu_actualizacion){
            String msj1=("ult_vs");
            String msj2=("error");

            url =(buscar_url(version));
            //buscar_url();
            //Toast.makeText(espera_soli.this, url, Toast.LENGTH_SHORT).show();

            if(url.equals("ult_vs")){
                Toast.makeText(espera_soli.this, "la version "+version+" es la mas reciente", Toast.LENGTH_SHORT).show();
            }else if (url.equals("error")){
                Toast.makeText(espera_soli.this, "Hay un error desconocido", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(espera_soli.this, "Se inicio la descarga", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(espera_soli.this, "Permiso denegado...!!!", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(espera_soli.this, "Error al  buscar actualizaciones", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(espera_soli.this, "Error Desconocido. Intentelo De Nuevo!!" + responseBody, Toast.LENGTH_SHORT).show();
            }


        });
        return resp;
    }
}