package com.example.chulkify.menus_usuarios;

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

import com.example.chulkify.Comunidad;
import com.example.chulkify.ConfiguraciondeComunidad;
import com.example.chulkify.PerfildelUsuario;
import com.example.chulkify.prestamos.cargahistorial;
import com.example.chulkify.transacciones_pg.transaccionesActivity;
import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.solicitudes.Soli_notifi;
import com.example.chulkify.inicio;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class menu_comunidad extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    private SharedPreferences preferences;
    private AsyncHttpClient buscar_url;
    private AsyncHttpClient aportar_conm;


    private TextView nombre_comu, fecha_comu, mv_usuario, tv_codigo,tv_t_us;
    private String usuario, fecha_m;
    private String codg_comu, city_comu,  nom_comu, fecha_at;
    private AsyncHttpClient comu_clien;
    private  int id_comu;
    private String nomb,most_fecha, n_comu, m_usuario, conv_total_us,total_us_comu, cog_cm;
    private Button btn_actualizar;
    ImageButton btn_prestamos;
    private EditText codigo_cop;
    private String ci_us_1,fecha1,hoy_tran;
    String aux;

    private String fecha;
    private int  dia, mes, anio;
    private int  diaS, mesS, anioS;

    private String version, url="null", resp,res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_comunidad);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        version = preferences.getString("version", null);
        usuario = preferences.getString("cedula_usuario", null);
        n_comu = preferences.getString("nombre_comu", null);
        fecha_m = preferences.getString("fecha_union_grupo", null);
        m_usuario = preferences.getString("nombre_usuario" , null);
        codg_comu = preferences.getString("codigo_comu",null);
        fecha_at = preferences.getString("fecha_actual",null);
        total_us_comu= String.valueOf(preferences.getInt("total_usuario_comu",0));
        btn_prestamos = (ImageButton) findViewById(R.id.btn_prestamos);
        ci_us_1=usuario;


        nombre_comu=findViewById(R.id.tv_comunidad_m);
        fecha_comu=findViewById(R.id.tv_fecha);
        mv_usuario=findViewById(R.id.txt_usuario_us);
        tv_codigo =findViewById(R.id.tv_codigo);
        tv_t_us   =findViewById(R.id.tv_t_us);
        codigo_cop = findViewById(R.id.edt_codigo);
        buscar_url =new AsyncHttpClient();
        String aux =(buscar_url(version));


        comu_clien = new AsyncHttpClient();

        Manejo_fechas n_p =new Manejo_fechas();
        fecha = n_p.fecha_actual();

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("fecha_actual", fecha);
        editor.apply();
       mostrar_datos();

         preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        String linea_aportes=preferences.getString("linea_ap", null);



        String ln_ap=linea_aportes;
        String[] parts = ln_ap.split("/");

       // Toast.makeText(menu_comunidad.this, "Error ...!!"+ linea_aportes, Toast.LENGTH_SHORT).show();

        if (parts[0].equals("0")||parts[0].equals("2")||parts[0].equals("3")||parts[0].equals("1")||parts[0].equals("4")||parts[0].equals("5")){
            aux="no_prestamos";
        }else {
           // Toast.makeText(menu_comunidad.this, "entro...!!!", Toast.LENGTH_SHORT).show();
            btn_prestamos.setVisibility(View.VISIBLE);
        }



        btn_actualizar = (Button) findViewById(R.id.btn_refres);
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrar_datos();
            }
        });



        //Boton notificaciones
        ImageButton btn_noti = (ImageButton) findViewById(R.id.btn_notificaciones);
        btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu_comunidad.this, Soli_notifi.class));
                //Toast.makeText(menu_inicio.this, "url", Toast.LENGTH_SHORT).show();
            }
        });

        //Boton aportes
        ImageButton btn_apotes = (ImageButton) findViewById(R.id.btn_aportes);
        btn_apotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_datos();
                startActivity(new Intent(menu_comunidad.this, transaccionesActivity.class));
                //Toast.makeText(menu_inicio.this, "url", Toast.LENGTH_SHORT).show();
            }
        });


        //Boton prestamos

        btn_prestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu_comunidad.this, cargahistorial.class));
                //Toast.makeText(menu_inicio.this, "url", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void perfil(View view){
        Intent perfil = new Intent(this, PerfildelUsuario.class);
        startActivity(perfil);
    }
    public void configuracion_comu(View view){
        Intent configuracion_comu = new Intent(this, ConfiguraciondeComunidad.class);
        startActivity(configuracion_comu);
    }
    public void comunidad(View view){
        Intent comunidad = new Intent(this, Comunidad.class);
        startActivity(comunidad);
    }


    public void consulta_datos(){
        //String ap="null";
        aportar_conm = new AsyncHttpClient();

        String ci_us = ci_us_1.toString().replace(" ", "%20");
        String fecha22 = fecha.toString().replace(" ", "%20");
        String l_consulta=getString(R.string.link_consulta_datos);
        String url = l_consulta+"?ci_us="+ci_us+"&fecha="+fecha22;

        aportar_conm.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(menu_comunidad.this, "Error ...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {


                            JSONObject jsonObj = new JSONObject(respuesta);
                            String ap = jsonObj.getString("dato");
                            String[] parts=ap.split("%%");

                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("fondos_us", parts[0]);
                            editor.putString("aportes_hoy", parts[1]);
                            editor.putString("retiro_hoy", parts[2]);
                            editor.putString("linea_ap", parts[3]);
                            editor.apply();
                            String aux2="dt1: "+parts[0]+"dt2: "+parts[1]+"dt3: "+parts[2]+"dt4: "+parts[3];

                           // Toast.makeText(menu_comunidad.this, "Error ...!!"+ aux2, Toast.LENGTH_SHORT).show();


                            preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
                            hoy_tran=preferences.getString("aportes_hoy", null);




                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(menu_comunidad.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }});
    }
    private void cargar_datos(){
        String cog_comu = codg_comu.replace(" ", "%20");
        String l_consult_comu=getString(R.string.link_consultar_comunidad);
        String url = l_consult_comu+"?codigo_comu="+cog_comu;
        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(menu_comunidad.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("ciudad_comu", jsonObj.getString("ciudad_comu"));
                            editor.putInt("id_comu", jsonObj.getInt("id_comu"));
                            editor.putString("nombre_comu", jsonObj.getString("nombre_comu"));
                            editor.putString("codigo_comu", jsonObj.getString("codigo_comu"));
                            editor.putInt("total_usuario_comu", jsonObj.getInt("total_usuario_comu"));
                            editor.apply();
                             consulta_datos();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(menu_comunidad.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();

            }


        });
    }
    private void mostrar_datos(){
        cargar_datos();

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        n_comu = preferences.getString("nombre_comu", null);
        codg_comu = preferences.getString("codigo_comu",null);
        total_us_comu= String.valueOf(preferences.getInt("total_usuario_comu",0));





        most_fecha= fecha;
        nombre_comu.setText(n_comu);
        fecha_comu.setText(most_fecha);
        mv_usuario.setText(m_usuario);
        codigo_cop.setText(codg_comu);
        tv_codigo.setText("");
        tv_t_us.setText(total_us_comu);
    }


    //menu supoerior derecho
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_of, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_iten_c_sesion){
            preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
            preferences.edit().clear().apply();
            startActivity(new Intent(menu_comunidad.this, inicio.class));
            //Intent intent= new Intent(menu_comunidad.this, Login.class);
        }
        else if (id == R.id.menu_actualizacion){
            String msj1=("ult_vs");
            String msj2=("error");

            url =(buscar_url(version));
            //buscar_url();
            //Toast.makeText(espera_soli.this, url, Toast.LENGTH_SHORT).show();

            if(url.equals("ult_vs")){
                Toast.makeText(menu_comunidad.this, "la version "+version+" es la mas reciente", Toast.LENGTH_SHORT).show();
            }else if (url.equals("error")){
                Toast.makeText(menu_comunidad.this, "Hay un error desconocido", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(menu_comunidad.this, "Se inicio la descarga", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(menu_comunidad.this, "Permiso denegado...!!!", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(menu_comunidad.this, "Error al  buscar actualizaciones", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(menu_comunidad.this, "Error Desconocido. Intentelo De Nuevo!!" + responseBody, Toast.LENGTH_SHORT).show();
            }


        });
        return resp;
    }



}