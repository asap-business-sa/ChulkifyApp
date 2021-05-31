package com.example.chulkify.transacciones_pg.reportes;

public class Transacciones {
    private int id_transaccion;
    private String tipo_tran;
    private String valor_tran;
    private String fecha_tran;
    private String hora_tran;

    public Transacciones(){}

    public Transacciones(int id_transaccion, String tipo_tran, String valor_tran, String fecha_tran, String hora_tran) {
        this.id_transaccion = id_transaccion;
        this.tipo_tran = tipo_tran;
        this.valor_tran = valor_tran;
        this.fecha_tran = fecha_tran;
        this.hora_tran = hora_tran;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public String getTipo_tran() {
        return tipo_tran;
    }

    public String getValor_tran() {
        return valor_tran;
    }

    public String getFecha_tran() {
        return fecha_tran;
    }

    public String getHora_tran() {
        return hora_tran;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public void setTipo_tran(String tipo_tran) {
        this.tipo_tran = tipo_tran;
    }

    public void setValor_tran(String valor_tran) {
        this.valor_tran = valor_tran;
    }

    public void setFecha_tran(String fecha_tran) {
        this.fecha_tran = fecha_tran;
    }

    public void setHora_tran(String hora_tran) {
        this.hora_tran = hora_tran;
    }
}
