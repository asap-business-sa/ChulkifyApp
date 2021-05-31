package com.example.chulkify.prestamos;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.menus_usuarios.menu_comunidad;
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

public class Activity_menu_prestamos extends AppCompatActivity {
    private static final int PERMISSION_STORAGE_CODE = 1000;
     SharedPreferences preferences;
    String estado_prestamo,estado_soli_pres;


    LinearLayout OPI,OPII,OPIII,OPIV,OPIII_1,OPIII_2,OPIII_3,OPIII_4,A1,AA1,AA2,AA3,AA4,AA5;
    ImageButton B1,B2,B3,B4;
    Button btn_refres;
    TextView tv_comunidad,tv_fecha,txt_usuario_es,T1,T2,T3,T4,T5,T6;

    //preferencias

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_prestamos);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        String e1 = preferences.getString("estado_prestamos", null);
        String e2 = preferences.getString("estado_soli_pres", null);

        OPI=(LinearLayout)findViewById(R.id.op_no_prestamos_msj);
        OPII=(LinearLayout)findViewById(R.id.op_prestamo_pagado);
        OPIII=(LinearLayout)findViewById(R.id.op_soli_activa);
        OPIV=(LinearLayout)findViewById(R.id.op_pres_activo);

        OPIII_1=(LinearLayout)findViewById(R.id.text_msj_soli_activa_espera);
        OPIII_2=(LinearLayout)findViewById(R.id.text_msj_soli_activa_aceptada);
        OPIII_3=(LinearLayout)findViewById(R.id.text_msj_soli_activa_negada);
        OPIII_4=(LinearLayout)findViewById(R.id.txt_msj_soli_e_a);

        A1=(LinearLayout)findViewById(R.id.text_msj_pres_liquidado);
        AA1=(LinearLayout)findViewById(R.id.msj_prestamo_pagando);
        AA2=(LinearLayout)findViewById(R.id.msj_prestamo_refinanciado);
        AA3=(LinearLayout)findViewById(R.id.msj_prestamo_mora);
        AA4=(LinearLayout)findViewById(R.id.msj_prestamo_mora_msj);
        AA5=(LinearLayout)findViewById(R.id.msj_prestamo_r_mora);

        B1=(ImageButton) findViewById(R.id.btn_soli_pres);
        B2=(ImageButton) findViewById(R.id.btn_ht_pres);
        B3=(ImageButton) findViewById(R.id.btn_estado_sol_pres);
        B4=(ImageButton) findViewById(R.id.btn_cartilla_pago);

        btn_refres=(Button) findViewById(R.id.btn_refres);

        T1=findViewById(R.id.text_val_ult_pres);
        T2=findViewById(R.id.text_fch_ult_pres);
        T3=findViewById(R.id.text_val_pres);
        T4=findViewById(R.id.text_cuota_actual);
        T5=findViewById(R.id.text_valor_pago_cuota);
        T6=findViewById(R.id.text_saldo_cuota);

        tv_comunidad=findViewById(R.id.tv_comunidad_m);
        tv_fecha=findViewById(R.id.tv_fecha);
        txt_usuario_es=findViewById(R.id.txt_usuario_us);



        estado_prestamo=e1.toString().trim();
        estado_soli_pres=e2.toString().trim();

        if (estado_prestamo.equals("NO_HAY") ||  estado_prestamo.equals("ESPERA")){


            if (estado_soli_pres.equals("NO_HAY") || estado_soli_pres.equals("CANCELADA")){
                OPI.setVisibility(View.VISIBLE);
                B1.setVisibility(View.VISIBLE);
            }
            else if (estado_soli_pres.equals("ESPERA")){
                Toast.makeText(Activity_menu_prestamos.this, "entro espera", Toast.LENGTH_SHORT).show();

                OPIII.setVisibility(View.VISIBLE);
                OPIII_1.setVisibility(View.VISIBLE);
                OPIII_4.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);

            }
            else if (estado_soli_pres.equals("NEGADA")){
                OPIII.setVisibility(View.VISIBLE);
                OPIII_3.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);

            }
            else if (estado_soli_pres.equals("ACEPTADA")){
                OPIV.setVisibility(View.VISIBLE);
                AA1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B4.setVisibility(View.VISIBLE);

            }

        } else if (estado_prestamo.equals("PAGADO") || estado_prestamo.equals("PAGADO_R")){
            if (estado_soli_pres.equals("NO_HAY") || estado_soli_pres.equals("CANCELADA")){
                OPII.setVisibility(View.VISIBLE);
                B1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                T1.setVisibility(View.VISIBLE);
                T2.setVisibility(View.VISIBLE);
            }else if (estado_soli_pres.equals("ESPERA")){
                OPIII.setVisibility(View.VISIBLE);
                OPIII_1.setVisibility(View.VISIBLE);
                OPIII_4.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
            }else if (estado_soli_pres.equals("NEGADA")){
                OPIII.setVisibility(View.VISIBLE);
                OPIII_3.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
            }else if (estado_soli_pres.equals("ACEPTADA")){
                OPIV.setVisibility(View.VISIBLE);
                AA1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B4.setVisibility(View.VISIBLE);
            }
        } else if (estado_prestamo.equals("LIQUIDADO")){

            OPIII.setVisibility(View.VISIBLE);
            A1.setVisibility(View.VISIBLE);
            if (estado_soli_pres.equals("NO_HAY") || estado_soli_pres.equals("CANCELADA")){
                OPII.setVisibility(View.VISIBLE);
                B1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                T1.setVisibility(View.VISIBLE);
                T2.setVisibility(View.VISIBLE);
            }else if (estado_soli_pres.equals("ESPERA")){
                OPIII_1.setVisibility(View.VISIBLE);
                OPIII_4.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
            }else if (estado_soli_pres.equals("NEGADA")){
                OPIII_3.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
            }else if (estado_soli_pres.equals("ACEPTADA")){
                OPIV.setVisibility(View.VISIBLE);
                AA1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B4.setVisibility(View.VISIBLE);
            }

        }
        else if (estado_prestamo.equals("PAGANDO") ){
            OPIV.setVisibility(View.VISIBLE);
            AA1.setVisibility(View.VISIBLE);
            B2.setVisibility(View.VISIBLE);
            B4.setVisibility(View.VISIBLE);
        }
        else if (estado_prestamo.equals("REFINANCIADO") ){
            OPIV.setVisibility(View.VISIBLE);
            AA2.setVisibility(View.VISIBLE);
            B2.setVisibility(View.VISIBLE);
            B4.setVisibility(View.VISIBLE);
        }
        else if (estado_prestamo.equals("MORA") ){
            OPIV.setVisibility(View.VISIBLE);
            AA3.setVisibility(View.VISIBLE);
            AA4.setVisibility(View.VISIBLE);
            B2.setVisibility(View.VISIBLE);
            B4.setVisibility(View.VISIBLE);
        }else if (estado_prestamo.equals("REFINANCIADO_MORA") ){
            OPIV.setVisibility(View.VISIBLE);
            AA5.setVisibility(View.VISIBLE);
            AA4.setVisibility(View.VISIBLE);
            B2.setVisibility(View.VISIBLE);
            B4.setVisibility(View.VISIBLE);
        }

        else{
            Toast.makeText(Activity_menu_prestamos.this, "error", Toast.LENGTH_SHORT).show();

        }

//Boton SOLICITUD

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_menu_prestamos.this, activity_solicitar_prestamo.class));
                //Toast.makeText(menu_inicio.this, "url", Toast.LENGTH_SHORT).show();
            }
        });


        //Boton espera solicitud

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_menu_prestamos.this, activity_espera_soli_prestamo.class));
                //Toast.makeText(menu_inicio.this, "url", Toast.LENGTH_SHORT).show();
            }
        });


        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_menu_prestamos.this,cartilla_pagoActivity.class));
                //Toast.makeText(menu_inicio.this, "url", Toast.LENGTH_SHORT).show();
            }
        });



    }
}