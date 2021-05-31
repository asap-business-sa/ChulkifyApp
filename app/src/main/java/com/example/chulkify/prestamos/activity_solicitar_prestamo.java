package com.example.chulkify.prestamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chulkify.Manejo_fechas;
import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.cargar_2;
import com.example.chulkify.envio_solicitud_comu.unir_comunidad;
import com.example.chulkify.inicio;
import com.example.chulkify.transacciones_pg.aportes.Aportar;

public class activity_solicitar_prestamo extends AppCompatActivity {

    private SharedPreferences preferences;

    private TextView valor_prestar, interes, diferido, valor_cuota,total_prestamo, fecha_limite;
    private EditText val_prestamo;
    private Button btn_actualizar, btn_cargar_datos, btn_soli_prestamo;
    private Spinner meses;
    private String fecha1, fecha2, hora1;
    private LinearLayout tarjeta1;
    private String fondo_us, fondo_comu;
    private double fondo_us2, fondo_comu2;
    private  int fd_us, fd_comu;
    private Double interes_p;

    //Variables para capturar preferencias
    private String ci_us_1, gp_1, cg_gp_1, taza,hoy_tran, maximo;


    //variables para capturar fecha
    private int  dia, mes, anio, hora, minutos, segundos;
    private String  diaS, mesS, anioS,horaS, minutosS, segundosS, minutosa;
    String valor_max;
    LinearLayout datos_prestamo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_prestamo);

        val_prestamo=findViewById(R.id.edt_valor_prestamo);
        valor_prestar=findViewById(R.id.txt_v_prestar);
        interes=findViewById(R.id.txt_v_interes);
        diferido=findViewById(R.id.txt_diferido);
        valor_cuota=findViewById(R.id.txt_v_cuota);
        total_prestamo=findViewById(R.id.txt_total_prestamo);
        fecha_limite=findViewById(R.id.txt_f_limite);
        btn_actualizar=findViewById(R.id.btn_refres);
        btn_cargar_datos=findViewById(R.id.btn_cargar_prestamo);
        btn_soli_prestamo=findViewById(R.id.btn_soli_prestamo);
        meses=findViewById(R.id.smeses);
        datos_prestamo=(LinearLayout)findViewById(R.id.ll_datos_prestamo);

        //capturar preferencias
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        ci_us_1=preferences.getString("cedula_usuario", null);
        gp_1=preferences.getString("nombre_comu", null);
        cg_gp_1=preferences.getString("codigo_comu", null);
        taza=preferences.getString("taza", null);
        hoy_tran=preferences.getString("aportes_hoy", null);
        maximo=preferences.getString("maximo", null);
        fondo_us=preferences.getString("fondos_usuario", null);
        fondo_comu=preferences.getString("fondos_comunidad", null);
        interes_p= Double.parseDouble(getString(R.string.interes_prestamo));

        fondo_us2=(Double.parseDouble(fondo_us))*2;
        fondo_comu2=Double.parseDouble(fondo_comu);

        if (fondo_us2 > fondo_comu2){
            valor_max=String.valueOf(fondo_comu2);
            val_prestamo.setText(valor_max);
            //Toast.makeText(activity_solicitar_prestamo.this, "el fondo del usuario es mayor", Toast.LENGTH_SHORT).show();
        }else if (fondo_comu2 >= fondo_us2){
            valor_max=String.valueOf(fondo_us2);
            val_prestamo.setText(valor_max);
            //Toast.makeText(activity_solicitar_prestamo.this, "el fondo de la comunidad es mayor", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(activity_solicitar_prestamo.this, "error", Toast.LENGTH_SHORT).show();

        }

        Manejo_fechas mf= new Manejo_fechas();
        //captura la fecha
        fecha1=mf.fecha_actual();
        fecha2=mf.fechaYhora_actual();


        btn_cargar_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double valprestar =Double.parseDouble(val_prestamo.getText().toString());
                Double valmaximo_p =Double.parseDouble(valor_max);
                String plazo=meses.getSelectedItem().toString();
                if (val_prestamo.getText().toString().isEmpty()) {
                    datos_prestamo.setVisibility(View.GONE);
                    btn_soli_prestamo.setVisibility(View.GONE);
                    Toast.makeText(activity_solicitar_prestamo.this, "Hay campos en blanco ", Toast.LENGTH_SHORT).show();
                } else {
                    if (valprestar > valmaximo_p){
                        datos_prestamo.setVisibility(View.GONE);
                        btn_soli_prestamo.setVisibility(View.GONE);
                       Toast.makeText(activity_solicitar_prestamo.this, "Ha sobrepasado el limite de prerstamo, su valor maximo para prestar es de:   $"+valor_max, Toast.LENGTH_LONG).show();
                    }else{
                        cargar_datos(valprestar, plazo);
                    }
                }
            }
        });



        btn_soli_prestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double valprestar =Double.parseDouble(val_prestamo.getText().toString());
                double val3 =valprestar * interes_p;
                String val3_s=String.valueOf(val3);


                String vl11=val_prestamo.getText().toString();
                String val2=meses.getSelectedItem().toString();
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("valor_prestar",val_prestamo.getText().toString());
                editor.putString("diferido_prestamo",meses.getSelectedItem().toString());
                editor.putString("interes_prestamo",val3_s);
                editor.apply();

                startActivity(new Intent(activity_solicitar_prestamo.this, Activity_enviar_solicitud_prestamo.class));
            }
        });


    }

    public void cargar_datos(double num, String plazo1){

        String val1=String.valueOf(num);
        double val3 =num * interes_p;
        double subtotal=num + val3;
        double pz=Double.parseDouble(plazo1);
        double cuota= subtotal/pz;
        double cuotaR= redondearDecimales(cuota, 2);

        double total = redondearDecimales((cuotaR * pz), 2);;
        String fecha_pz= "0";
        Manejo_fechas mf = new Manejo_fechas();
        if(plazo1.equals("3")){fecha_pz=mf.mes3_fin();}
        else if(plazo1.equals("6")){fecha_pz=mf.mes6_fin();}
        else if(plazo1.equals("9")){fecha_pz=mf.mes9_fin();}
        else if(plazo1.equals("12")){fecha_pz=mf.mes12_fin();}


        String [] fh1=fecha_pz.split("/");
        String fh2=fh1[0]+"/"+fh1[1]+"/"+fh1[2]+"  -  "+fh1[3]+":"+fh1[4]+":"+fh1[5];


        valor_prestar.setText(val1);
        interes.setText(String.valueOf(val3));
        diferido.setText(plazo1);
        valor_cuota.setText(String.valueOf(cuotaR));
        total_prestamo.setText(String.valueOf(total));
        fecha_limite.setText(fh2);
        datos_prestamo.setVisibility(View.VISIBLE);
        btn_soli_prestamo.setVisibility(View.VISIBLE);

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