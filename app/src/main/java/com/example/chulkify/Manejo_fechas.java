package com.example.chulkify;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Manejo_fechas {


    private SharedPreferences preferences;
    private String fecha;
    private int  dia, mes, anio, hora, minutos, segundos;
    private String  diaS, mesS, anioS,horaS, minutosS, segundosS, minutosa;
    private int m_cadu=0;
    private String mnt="0";

    public String fecha_actual(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();

        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        mes= fecha_a.get(Calendar.MONTH)+1;
        anio= fecha_a.get(Calendar.YEAR);

        diaS= String.valueOf(dia);
        mesS = String.valueOf(mes);
        anioS = String.valueOf(anio);


        SimpleDateFormat h= new SimpleDateFormat("kk");
        horaS=h.format(date);
        hora=Integer.valueOf(horaS);

        SimpleDateFormat m= new SimpleDateFormat("mm");
        minutosS=m.format(date);
        SimpleDateFormat mm= new SimpleDateFormat("m");
        minutos=Integer.parseInt(mm.format(date));
        minutosa=String.valueOf(minutos);
        SimpleDateFormat s= new SimpleDateFormat("ss");
        segundosS=s.format(date);
        segundos=Integer.valueOf(segundosS);


        fecha = diaS+"/"+mesS+"/"+anioS;
        return fecha;
    }
    public String hora_actual_formato(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();

        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        mes= fecha_a.get(Calendar.MONTH)+1;
        anio= fecha_a.get(Calendar.YEAR);

        diaS= String.valueOf(dia);
        mesS = String.valueOf(mes);
        anioS = String.valueOf(anio);


        SimpleDateFormat h= new SimpleDateFormat("kk");
        horaS=h.format(date);
        hora=Integer.valueOf(horaS);

        SimpleDateFormat m= new SimpleDateFormat("mm");
        minutosS=m.format(date);
        SimpleDateFormat mm= new SimpleDateFormat("m");
        minutos=Integer.parseInt(mm.format(date));
        minutosa=String.valueOf(minutos);
        SimpleDateFormat s= new SimpleDateFormat("ss");
        segundosS=s.format(date);
        segundos=Integer.valueOf(segundosS);


        fecha = horaS+"/"+minutosS+"/"+segundosS;
        return fecha;
    }
    public String fechaYhora_actual(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();

        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        mes= fecha_a.get(Calendar.MONTH)+1;
        anio= fecha_a.get(Calendar.YEAR);

        diaS= String.valueOf(dia);
        mesS = String.valueOf(mes);
        anioS = String.valueOf(anio);


        SimpleDateFormat h= new SimpleDateFormat("kk");
        horaS=h.format(date);
        hora=Integer.valueOf(horaS);

        SimpleDateFormat m= new SimpleDateFormat("mm");
        minutosS=m.format(date);
        SimpleDateFormat mm= new SimpleDateFormat("m");
        minutos=Integer.parseInt(mm.format(date));
        minutosa=String.valueOf(minutos);
        SimpleDateFormat s= new SimpleDateFormat("ss");
        segundosS=s.format(date);
        segundos=Integer.valueOf(segundosS);


        fecha = diaS+"/"+mesS+"/"+anioS+"/"+horaS+"/"+minutosS+"/"+segundosS;
        return fecha;
    }
    public int anio_actual(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();
        anio= fecha_a.get(Calendar.YEAR);
        return anio;
    }
    public int mes_actual(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();
        mes= fecha_a.get(Calendar.MONTH)+1;
        return mes;
    }
    public int dia_actual(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();
        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        return dia;
    }
    public int hora_actual(){
        Date date = new Date();
        SimpleDateFormat hh = new SimpleDateFormat("k");
        hora=Integer.parseInt(hh.format(date));
        return hora;
    }
    public int minuto_actual(){
        Date date = new Date();
        SimpleDateFormat mm= new SimpleDateFormat("m");
        minutos=Integer.parseInt(mm.format(date));
        return minutos;
    }
    public int segundo_actual(){
        Date date = new Date();
        SimpleDateFormat ss= new SimpleDateFormat("s");
        segundos=Integer.parseInt(ss.format(date));
        return segundos;
    }
    public String caducidad(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();

        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        mes= fecha_a.get(Calendar.MONTH)+1;
        anio= fecha_a.get(Calendar.YEAR);


        SimpleDateFormat hh = new SimpleDateFormat("k");
        hora=Integer.parseInt(hh.format(date));

        SimpleDateFormat mm= new SimpleDateFormat("m");
        minutos=Integer.parseInt(mm.format(date));

        SimpleDateFormat ss= new SimpleDateFormat("s");
        segundos=Integer.parseInt(ss.format(date));

        if ((anio%4)==0){
            if (mes == 1){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 2){
                if ((dia <= 27) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 28){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 3){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 4){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 5){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 6){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 7){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 8){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 9){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 10){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 11){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 12){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=1;
                    dia=1;
                    anio=anio+1;
                }
                else if (dia == 31){
                    mes=1;
                    dia=2;
                    anio=anio+1;
                }
            }
        }
        else {
            if (mes == 1){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 2){
                if ((dia <= 26) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 27){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 28){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 3){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 4){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 5){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 6){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 7){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 8){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 9){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 10){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 31){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 11){
                if ((dia <= 28) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 29){
                    mes=mes+1;
                    dia=1;
                }
                else if (dia == 30){
                    mes=mes+1;
                    dia=2;
                }
            }
            else if (mes == 12){
                if ((dia <= 29) && (dia >= 1)){
                    dia=dia+2;
                }
                else if (dia == 30){
                    mes=1;
                    dia=1;
                    anio=anio+1;
                }
                else if (dia == 31){
                    mes=1;
                    dia=2;
                    anio=anio+1;
                }
            }
        }


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);

        String fecha_caducidad=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_caducidad;

    }
    /*prestamos*/
    public String mes1_inicio(){
        String aux_fecha=caducidad();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");

        if ((dia<=10)||(dia>=1)){
            if (mes==12){
                mes=1;
                anio=anio+1;
            }else {
                mes=mes+1;
            }
            dia=5;
        }else {
            if (mes==11){
                mes=1;
                anio=anio+1;
            }else if (mes==12){
                mes=2;
                anio=anio+1;
            }else {
                mes=mes+2;
            }
            dia=5;
        }


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes1_fin(){
        String aux_fecha=mes1_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");


            if (mes == 12) {
                mes = 1;
                anio = anio + 1;
            } else {
                mes = mes + 1;
            }
            dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes2_inicio(){
        String aux_fecha=mes1_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes2_fin(){
        String aux_fecha=mes2_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");


        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes3_inicio(){
        String aux_fecha=mes2_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes3_fin(){
        String aux_fecha=mes3_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes4_inicio(){
        String aux_fecha=mes3_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes4_fin(){
        String aux_fecha=mes4_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");


        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes5_inicio(){
        String aux_fecha=mes4_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes5_fin(){
        String aux_fecha=mes5_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes6_inicio(){
        String aux_fecha=mes5_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes6_fin(){
        String aux_fecha=mes6_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes7_inicio(){
        String aux_fecha=mes6_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes7_fin(){
        String aux_fecha=mes7_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes8_inicio(){
        String aux_fecha=mes7_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes8_fin(){
        String aux_fecha=mes8_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes9_inicio(){
        String aux_fecha=mes8_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes9_fin(){
        String aux_fecha=mes9_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");


        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes10_inicio(){
        String aux_fecha=mes9_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes10_fin(){
        String aux_fecha=mes10_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes11_inicio(){
        String aux_fecha=mes10_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes11_fin(){
        String aux_fecha=mes11_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes12_inicio(){
        String aux_fecha=mes11_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes12_fin(){
        String aux_fecha=mes12_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes13_inicio(){
        String aux_fecha=mes12_fin();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("1");



        dia=5;



        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String mes13_fin(){
        String aux_fecha=mes13_inicio();
        String [] f=aux_fecha.split("/");

        int anio = Integer.parseInt(f[2]);
        int mes = Integer.parseInt(f[1]);
        int dia = Integer.parseInt(f[0]);
        int hora = Integer.parseInt("0");
        int minutos = Integer.parseInt("0");
        int segundos = Integer.parseInt("0");



        if (mes == 12) {
            mes = 1;
            anio = anio + 1;
        } else {
            mes = mes + 1;
        }
        dia = 5;


        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);



        String fecha_mes=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_mes;
    }
    public String extremos_ini(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();

        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        mes= fecha_a.get(Calendar.MONTH)+1;
        anio= fecha_a.get(Calendar.YEAR);

        hora=0;
        minutos=0;
        segundos=1;

        if (dia==5){
            dia= fecha_a.get(Calendar.DAY_OF_MONTH);
            mes= fecha_a.get(Calendar.MONTH)+1;
            anio= fecha_a.get(Calendar.YEAR);
        }

        else if (dia<5){
            if (mes==1){
                dia=5;
                mes=12;
                anio=anio-1;

            }else{
                dia=5;
                mes=mes-1;
                anio= fecha_a.get(Calendar.YEAR);
            }
        }

        else if(dia>5){
            dia=5;
            mes= fecha_a.get(Calendar.MONTH)+1;
            anio= fecha_a.get(Calendar.YEAR);
        }

        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);

        String fecha_caducidad=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_caducidad;

    }
    public String extremos_fin(){
        Calendar fecha_a = Calendar.getInstance();
        Date date = new Date();

        dia= fecha_a.get(Calendar.DAY_OF_MONTH);
        mes= fecha_a.get(Calendar.MONTH)+1;
        anio= fecha_a.get(Calendar.YEAR);

        hora=0;
        minutos=0;
        segundos=0;

        if (dia==5){
            if (mes==12){

                dia= fecha_a.get(Calendar.DAY_OF_MONTH);
                mes= 1;
                anio= anio+1;
            }
            else {
                dia= fecha_a.get(Calendar.DAY_OF_MONTH);
                mes= mes+1;
                anio= fecha_a.get(Calendar.YEAR);
            }

        }

        else if (dia<5){
                dia=5;
                mes= fecha_a.get(Calendar.MONTH)+1;
                anio= fecha_a.get(Calendar.YEAR);

        }

        else if(dia>5){
            if (mes==12){

                dia= 5;
                mes= 1;
                anio= anio+1;
            }
            else {
                dia= 5;
                mes= mes+1;
                anio= fecha_a.get(Calendar.YEAR);
            }
        }

        String aaaa= String.valueOf(anio);
        String mmmm= String.valueOf(mes);
        String dddd= String.valueOf(dia);
        String hhhh= String.valueOf(hora);
        String mnt= String.valueOf(minutos);
        String ssss= String.valueOf(segundos);

        String fecha_caducidad=dddd+"/"+mmmm+"/"+aaaa+"/"+hhhh+"/"+mnt+"/"+ssss;
        return fecha_caducidad;

    }




}
