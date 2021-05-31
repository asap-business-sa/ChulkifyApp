package com.example.chulkify.super_usuario;

public class Comunidades {
    private int id_comu;
    private String nombre_comu;
    private String codigo_comu;
    private String total_deposito;
    private String total_comisiones;
    private String admisntrador;
    private String total_usuario;
    private String ciudad;


    public Comunidades(){

    }

    public Comunidades(int id_comu, String nombre_comu, String codigo_comu, String total_deposito, String total_comisiones, String admisntrador, String total_usuario, String ciudad) {
        this.id_comu = id_comu;
        this.nombre_comu = nombre_comu;
        this.codigo_comu = codigo_comu;
        this.total_deposito = total_deposito;
        this.total_comisiones = total_comisiones;
        this.admisntrador = admisntrador;
        this.total_usuario = total_usuario;
        this.ciudad = ciudad;
    }

    public int getId_comu() {
        return id_comu;
    }

    public void setId_comu(int id_comu) {
        this.id_comu = id_comu;
    }

    public String getNombre_comu() {
        return nombre_comu;
    }

    public void setNombre_comu(String nombre_comu) {
        this.nombre_comu = nombre_comu;
    }

    public String getCodigo_comu() {
        return codigo_comu;
    }

    public void setCodigo_comu(String codigo_comu) {
        this.codigo_comu = codigo_comu;
    }

    public String getTotal_deposito() {
        return total_deposito;
    }

    public void setTotal_deposito(String total_deposito) {
        this.total_deposito = total_deposito;
    }

    public String getTotal_comisiones() {
        return total_comisiones;
    }

    public void setTotal_comisiones(String total_comisiones) {
        this.total_comisiones = total_comisiones;
    }

    public String getAdmisntrador() {
        return admisntrador;
    }

    public void setAdmisntrador(String admisntrador) {
        this.admisntrador = admisntrador;
    }

    public String getTotal_usuario() {
        return total_usuario;
    }

    public void setTotal_usuario(String total_usuario) {
        this.total_usuario = total_usuario;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
