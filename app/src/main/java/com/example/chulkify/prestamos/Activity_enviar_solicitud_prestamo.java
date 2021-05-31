package com.example.chulkify.prestamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.cargar_2;
import com.example.chulkify.inicio;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Activity_enviar_solicitud_prestamo extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien, regis_gp, regis_us, regis_us33;

    private String usuario, cg_comu, val_prestamo, plaz_prestamo;
    private String res, nnn, int_p;
    private String f_actual, nomb_comu;
    private String[]  fi = new String[13];
    private String[]  ff = new String[13];


    private String fechacrea, fechacadu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_solicitud_prestamo);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        cg_comu = preferences.getString("comunidad", null);
        usuario =preferences.getString("cedula_usuario", null);
        val_prestamo=preferences.getString("valor_prestar", null);

        plaz_prestamo=preferences.getString("diferido_prestamo", null);
        int_p=preferences.getString("interes_prestamo", null);
        nomb_comu = preferences.getString("nombre_comu", null);


        Manejo_fechas mf = new Manejo_fechas();

        fechacrea=mf.fechaYhora_actual();
        fechacadu=mf.caducidad();
        f_actual= mf.fecha_actual();

        fi[0]=mf.mes1_inicio();
        fi[1]=mf.mes2_inicio();
        fi[2]=mf.mes3_inicio();
        fi[3]=mf.mes4_inicio();
        fi[4]=mf.mes5_inicio();
        fi[5]=mf.mes6_inicio();
        fi[6]=mf.mes7_inicio();
        fi[7]=mf.mes8_inicio();
        fi[8]=mf.mes9_inicio();
        fi[9]=mf.mes10_inicio();
        fi[10]=mf.mes11_inicio();
        fi[11]=mf.mes12_inicio();

        ff[0]=mf.mes1_fin();
        ff[1]=mf.mes2_fin();
        ff[2]=mf.mes3_fin();
        ff[3]=mf.mes4_fin();
        ff[4]=mf.mes5_fin();
        ff[5]=mf.mes6_fin();
        ff[6]=mf.mes7_fin();
        ff[7]=mf.mes8_fin();
        ff[8]=mf.mes9_fin();
        ff[9]=mf.mes10_fin();
        ff[10]=mf.mes11_fin();
        ff[11]=mf.mes12_fin();




       /* Toast.makeText(Activity_enviar_solicitud_prestamo.this, cg_comu, Toast.LENGTH_SHORT).show();
        Toast.makeText(Activity_enviar_solicitud_prestamo.this, usuario, Toast.LENGTH_SHORT).show();
        Toast.makeText(Activity_enviar_solicitud_prestamo.this, val_prestamo, Toast.LENGTH_SHORT).show();
        Toast.makeText(Activity_enviar_solicitud_prestamo.this, plaz_prestamo, Toast.LENGTH_SHORT).show();


        */

        comu_clien = new AsyncHttpClient();
        regis_gp = new AsyncHttpClient();
        regis_us = new AsyncHttpClient();
        regis_us33 = new AsyncHttpClient();

       datos_us();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(Activity_enviar_solicitud_prestamo.this, inicio.class);
                startActivity(intent);


            }
        },25000);

    }

    private void datos_us(){

        String cog_comu = cg_comu.replace(" ", "%20");
        String url_link = getString(R.string.link_buscar_usuarios);
        String url = url_link+"?codigo_comu="+cog_comu;
        //Toast.makeText(cargar_2.this, url , Toast.LENGTH_SHORT).show();
        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            JSONObject jsonObj = new JSONObject(respuesta);
                            res=jsonObj.getString("dato");
                            //Toast.makeText(cargar_2.this, "entro:"+res , Toast.LENGTH_SHORT).show();
                            String[] parts = res.split("/");
                            if (parts[1] == "dato_null"){
                                Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                            }else {
                                for (int i = 1; i < parts.length; i++) {
                                    nnn = parts[i];
                                    String[] parts2 = nnn.split("%%");
                                    String usuario_soli=parts2[1];
                                    String ci_soli=parts2[0];
                                    if (ci_soli.equals(usuario)){

                                    }else {

                                        enviar_solicitudes(ci_soli);
                                        Toast.makeText(Activity_enviar_solicitud_prestamo.this, " enviando Solicitud a:" + usuario_soli, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                crear_prestamo_gp();
                                //Toast.makeText(getApplicationContext(), "las solicitudes fueron enviadas con exito ...!!!", Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void enviar_solicitudes(String dato){


        String ci_emisor = usuario.replace(" ", "%20");
        String cg_cm = cg_comu.replace(" ", "%20");
        String ci_receptor = dato.replace(" ", "%20");
        String fecha_crea = fechacrea.replace(" ", "%20");
        String fecha_cadu = fechacadu.replace(" ", "%20");
        String v_pres = val_prestamo.replace(" ", "%20");
        String p_pres = plaz_prestamo.replace(" ", "%20");

        String url_link2 = getString(R.string.link_generar_solicitud_prestamos);
        String url = url_link2+"?ci_emisor="+ci_emisor+"&cg_cm="+cg_cm+"&ci_receptor="+ci_receptor+"&fecha_crea="+fecha_crea+"&fecha_cadu="+fecha_cadu+"&p_pres="+p_pres+"&v_pres="+v_pres;
        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        comu_clien.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            Toast.makeText(getApplicationContext(), "la solicitud fue enviada con exito ...!!!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void crear_prestamo_gp(){
        double valpres=Double.parseDouble(val_prestamo);
        double valint =Double.parseDouble(int_p);
        double pz=Double.parseDouble(plaz_prestamo);
        double subtotal=valpres+valint;
        double cuota= subtotal/pz;
        double cut_c =redondearDecimales(cuota, 2);
        double total=cut_c*pz;

        String ci_us = usuario.replace(" ", "%20");
        String n_comu = nomb_comu.replace(" ", "_");
        String v_pres = val_prestamo.replace(" ", "%20");
        String i_pres = int_p.replace(" ", "%20");
        String p_pres = plaz_prestamo.replace(" ", "%20");
        String ct_p =String.valueOf(cut_c).replace(" ", "%20");
        String t_pagar = String.valueOf(total).replace(" ", "%20");
        String fecha_crea = f_actual.replace(" ", "%20");
        String fecha_fin="0/0/0/0/0/0";

        if (plaz_prestamo.equals("3")){  fecha_fin= ff[2].replace(" ", "%20");  }
        else if (plaz_prestamo.equals("6")){  fecha_fin= ff[5].replace(" ", "%20");  }
        else if (plaz_prestamo.equals("9")){  fecha_fin= ff[8].replace(" ", "%20");  }
        else if (plaz_prestamo.equals("12")){  fecha_fin= ff[11].replace(" ", "%20"); }

        String url_link3 = getString(R.string.link_gp_crea_pres);
        String url = url_link3+"?ci_us="+ci_us+"&n_cm="+n_comu+"&v_pres="+v_pres+"&i_pres="+i_pres+"&vc_pres="+ct_p+"&vp_pres="+t_pagar+"&p_pres="+p_pres+"&fi_pres="+fecha_crea+"&ff_pres="+fecha_fin;
        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        regis_gp.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            ult_prestamo();



                            //Toast.makeText(getApplicationContext(), "la solicitud fue enviada con exito ...!!!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error Desconocido. 000002"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void crear_prestamo_us(String fch_i, String fch_f, String n_cuota, String id_pres){
        double valpres=Double.parseDouble(val_prestamo);
        double valint =Double.parseDouble(int_p);
        double pz=Double.parseDouble(plaz_prestamo);
        double subtotal=valpres+valint;
        double cuota= subtotal/pz;
        double cut_c =redondearDecimales(cuota, 2);
        double total=cut_c*pz;

        String ci_us = usuario.replace(" ", "%20");
        String n_comu = nomb_comu.replace(" ", "_");
        String ct_p =String.valueOf(cut_c).replace(" ", "%20");
        String n_ct =n_cuota.replace(" ", "%20");
        String f_crea = f_actual.replace(" ", "%20");
        String f_ini=fch_i.replace(" ", "%20");
        String f_fin=fch_f.replace(" ", "%20");
        String url_link4 = getString(R.string.link_us_crea_pres);
        String id_p =id_pres.replace(" ", "%20");


        String url = url_link4+"?ci_us="+ci_us+"&v_cuota="+ct_p+"&n_comu="+n_comu+"&n_cuota="+n_ct+"&f_ini="+f_ini+"&f_fin="+f_fin+"&f_crea="+f_crea+"&id_p="+id_p;

        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        regis_us.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            //Toast.makeText(getApplicationContext(), "Solicitudes enviadas con exito ...!!!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error Desconocido. 00001"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ult_prestamo(){


        String ci_us = usuario.replace(" ", "%20");
        String n_comu = nomb_comu.replace(" ", "_");

        String url_link5 = getString(R.string.link_ult_prestamo);


        String url = url_link5+"?ci_us="+ci_us+"&n_comu="+n_comu;

        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        regis_us33.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            //Toast.makeText(getApplicationContext(),"entro nuevamente1:   ", Toast.LENGTH_SHORT).show();

                            JSONObject jsonObj2 = new JSONObject(respuesta);
                            String res_id=jsonObj2.getString("dato");
                          Toast.makeText(getApplicationContext(),"entro nuevamente:   " + res_id, Toast.LENGTH_SHORT).show();

                            int i_p=Integer.parseInt(plaz_prestamo);
                            for(int i = 0; i < i_p; i++){
                                int aux1 = i+1;
                                Toast.makeText(getApplicationContext(), "entro"+plaz_prestamo + i, Toast.LENGTH_SHORT).show();
                                String aux2 = String.valueOf(aux1);
                                crear_prestamo_us(fi[i], ff[i], aux2,res_id);}



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Activity_enviar_solicitud_prestamo.this, "Error Desconocido. 00001"+responseBody, Toast.LENGTH_SHORT).show();
            }
        });
    }




    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }


}