package com.example.chulkify.prestamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class cartilla_pagoActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    private AsyncHttpClient consult_prest,consult_cuota, consult_pago;

    LinearLayout ly_caja_pago,ll_c1 ,ll_c2,ll_c3,ll_c4,ll_c5,ll_c6,ll_c7,ll_c8,ll_c9,ll_c10,ll_c11,ll_c12;
    Button btn_detalle_01,btn_detalle_02,btn_detalle_03,btn_detalle_04,btn_detalle_05,btn_detalle_06,btn_detalle_07,btn_detalle_08,btn_detalle_09,btn_detalle_10,btn_detalle_11,btn_detalle_12,btn_abonar;
    EditText edt_abono;
    TextView txt_nmb_us, Txt_nmb_comu, id_prest, vl_prest, ct_n_cuota, ct_estado, txt_valor_cuota, txt_abonos, txt_saldos;
    TextView estado_cuota01,estado_cuota02,estado_cuota03,estado_cuota04,estado_cuota05,estado_cuota06,estado_cuota07,estado_cuota08,estado_cuota09,estado_cuota10,estado_cuota11,estado_cuota12;
    String usuario, ci_us, n_comu, codg_gp;
    String ext_ini, ext_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartilla_pago);


        //edit txt
        edt_abono=(EditText) findViewById(R.id.edt_abono);

        //tv
        txt_nmb_us=(TextView) findViewById(R.id.txt_nmb_us);
        Txt_nmb_comu=(TextView) findViewById(R.id.txt_nmb_comu);
        id_prest=(TextView) findViewById(R.id.id_prest);
        vl_prest=(TextView) findViewById(R.id.vl_prest);
        ct_n_cuota=(TextView) findViewById(R.id.ct_n_cuota);
        ct_estado=(TextView) findViewById(R.id.ct_estado);
        txt_valor_cuota=(TextView) findViewById(R.id.txt_valor_cuota);
        txt_abonos=(TextView) findViewById(R.id.txt_abonos);
        txt_saldos=(TextView) findViewById(R.id.txt_saldo);

        estado_cuota01=(TextView) findViewById(R.id.estado_cuota_01);
        estado_cuota02=(TextView) findViewById(R.id.estado_cuota_02);
        estado_cuota03=(TextView) findViewById(R.id.estado_cuota_03);
        estado_cuota04=(TextView) findViewById(R.id.estado_cuota_04);
        estado_cuota05=(TextView) findViewById(R.id.estado_cuota_05);
        estado_cuota06=(TextView) findViewById(R.id.estado_cuota_06);
        estado_cuota07=(TextView) findViewById(R.id.estado_cuota_07);
        estado_cuota08=(TextView) findViewById(R.id.estado_cuota_08);
        estado_cuota09=(TextView) findViewById(R.id.estado_cuota_09);
        estado_cuota10=(TextView) findViewById(R.id.estado_cuota_10);
        estado_cuota11=(TextView) findViewById(R.id.estado_cuota_11);
        estado_cuota12=(TextView) findViewById(R.id.estado_cuota_12);


        //linear Layout
        ll_c1=(LinearLayout) findViewById(R.id.lly_c1);
        ll_c2=(LinearLayout) findViewById(R.id.lly_c2);
        ll_c3=(LinearLayout) findViewById(R.id.lly_c3);
        ll_c4=(LinearLayout) findViewById(R.id.lly_c4);
        ll_c5=(LinearLayout) findViewById(R.id.lly_c5);
        ll_c6=(LinearLayout) findViewById(R.id.lly_c6);
        ll_c7=(LinearLayout) findViewById(R.id.lly_c7);
        ll_c8=(LinearLayout) findViewById(R.id.lly_c8);
        ll_c9=(LinearLayout) findViewById(R.id.lly_c9);
        ll_c10=(LinearLayout) findViewById(R.id.lly_c10);
        ll_c11=(LinearLayout) findViewById(R.id.lly_c11);
        ll_c12=(LinearLayout) findViewById(R.id.lly_c12);
        ly_caja_pago=(LinearLayout) findViewById(R.id.ly_caja_pago);


        //button
        btn_abonar=(Button) findViewById(R.id.btn_abonar);
        btn_detalle_01=(Button) findViewById(R.id.btn_detalle_01);
        btn_detalle_02=(Button) findViewById(R.id.btn_detalle_02);
        btn_detalle_03=(Button) findViewById(R.id.btn_detalle_03);
        btn_detalle_04=(Button) findViewById(R.id.btn_detalle_04);
        btn_detalle_05=(Button) findViewById(R.id.btn_detalle_05);
        btn_detalle_06=(Button) findViewById(R.id.btn_detalle_06);
        btn_detalle_07=(Button) findViewById(R.id.btn_detalle_07);
        btn_detalle_08=(Button) findViewById(R.id.btn_detalle_08);
        btn_detalle_09=(Button) findViewById(R.id.btn_detalle_09);
        btn_detalle_10=(Button) findViewById(R.id.btn_detalle_10);
        btn_detalle_11=(Button) findViewById(R.id.btn_detalle_11);
        btn_detalle_12=(Button) findViewById(R.id.btn_detalle_12);

        consult_cuota = new AsyncHttpClient();
        consult_prest= new AsyncHttpClient();
        consult_pago = new AsyncHttpClient();


        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        ci_us = preferences.getString("cedula_usuario", null);
        usuario = preferences.getString("nombre_usuario", null);
        n_comu = preferences.getString("nombre_comu", null);
        codg_gp=preferences.getString("comunidad", null);

        txt_nmb_us.setText(usuario);
        Txt_nmb_comu.setText(n_comu);
        consulta_prestamo();

        btn_abonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pagar_cuota();


            }
        });



        btn_detalle_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("1");

            }
        });


        btn_detalle_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("2");

            }
        });


        btn_detalle_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("3");

            }
        });


        btn_detalle_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("4");

            }
        });


        btn_detalle_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("5");

            }
        });


        btn_detalle_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("6");

            }
        });


        btn_detalle_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("7");

            }
        });


        btn_detalle_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("8");

            }
        });


        btn_detalle_09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("9");

            }
        });


        btn_detalle_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("10");

            }
        });


        btn_detalle_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("11");

            }
        });


        btn_detalle_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta_prestamo_cuota("12");

            }
        });







    }


    private void consulta_prestamo(){

        String ci_usu = ci_us.replace(" ", "%20");
        String nm_comu = n_comu.replace(" ", "_");
        String url_link = getString(R.string.link_consult_prest);
        String url = url_link+"?ci_us="+ci_usu+"&n_comu="+nm_comu;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();
        consult_prest.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res=jsonObj.getString("dato");
                            //Toast.makeText(cargar_2.this, "entro:"+res , Toast.LENGTH_SHORT).show();
                            String[] parts = res.split("//");
                            if (parts[0] == "NO_HAY"){
                                Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                               String id_prestamo=parts[0];
                                String valor_prestamo=parts[1];
                                String plazo_prestamo=parts[2];
                                String valor_pagar=parts[3];
                                String estado_prestamo=parts[4];

                                id_prest.setText(id_prestamo);
                                vl_prest.setText(valor_prestamo);


                                if (plazo_prestamo.equals("3")){

                                    consulta_cuota(id_prestamo, "1");
                                    consulta_cuota(id_prestamo, "2");
                                    consulta_cuota(id_prestamo, "3");



                                }
                                else if (plazo_prestamo.equals("6")){

                                    consulta_cuota(id_prestamo, "1");
                                    consulta_cuota(id_prestamo, "2");
                                    consulta_cuota(id_prestamo, "3");

                                    consulta_cuota(id_prestamo, "4");
                                    consulta_cuota(id_prestamo, "5");
                                    consulta_cuota(id_prestamo, "6");
                                }
                                else if (plazo_prestamo.equals("9")){

                                    consulta_cuota(id_prestamo, "1");
                                    consulta_cuota(id_prestamo, "2");
                                    consulta_cuota(id_prestamo, "3");

                                    consulta_cuota(id_prestamo, "4");
                                    consulta_cuota(id_prestamo, "5");
                                    consulta_cuota(id_prestamo, "6");

                                    consulta_cuota(id_prestamo, "7");
                                    consulta_cuota(id_prestamo, "8");
                                    consulta_cuota(id_prestamo, "9");
                                }
                                else if (plazo_prestamo.equals("12")){


                                    consulta_cuota(id_prestamo, "1");
                                    consulta_cuota(id_prestamo, "2");
                                    consulta_cuota(id_prestamo, "3");

                                    consulta_cuota(id_prestamo, "4");
                                    consulta_cuota(id_prestamo, "5");
                                    consulta_cuota(id_prestamo, "6");

                                    consulta_cuota(id_prestamo, "7");
                                    consulta_cuota(id_prestamo, "8");
                                    consulta_cuota(id_prestamo, "9");

                                    consulta_cuota(id_prestamo, "10");
                                    consulta_cuota(id_prestamo, "11");
                                    consulta_cuota(id_prestamo, "12");
                                }


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cartilla_pagoActivity.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void consulta_cuota(String id_p, final String n_cta){

        String ci_usu = ci_us.replace(" ", "%20");
        String nm_comu = n_comu.replace(" ", "_");


        String url_link = getString(R.string.link_consult_cuota);
        String url = url_link+"?ci_us="+ci_usu+"&n_comu="+nm_comu+"&id_prest="+id_p+"&n_cuota="+n_cta;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();
        consult_prest.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res2=jsonObj.getString("dato");
                            //Toast.makeText(cargar_2.this, "entro:"+res , Toast.LENGTH_SHORT).show();
                            String[] parts2 = res2.split("//");
                            if (parts2[0] == "NO_HAY"){
                                Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                                String valor_cuota=parts2[0];
                                String valor_pago=parts2[1];
                                String valor_saldo=parts2[2];
                                String fecha_ini=parts2[3];
                                String fecha_fin=parts2[4];
                                String estado=parts2[5];


                                Manejo_fechas mf = new Manejo_fechas();
                                ext_ini= mf.extremos_ini();
                                ext_fin= mf.extremos_fin();
                               // Toast.makeText(cartilla_pagoActivity.this, ext_ini+"**"+fecha_ini+"---"+ext_fin+"**"+fecha_fin, Toast.LENGTH_SHORT).show();

                                if (fecha_ini.equals(ext_ini) && fecha_fin.equals(ext_fin)){
                                    consulta_prestamo_cuota(n_cta);
                                    ly_caja_pago.setVisibility(View.VISIBLE);
                                }
                                else {
                                    ly_caja_pago.setVisibility(View.GONE);
                                }

                                if (n_cta.equals("1")){
                                    estado_cuota01.setText(estado);
                                    ll_c1.setVisibility(View.VISIBLE);

                                }
                                else if (n_cta.equals("2")){
                                    estado_cuota02.setText(estado);
                                    ll_c2.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("3")){
                                    estado_cuota03.setText(estado);
                                    ll_c3.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("4")){
                                    estado_cuota04.setText(estado);
                                    ll_c4.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("5")){
                                    estado_cuota05.setText(estado);
                                    ll_c5.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("6")){
                                    estado_cuota06.setText(estado);
                                    ll_c6.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("7")){
                                    estado_cuota07.setText(estado);
                                    ll_c7.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("8")){
                                    estado_cuota08.setText(estado);
                                    ll_c8.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("9")){
                                    estado_cuota09.setText(estado);
                                    ll_c9.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("10")){
                                    estado_cuota10.setText(estado);
                                    ll_c10.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("11")){
                                    estado_cuota11.setText(estado);
                                    ll_c11.setVisibility(View.VISIBLE);

                                }else if (n_cta.equals("12")){
                                    estado_cuota12.setText(estado);
                                    ll_c12.setVisibility(View.VISIBLE);

                                }


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cartilla_pagoActivity.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void consulta_prestamo_cuota(final String numero_de_cuota){

        String ci_usu = ci_us.replace(" ", "%20");
        String nm_comu = n_comu.replace(" ", "_");
        String url_link = getString(R.string.link_consult_prest);
        String url = url_link+"?ci_us="+ci_usu+"&n_comu="+nm_comu;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();
        consult_prest.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res3=jsonObj.getString("dato");
                            //Toast.makeText(cargar_2.this, "entro:"+res , Toast.LENGTH_SHORT).show();
                            String[] parts3 = res3.split("//");
                            if (parts3[0] == "NO_HAY"){
                                Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                                String id_prestamo=parts3[0];
                                String valor_prestamo=parts3[1];
                                String plazo_prestamo=parts3[2];
                                String valor_pagar=parts3[3];
                                String estado_prestamo=parts3[4];


                                info_cuota(id_prestamo, numero_de_cuota);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cartilla_pagoActivity.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void info_cuota(String id_p, final String n_cta){

        String ci_usu = ci_us.replace(" ", "%20");
        String nm_comu = n_comu.replace(" ", "_");

        String url_link = getString(R.string.link_consult_cuota);
        String url = url_link+"?ci_us="+ci_usu+"&n_comu="+nm_comu+"&id_prest="+id_p+"&n_cuota="+n_cta;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();
        consult_prest.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            String res4=jsonObj.getString("dato");
                            //Toast.makeText(cargar_2.this, "entro:"+res , Toast.LENGTH_SHORT).show();
                            String[] parts4 = res4.split("//");
                            if (parts4[0] == "NO_HAY"){
                                Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                                String valor_cuota=parts4[0];
                                String valor_pago=parts4[1];
                                String valor_saldo=parts4[2];
                                String fecha_ini=parts4[3];
                                String fecha_fin=parts4[4];
                                String estado=parts4[5];



                                    ct_n_cuota.setText(n_cta);
                                    ct_estado.setText(estado);
                                    txt_valor_cuota.setText(valor_cuota);
                                    txt_abonos.setText(valor_pago);
                                    txt_saldos.setText(valor_saldo);
                                    edt_abono.setHint(valor_saldo);

                                Manejo_fechas mf = new Manejo_fechas();
                                ext_ini= mf.extremos_ini();
                                ext_fin= mf.extremos_fin();
                                // Toast.makeText(cartilla_pagoActivity.this, ext_ini+"**"+fecha_ini+"---"+ext_fin+"**"+fecha_fin, Toast.LENGTH_SHORT).show();

                                if (fecha_ini.equals(ext_ini) && fecha_fin.equals(ext_fin)){
                                    //consulta_prestamo_cuota(n_cta);
                                    ly_caja_pago.setVisibility(View.VISIBLE);
                                }
                                else {
                                    ly_caja_pago.setVisibility(View.GONE);
                                }








                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cartilla_pagoActivity.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pagar_cuota(){
        String f_ci_us= ci_us.replace(" ", "%20");
        String f_n_comu=n_comu.replace(" ", "_");
        String f_id_prest= id_prest.getText().toString();
        String f_n_cuota= ct_n_cuota.getText().toString();
        String f_v_abono=  edt_abono.getText().toString();
        String f_cdg_gp= codg_gp.replace(" ", "%20");

        Manejo_fechas f_mf= new Manejo_fechas();
        String f_fecha_act= f_mf.fechaYhora_actual();
        String url_link5 = getString(R.string.link_pagar_cuota);
        String url = url_link5+"?ci_us="+f_ci_us+"&n_comu="+f_n_comu+"&id_prest="+f_id_prest+"&n_cuota="+f_n_cuota+"&v_abono="+f_v_abono+"&cdg_gp="+f_cdg_gp+"&fecha="+f_fecha_act;
        //Toast.makeText(cartilla_pagoActivity.this, f_ci_us+"-"+f_n_comu+"-"+f_id_prest+"-"+f_n_cuota+"-"+f_v_abono+"-"+f_cdg_gp+"-"+f_fecha_act , Toast.LENGTH_SHORT).show();

        consult_pago.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cartilla_pagoActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            Toast.makeText(cartilla_pagoActivity.this, "Transaccion: Realizada" , Toast.LENGTH_SHORT).show();
                            edt_abono.setText("");
                            consulta_prestamo();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cartilla_pagoActivity.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });


    }
        /*
        {

        String ci_usu = ci_us.replace(" ", "%20");
        String nm_comu = n_comu.replace(" ", "_");
        String url_link = getString(R.string.link_consult_prest);
        String url = url_link+"?ci_us="+ci_usu+"&n_comu="+nm_comu;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();

    }

         */
}