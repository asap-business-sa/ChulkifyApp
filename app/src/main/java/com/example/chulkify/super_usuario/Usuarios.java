package com.example.chulkify.super_usuario;

public class Usuarios {
    private int id_us;
    private String cedula_us;
    private String usuario_us;
    private String nombre_us;
    private String apellido_us;
    private String tipo_us;
    private String fondos_us;
    private String grupo_us;
    private String estado_grupo;
    private String direccion;
    private String correo;
    private String telefono;


    public  Usuarios(){}


    public Usuarios(int id_us,String Correo, String cedula_us, String usuario_us, String nombre_us, String apellido_us, String tipo_us, String fondos_us, String grupo_us, String estado_grupo, String direccion, String telefono) {
        this.id_us = id_us;
        this.cedula_us = cedula_us;
        this.usuario_us = usuario_us;
        this.nombre_us = nombre_us;
        this.apellido_us = apellido_us;
        this.tipo_us = tipo_us;
        this.fondos_us = fondos_us;
        this.grupo_us = grupo_us;
        this.estado_grupo = estado_grupo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_us() {
        return id_us;
    }

    public void setId_us(int id_us) {
        this.id_us = id_us;
    }

    public String getCedula_us() {
        return cedula_us;
    }

    public void setCedula_us(String cedula_us) {
        this.cedula_us = cedula_us;
    }

    public String getUsuario_us() {
        return usuario_us;
    }

    public void setUsuario_us(String usuario_us) {
        this.usuario_us = usuario_us;
    }

    public String getNombre_us() {
        return nombre_us;
    }

    public void setNombre_us(String nombre_us) {
        this.nombre_us = nombre_us;
    }

    public String getApellido_us() {
        return apellido_us;
    }

    public void setApellido_us(String apellido_us) {
        this.apellido_us = apellido_us;
    }

    public String getTipo_us() {
        return tipo_us;
    }

    public void setTipo_us(String tipo_us) {
        this.tipo_us = tipo_us;
    }

    public String getFondos_us() {
        return fondos_us;
    }

    public void setFondos_us(String fondos_us) {
        this.fondos_us = fondos_us;
    }

    public String getGrupo_us() {
        return grupo_us;
    }

    public void setGrupo_us(String grupo_us) {
        this.grupo_us = grupo_us;
    }

    public String getEstado_grupo() {
        return estado_grupo;
    }

    public void setEstado_grupo(String estado_grupo) {
        this.estado_grupo = estado_grupo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
