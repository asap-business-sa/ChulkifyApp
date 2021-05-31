package com.example.chulkify.login;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.inicio;
import com.example.chulkify.menus_usuarios.menu_no_comunidad;
import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import org.json.JSONObject;

import java.io.File;

public class Login extends AppCompatActivity {

    //private GoogleApiClient googleApiClient;

    private static final int PERMISSION_STORAGE_CODE = 1000;

    private EditText et_usuario, et_contra;
    private Button btn_Logear;
    private TextView tv1;
    private ImageButton  btn_new_us;
    private AsyncHttpClient usuario_clien, buscar_url;
    private SharedPreferences preferences;
    private String us, pw;

    private String codigo1;


    private int m_cadu=0;
    private String mnt="0";
    private String version_1="default", url="null",resp,res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //fyh_actual2();

        btn_Logear = (Button) findViewById(R.id.btn_ingresar);
        et_usuario = (EditText) findViewById(R.id.txt_lg_user);
        et_contra = (EditText) findViewById(R.id.txt_lg_pass);



        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        version_1=preferences.getString("version", null);
        Toast.makeText(Login.this, version_1, Toast.LENGTH_SHORT).show();

        buscar_url =new AsyncHttpClient();
        String aux =(buscar_url(version_1));

        usuario_clien = new AsyncHttpClient();
        botonLoguin();
    }

    private void botonLoguin() {
        btn_Logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_usuario.getText().toString().isEmpty() || et_contra.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Hay campos en blanco ", Toast.LENGTH_SHORT).show();
                } else {
                    String usuario = et_usuario.getText().toString().replace(" ", "%20");
                    String password = et_contra.getText().toString().replace(" ", "%20");
                    String l_lg= getString(R.string.link_login);
                    String url = l_lg+"?usuario_us="+usuario+"&contrasena_us="+password;
                    //Toast.makeText(Login.this, url, Toast.LENGTH_SHORT).show();

                    usuario_clien.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200) {
                                String respuesta = new String(responseBody);
                                if (respuesta.equalsIgnoreCase("null")) {
                                    Toast.makeText(Login.this, "Error De Usuario y/o Contraseña!!", Toast.LENGTH_SHORT).show();
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
                                        editor.putString("tipo", jsonObj.getString("tipo_us"));
                                        editor.putString("fondos_us", jsonObj.getString("fondos_us"));
                                        editor.putString("fecha_ini", jsonObj.getString("fecha_inicio_us"));
                                        editor.putString("pass", jsonObj.getString("contrasena_us"));
                                        editor.putString("fecha_union_grupo", jsonObj.getString("fecha_union_comu_us"));
                                        editor.putString("estado_usuario", jsonObj.getString("estado_gru_us"));
                                        editor.putString("linea_ap",jsonObj.getString("histo_aport_us"));
                                        editor.apply();

                                        Intent intent = null;
                                        if(n_tp.equals("NO_COMU")){
                                            intent= new Intent(Login.this, menu_no_comunidad.class);
                                        }else if(n_tp.equals("US_COMU")){
                                            intent= new Intent(Login.this, cargar_1.class);
                                        }else if(n_tp.equals("ADMIN_COMU")){
                                            intent= new Intent(Login.this, cargar_1.class);
                                        }else if(n_tp.equals("US_ESPERA")){
                                            intent= new Intent(Login.this, cargar3.class);
                                        }else if(n_tp.equals("SUPER_US")){
                                            //Toast.makeText(Login.this, "Entro!!", Toast.LENGTH_SHORT).show();

                                            intent= new Intent(Login.this, cargar_admin_Activity.class);
                                        }
                                        else {
                                            Toast.makeText(Login.this, "Errode tipo!!", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(Login.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();


                            et_usuario.setText("");
                            et_contra.setText("");
                        }


                    });
                }

            }

        });

    }



    // menu superoir derecho
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ini, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_actualizacion){
            String msj1=("ult_vs");
            String msj2=("error");

            url =(buscar_url(version_1));
            //buscar_url();
            //Toast.makeText(espera_soli.this, url, Toast.LENGTH_SHORT).show();

            if(url.equals("ult_vs")){
                Toast.makeText(Login.this, "la version "+version_1+" es la mas reciente", Toast.LENGTH_SHORT).show();
            }else if (url.equals("error")){
                Toast.makeText(Login.this, "Hay un error desconocido", Toast.LENGTH_SHORT).show();
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
        url =(buscar_url(version_1));
        String url_d= url.trim();
        File file = new File(getExternalFilesDir(null), "chulkify.apk");
        Toast.makeText(Login.this, "Se inicio la descarga", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Login.this, "Permiso denegado...!!!", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(Login.this, "Error al  buscar actualizaciones", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Login.this, "Error Desconocido. Intentelo De Nuevo!!" + responseBody, Toast.LENGTH_SHORT).show();
            }


        });
        return resp;
    }


}



