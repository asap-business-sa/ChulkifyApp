package com.example.chulkify.prestamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.espera_soli;
import com.example.chulkify.inicio;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class activity_espera_soli_prestamo extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    private SharedPreferences preferences;
    private AsyncHttpClient buscar_soli, buscar_url, buscar_cadu, cancelar_htt;
    private Button cancelar;
    private TextView tv_mensaje, tv_nombre_comu, tv_aceptadas, tv_espera, tv_negadas, tv_v_pres, tv_diferido, tv_cuota;
    private String nombre_comu, aceptadas, espera, negadas, usuario, resp,res, comunidad;
    private int n_aceptadas, n_espera, n_negadas;
    private String version, url="null";
    String n_comu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_soli_prestamo);

        cancelar = (Button) findViewById(R.id.btn_env_soli_comu);
        tv_nombre_comu = (TextView) findViewById(R.id.tv_nombre_comu);
        tv_aceptadas = (TextView) findViewById(R.id.tv_n_i_comu);
        tv_espera = (TextView) findViewById(R.id.tv_ubi_comu);
        tv_negadas = (TextView) findViewById(R.id.tv_admin_comu);
        tv_mensaje=(TextView) findViewById(R.id.tv_mensaje_2);
        tv_v_pres=(TextView) findViewById(R.id.txt_valor_pres);
        tv_cuota=(TextView) findViewById(R.id.txt_cuota);
        tv_diferido=(TextView) findViewById(R.id.txt_diferido);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("cedula_usuario", null);
        comunidad = preferences.getString("nombre_comu", null);
        n_comu=preferences.getString("nombre_comu", null);
        version = preferences.getString("version", null);

        buscar_soli = new AsyncHttpClient();

        buscar_url =new AsyncHttpClient();
        buscar_cadu =new AsyncHttpClient();

        tv_nombre_comu.setText("-");
        tv_aceptadas.setText("-");
        tv_espera.setText("-");
        tv_negadas.setText("-");
        //tv_fecha.setText("0000");
        //tv_hora.setText("1111");
       cargar_datos();

        //Toast.makeText(espera_soli.this, version, Toast.LENGTH_SHORT).show();
        //buscar_url();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // cancelar_soli(usuario, n_comu);
            }
        });


    }



    private void cargar_datos(){
        String codigo_comunidad = usuario.replace(" ", "%20");
        String n_comunidad = n_comu.replace(" ", "_");
        String link_consult=getString(R.string.link_estado_solicitud_prestamos);
        String url = link_consult+"?ci_us="+codigo_comunidad+"&n_comu="+n_comunidad;
        buscar_soli.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(activity_espera_soli_prestamo.this, "codigo no encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObj = new JSONObject(respuesta);
                            res=jsonObj.getString("dato");
                            String[] parts = res.split("/");
                            Toast.makeText(activity_espera_soli_prestamo.this, res, Toast.LENGTH_SHORT).show();
                            if (parts[1] == "NO_HAY"){
                                Toast.makeText(activity_espera_soli_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(activity_espera_soli_prestamo.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }


        });

    }

/*
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
                        Toast.makeText(activity_espera_soli_prestamo.this, "Error...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String resp = jsonObj.getString("dato");

                            if (resp.equals("cancelar")){
                                Toast.makeText(activity_espera_soli_prestamo.this, "Has cancelado la colicitud de union a comunidad", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(activity_espera_soli_prestamo.this, inicio.class));
                            } else {
                                Toast.makeText(activity_espera_soli_prestamo.this, "Error...!", Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(activity_espera_soli_prestamo.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();


            }


        });


    }

 */
}