package com.example.chulkify.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.cargar_2;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class nuevo_usuario extends AppCompatActivity {

    private EditText edt_cedula, edt_usuario, edt_correo, edt_nombre,
            edt_apellido, edt_contrasena1, edt_contrasena2, edt_direccion,
            edt_ciudad, edt_telefono;

    private String fecha;
    private int  dia, mes, anio;
    private int  diaS, mesS, anioS;
    private Button btn_guardar;
    private AsyncHttpClient cliente,clien;
    private Date fecha_dt;

    //validar correo patron
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        Manejo_fechas new_f=new Manejo_fechas();
        fecha = new_f.fechaYhora_actual();


        edt_cedula=(EditText) findViewById(R.id.txt_nu_ci);
        edt_usuario=(EditText) findViewById(R.id.txt_nu_user);
        edt_correo=(EditText) findViewById(R.id.txt_nu_correo);
        edt_nombre=(EditText) findViewById(R.id.txt_nu_nombre);
        edt_apellido=(EditText) findViewById(R.id.txt_nu_apellido);
        edt_contrasena1=(EditText) findViewById(R.id.txt_nu_pass);
        edt_contrasena2=(EditText) findViewById(R.id.txt_nu_cpass);
        edt_direccion=(EditText) findViewById(R.id.txt_nu_direccion);
        edt_ciudad=(EditText) findViewById(R.id.txt_nu_ciudad);
        edt_telefono=(EditText) findViewById(R.id.txt_nu_telefono);




        cliente=new AsyncHttpClient();
        clien=new AsyncHttpClient();


        btn_guardar=(Button) findViewById(R.id.btn_registrar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarregistro();
            }
        });
    }



    public void ejecutarregistro(){
        String cedula = edt_cedula.getText().toString().trim();
        String usuario_new = edt_usuario.getText().toString().trim();
        String correo = edt_correo.getText().toString().trim();
        String nombre = edt_nombre.getText().toString().trim();
        String apellido = edt_apellido.getText().toString().trim();
        String pass = edt_contrasena1.getText().toString().trim();
        String pass2 = edt_contrasena2.getText().toString().trim();
        String direccion = edt_direccion.getText().toString().trim();
        String ciudad = edt_ciudad.getText().toString().trim();
        String telefono = edt_telefono.getText().toString().trim();
        String fcha = fecha.trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        Matcher mather = pattern.matcher(correo);
        if (usuario_new.isEmpty()) {
            edt_usuario.setError("complete los campos");
        } else if (correo.isEmpty()) {
            edt_correo.setError("complete los campos");
        } else if (mather.find() == false) {
            edt_correo.setError("el correo ingresado es invalido");
        } else if (pass.isEmpty()) {
            edt_contrasena1.setError("complete los campos");
        } else if (pass2.isEmpty()) {
            edt_contrasena2.setError("complete los campos");
        }else if (pass2.equals(pass)== false) {
            edt_contrasena2.setError("las contraseñas no coinciden");
        } else if (nombre.isEmpty()) {
            edt_nombre.setError("complete los campos");
        } else if (apellido.isEmpty()) {
            edt_apellido.setError("complete los campos");
        } else if (cedula.isEmpty()) {
            edt_cedula.setError("complete los campos");
        }  else if (ciudad.isEmpty()) {
            edt_ciudad.setError("complete los campos");
        } else if (direccion.isEmpty()) {
            edt_direccion.setError("complete los campos");
        } else if (telefono.isEmpty()) {
            edt_telefono.setError("complete los campos");
        } else {



        String ci_us = cedula.replace(" ", "%20");
        String nw_us = usuario_new.replace(" ", "%20");
        String correo_us = correo.replace(" ", "%20");
        String nom_us = nombre.replace(" ", "%20");
        String ape_us = apellido.replace(" ", "%20");
        String pass_us = pass.replace(" ", "%20");
        String dir_us = direccion.replace(" ", "%20");
        String cd_us = ciudad.replace(" ", "%20");
        String tf_us = telefono.replace(" ", "%20");
        String fecha_us = fcha.replace(" ", "%20");
        String l_rg=getString(R.string.link_registro_us);
        String url = l_rg+"?cedula_us="+ci_us+"&nombre_us="+nom_us+"&apellidos_us="+ape_us+"&correo_us="+correo_us+"&usuario_us="+nw_us+"&contrasena_us="+pass_us+"&telefono_us="+tf_us+"&direccion_us="+dir_us+"&ciudad_us="+cd_us+"&fecha_inicio_us="+fecha_us;

        clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(nuevo_usuario.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            Toast.makeText(getApplicationContext(), "El usuario  se guardo con exito", Toast.LENGTH_SHORT).show();
                            //progressDialog.dismiss();
                            startActivity(new Intent(nuevo_usuario.this, Login.class));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(nuevo_usuario.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();

            }


        });
    }

    }


}
