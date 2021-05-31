package com.example.chulkify;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.chulkify.login.menu_inicio;
import com.loopj.android.http.AsyncHttpClient;


public class Consultas_inicio  extends AppCompatActivity{






    public String calcular_caducidad(){
        SharedPreferences preferences;
        String cdd="CADUCADO";

        Manejo_fechas mf = new Manejo_fechas();

        int aaa=mf.anio_actual();
        int mmm=mf.mes_actual();
        int ddd=mf.dia_actual();
        int hhh=mf.hora_actual();
        int mnt=mf.minuto_actual();
        int sss=mf.segundo_actual();

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        int aux_anio = preferences.getInt("anio_cadu", 0);
        int aux_mes = preferences.getInt("mes_cadu", 0);
        int aux_dia = preferences.getInt("dia_cadu", 0);
        int aux_hora = preferences.getInt("hora_cadu", 0);
        int aux_mnt = preferences.getInt("minuto_cadu", 0);


        if (aaa >= aux_anio){
            if (mmm >= aux_mes){
                if (ddd >= aux_dia){
                    if (hhh >= aux_hora){
                        if (mnt >= aux_mnt){
                            preferences.edit().clear().apply();

                           cdd="CADUCADO";

                        }
                        else{
                           cdd="NO_CADUCADO";
                        }
                    }
                    else{
                        cdd="NO_CADUCADO";
                    }
                }
                else{
                    cdd="NO_CADUCADO";
                }
            }
            else{
                cdd="NO_CADUCADO";
            }
        }

        else{
            cdd="NO_CADUCADO";
        }

        return cdd;
    }
}
