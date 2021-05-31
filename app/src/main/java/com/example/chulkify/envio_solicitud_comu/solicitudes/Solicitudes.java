package com.example.chulkify.envio_solicitud_comu.solicitudes;

public class Solicitudes {
    private int id_notif;
    private String ci_emi_notif;
    private String ci_recep_notif;
    private String estado_notif;
    private String estado_g_notif;
    private String tipo_notif;
    private String fecha_crea_notif;
    private String fecha_cadu_notif;
    private String nombre_us;
    private String apellido_us;
    private String usuario_us;
    private String tipo_s;
    private String valor_p;
    private String plazo_p;
    private String msj1;
    private String msj2;


    public Solicitudes(){}


    public Solicitudes(int id_notif, String ci_emi_notif, String ci_recep_notif, String estado_notif, String estado_g_notif, String tipo_notif, String fecha_crea_notif, String fecha_cadu_notif, String nombre_us, String apellido_us, String usuario_us, String tipo_s, String valor_p, String plazo_p, String msj1, String msj2) {
        this.id_notif = id_notif;
        this.ci_emi_notif = ci_emi_notif;
        this.ci_recep_notif = ci_recep_notif;
        this.estado_notif = estado_notif;
        this.estado_g_notif = estado_g_notif;
        this.tipo_notif = tipo_notif;
        this.fecha_crea_notif = fecha_crea_notif;
        this.fecha_cadu_notif = fecha_cadu_notif;
        this.nombre_us = nombre_us;
        this.apellido_us = apellido_us;
        this.usuario_us = usuario_us;
        this.tipo_s = tipo_s;
        this.valor_p = valor_p;
        this.plazo_p = plazo_p;
        this.msj1 = msj1;
        this.msj2 = msj2;
    }

    public String getMsj1() {
        return msj1;
    }

    public void setMsj1(String msj1) {
        this.msj1 = msj1;
    }

    public String getMsj2() {
        return msj2;
    }

    public void setMsj2(String msj2) {
        this.msj2 = msj2;
    }

    public String getTipo_s() {
        return tipo_s;
    }

    public void setTipo_s(String tipo_s) {
        this.tipo_s = tipo_s;
    }

    public String getValor_p() {
        return valor_p;
    }

    public void setValor_p(String valor_p) {
        this.valor_p = valor_p;
    }

    public String getPlazo_p() {
        return plazo_p;
    }

    public void setPlazo_p(String plazo_p) {
        this.plazo_p = plazo_p;
    }

    public int getId_notif() {
        return id_notif;
    }

    public String getCi_emi_notif() {
        return ci_emi_notif;
    }

    public String getCi_recep_notif() {
        return ci_recep_notif;
    }

    public String getEstado_notif() {
        return estado_notif;
    }

    public String getEstado_g_notif() {
        return estado_g_notif;
    }

    public String getTipo_notif() {
        return tipo_notif;
    }

    public String getFecha_crea_notif() {
        return fecha_crea_notif;
    }

    public String getFecha_cadu_notif() {
        return fecha_cadu_notif;
    }

    public String getNombre_us() {
        return nombre_us;
    }

    public String getApellido_us() {
        return apellido_us;
    }

    public String getUsuario_us() {
        return usuario_us;
    }

    public void setId_notif(int id_notif) {
        this.id_notif = id_notif;
    }

    public void setCi_emi_notif(String ci_emi_notif) {
        this.ci_emi_notif = ci_emi_notif;
    }

    public void setCi_recep_notif(String ci_recep_notif) {
        this.ci_recep_notif = ci_recep_notif;
    }

    public void setEstado_notif(String estado_notif) {
        this.estado_notif = estado_notif;
    }

    public void setEstado_g_notif(String estado_g_notif) {
        this.estado_g_notif = estado_g_notif;
    }

    public void setTipo_notif(String tipo_notif) {
        this.tipo_notif = tipo_notif;
    }

    public void setFecha_crea_notif(String fecha_crea_notif) {
        this.fecha_crea_notif = fecha_crea_notif;
    }

    public void setFecha_cadu_notif(String fecha_cadu_notif) {
        this.fecha_cadu_notif = fecha_cadu_notif;
    }

    public void setNombre_us(String nombre_us) {
        this.nombre_us = nombre_us;
    }

    public void setApellido_us(String apellido_us) {
        this.apellido_us = apellido_us;
    }

    public void setUsuario_us(String usuario_us) {
        this.usuario_us = usuario_us;
    }
}