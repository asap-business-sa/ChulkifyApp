package com.example.chulkify.nueva_comunidad;

import android.os.Parcel;
import android.os.Parcelable;

public class cargar_comunidad implements Parcelable {
    private int id;
    private String nombre;
    private String codigo;
    private double depositos_t;
    private double comiciones_t;
    private String admin;
    private  int t_usuarios;
    private String ciudad;
    private String f_inicio;

    public cargar_comunidad() {
    }
    public cargar_comunidad(int id, String nombres, String codigo, double comiciones_t, double depositos_t, String admin, int t_usuarios,String ciudad, String f_inicio) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.depositos_t = depositos_t;
        this.comiciones_t = comiciones_t;
        this.admin = admin;
        this.t_usuarios = t_usuarios;
        this.ciudad = ciudad;
        this.f_inicio = f_inicio;
    }
    //Getter

    public int getId() {  return id; }
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo;  }
    public Double getDepositos_t() { return depositos_t; }
    public Double getComiciones_t() { return comiciones_t; }
    public String getAdmin() { return admin; }
    public int getT_usuarios() {return t_usuarios;}
    public String getCiudad() { return  ciudad; }
    public String getF_inicio() { return f_inicio;}


    //SETTER
    public void setId(int id) { this.id = id;  }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setDepositos_t(Double  depositos_t) { this.depositos_t = depositos_t; }
    public void setComiciones_t(Double comiciones_t) { this.comiciones_t = comiciones_t;  }
    public void setAdmin(String admin) { this.admin = admin; }
    public void setT_usuarios(int t_usuarios) { this.t_usuarios = t_usuarios;}
    public void setCiudad(String ciudad) { this.ciudad = ciudad;}
    public void setF_inicio(String f_inicio) { this.f_inicio = f_inicio;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.codigo);
        dest.writeDouble(this.depositos_t);
        dest.writeDouble(this.comiciones_t);
        dest.writeString(this.admin);
        dest.writeInt(this.t_usuarios);
        dest.writeString(this.ciudad);
        dest.writeString(this.f_inicio);
    }

    protected cargar_comunidad(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.codigo = in.readString();
        this.depositos_t = in.readDouble();
        this.comiciones_t = in.readDouble();
        this.admin = in.readString();
        this.t_usuarios = in.readInt();
        this.ciudad = in.readString();
        this.f_inicio = in.readString();
    }

    public static final Parcelable.Creator<cargar_comunidad> CREATOR = new Parcelable.Creator<cargar_comunidad>() {
        @Override
        public cargar_comunidad createFromParcel(Parcel source) {
            return new cargar_comunidad(source);
        }

        @Override
        public cargar_comunidad[] newArray(int size) {
            return new cargar_comunidad[size];
        }
    };
}
