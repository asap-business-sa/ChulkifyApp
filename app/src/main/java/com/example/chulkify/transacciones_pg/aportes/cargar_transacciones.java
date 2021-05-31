package com.example.chulkify.transacciones_pg.aportes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.chulkify.R;
import com.example.chulkify.menus_usuarios.menu_comunidad;
import com.example.chulkify.transacciones_pg.transaccionesActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class cargar_transacciones extends AppCompatActivity {

    private SharedPreferences preferences;
    private AsyncHttpClient comu_clien;
    private String usuario;
    private AsyncHttpClient buscar_url;
    private AsyncHttpClient aportar_conm;
    public String ci_us_1,val_aportar,fecha1,hora1,gp_1,cg_gp_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargar_transacciones);


        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        usuario = preferences.getString("comunidad", null);
        ci_us_1= preferences.getString("cedula_usuario", null);
        val_aportar= preferences.getString("val_aportar", null);
        fecha1= preferences.getString("fecha_t_ap", null);
        hora1= preferences.getString("hora_t_ap", null);
        gp_1= preferences.getString("nombre_comu", null);
        cg_gp_1= preferences.getString("codigo_comu", null);
        //Toast.makeText(cargar3.this, usuario, Toast.LENGTH_SHORT).show();
        aportar();


        comu_clien = new AsyncHttpClient();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(cargar_transacciones.this, transaccionesActivity.class);
                startActivity(intent);
                //cargar3.this.finish();
            }
        },07000);
    }

    private void cargar_datos(){

        consulta_datos();

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("val_aportar", "null");
        editor.putString("fecha_t_ap", "null");
        editor.putString("hora_t_ap", "null");
        editor.apply();

    }

   /*
   public void aportar() {
        aportar_conm = new AsyncHttpClient();

        final String ci_us = ci_us_1.trim();
        final String aporte = val_aportar.trim();
        final String fecha = fecha1.toString().trim();
        final String hora = hora1.toString().trim();
        final String gp = gp_1.replace(" ", "%20").trim();
        final String cg_gp = cg_gp_1.trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.marlonmym.tk/chulki/retirar/retirar_us.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("transaccion_correcta")) {
                    Toast.makeText(getApplicationContext(), "la transaccion se realizo con exito ...!!!", Toast.LENGTH_SHORT).show();
                    cargar_datos();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> parametros = new HashMap<String, String>();


                parametros.put("ci_us", ci_us);
                parametros.put("aporte", aporte);
                parametros.put("fecha", fecha);
                parametros.put("hora", hora);
                parametros.put("gp", gp);
                parametros.put("cg_gp", cg_gp);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(cargar_retiro.this);
        requestQueue.add(stringRequest);



    }

    */

    public void aportar(){
        //String ap="null";
        aportar_conm = new AsyncHttpClient();
        String ci_us = ci_us_1.toString().replace(" ", "%20");
        String fecha22 = fecha1.toString().replace(" ", "%20");
        String ci_us2 = ci_us_1.trim();
        String aporte2 = val_aportar.trim();
        String fecha2 = fecha1.toString().trim();
        String gp2 = gp_1.replace(" ", "%20").trim();
        String gp3 = gp_1.replace(" ", "_").trim();
        String cg_gp2 = cg_gp_1.trim();
        String hora2 = hora1.toString().trim();
        String l_ap=getString(R.string.link_aportar);

        String url = l_ap+"?ci_us="+ci_us2+"&aporte="+aporte2+"&fecha="+fecha2+"&gp="+gp2+"&gp2="+gp3+"&cg_gp="+cg_gp2+"&hora="+hora2;

        aportar_conm.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar_transacciones.this, "Error ...!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            Toast.makeText(getApplicationContext(), "la transaccion se realizo con exito ...!!!", Toast.LENGTH_SHORT).show();
                            cargar_datos();

                            //Toast.makeText(getApplicationContext(), "datos cargados...!!!"+parts[0]+" - "+parts[1]+" - "+parts[2]+" - "+parts[3]+" - ", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar_transacciones.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();


            }});

    }


    public void consulta_datos(){
        //String ap="null";
        aportar_conm = new AsyncHttpClient();

        String ci_us = ci_us_1.toString().replace(" ", "%20");
        String fecha22 = fecha1.toString().replace(" ", "%20");
        String l_consulta=getString(R.string.link_consulta_datos);
        String url = l_consulta+"?ci_us="+ci_us+"&fecha="+fecha22;

        aportar_conm.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    if (respuesta.equalsIgnoreCase("null")) {
                        Toast.makeText(cargar_transacciones.this, "Error ...!!", Toast.LENGTH_SHORT).show();
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(cargar_transacciones.this, "Error Desconocido. Intentelo De Nuevo!!"+responseBody, Toast.LENGTH_SHORT).show();
            }});
    }


}