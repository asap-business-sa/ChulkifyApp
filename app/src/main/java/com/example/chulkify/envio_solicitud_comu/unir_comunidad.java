package com.example.chulkify.envio_solicitud_comu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class unir_comunidad extends AppCompatActivity {
    private SharedPreferences preferences;
    private AsyncHttpClient buscar_comu, buscar_usuario;
    private String usuario, n_usuario;
    private EditText codg_buscar;
    private Button btn_buscar_comu, btn_enviar_soli_comu;
    private TextView tv_nombre_comu, tv_n_integrantes_comu, tv_ubi_comu, tv_admin_comu;
    private String nombre_comu, n_integrantes_comu, ubi_comu, admin_comu,  ci_admin;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unir_comunidad);
        codg_buscar = (EditText) findViewById(R.id.txt_codibo_buscar);
        btn_buscar_comu = (Button) findViewById(R.id.btn_buscar_comu);
        btn_enviar_soli_comu = (Button) findViewById(R.id.btn_env_soli_comu);
        tv_nombre_comu = (TextView) findViewById(R.id.tv_nombre_comu);
        tv_n_integrantes_comu = (TextView) findViewById(R.id.tv_n_i_comu);
        tv_ubi_comu = (TextView) findViewById(R.id.tv_ubi_comu);
        tv_admin_comu = (TextView) findViewById(R.id.tv_admin_comu);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        buscar_comu = new AsyncHttpClient();
        buscar_usuario =new AsyncHttpClient();

        btn_buscar_comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("codigo_comu_solicitud",  (codg_buscar.getText().toString()));
                //editor.putString("nomb_comu_solicitud",  (codg_buscar.getText().toString()));
                editor.apply();
                tv_nombre_comu.setText("-");
                tv_n_integrantes_comu.setText("-");
                tv_ubi_comu.setText("-");
                tv_admin_comu.setText("-");
                cargar_datos();
            }
            });
        btn_enviar_soli_comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(unir_comunidad.this, cargar_2.class));
            }
        });
    }
    private void cargar_datos(){
        if (codg_buscar.getText().toString().isEmpty()) {
            Toast.makeText(unir_comunidad.this, "Hay campos en blanco ", Toast.LENGTH_SHORT).show();
        } else {
            String codigo_comunidad = codg_buscar.getText().toString().replace(" ", "%20");
            String l_c_cm_adm=getString(R.string.link_consulta_comu_admin);
            String url = l_c_cm_adm+"?codigo_comu="+codigo_comunidad;
            //Toast.makeText(Login.this, url, Toast.LENGTH_SHORT).show();
            buscar_comu.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {
                        String respuesta = new String(responseBody);
                        if (respuesta.equalsIgnoreCase("null")) {
                            Toast.makeText(unir_comunidad.this, "codigo no encontrado", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObj = new JSONObject(respuesta);
                                String  dt = jsonObj.getString("dato");
                                String[] parts=dt.split("%%");

                                /*
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("fecha_actual",cd.fecha_actual() );
                                editor.apply();
                                 */

                                nombre_comu =parts[0];
                                n_integrantes_comu =  parts[1];
                                ubi_comu = parts[2];
                                ci_admin = parts[3];
                                admin_comu = parts[4];
                                tv_nombre_comu.setText(nombre_comu);
                                tv_n_integrantes_comu.setText(n_integrantes_comu);
                                tv_ubi_comu.setText(ubi_comu);
                                tv_admin_comu.setText(admin_comu);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("nomb_comu_solicitud",  nombre_comu);
                                editor.apply();
                                btn_enviar_soli_comu.setEnabled(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(unir_comunidad.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
                }
            });
        }
        }
}