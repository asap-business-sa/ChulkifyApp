package com.example.chulkify.login;

import android.os.Parcel;
import android.os.Parcelable;

public class Logear_usuario implements Parcelable {
    private int id;
    private String Ci;
    private String nombres;
    private String apellidos;
    private String nombre_usuario;
    private String correo;
    private String password;
    private String direccion, telefono, ciudad;
    private Double fondos;
    private  int grupo, estado_grupo, id_cuenta, tipo, aportes;
    private String f_inicio;
    private String f_union;


    public Logear_usuario() {
    }
    public Logear_usuario(int id, String ci, String nombres, String apellidos, String nombre_usuario, String correo, String password, Double fondos, int grupo, int estado_grupo, int id_cuenta, int tipo, String direccion, String telefono, String ciudad, String f_inicio, int aportes, String f_union) {
        this.id = id;
        this.Ci = Ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombre_usuario = nombre_usuario;
        this.correo = correo;
        this.password = password;
        this.fondos = fondos;
        this.grupo = grupo;
        this.estado_grupo = estado_grupo;
        this.id_cuenta = id_cuenta;
        this.tipo = tipo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.f_inicio = f_inicio;
        this.f_inicio = f_union;
        this.aportes = aportes;
    }
    //Getter

    public int getId() {  return id; }
    public String getCi() {  return Ci; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getNombre_usuario() { return nombre_usuario; }
    public String getCorreo() { return correo;  }
    public String getPassword() { return password; }
    public Double getFondos() { return fondos; }
    public int getGrupo() { return grupo; }
    public int getEstado_grupo() { return estado_grupo; }
    public int getId_cuenta() { return id_cuenta; }
    public int getTipo() {  return id_cuenta; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCiudad() { return  ciudad; }
    public String getF_inicio() { return f_inicio;}
    public String getF_union() { return f_union;}
    public int getAportes() { return aportes; }


    //SETTER
    public void setId(int id) { this.id = id;  }
    public void setCi(String Ci) { this.Ci = Ci; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setPassword(String password) { this.password = password; }
    public void setFondos(Double fondos) { this.fondos = fondos;  }
    public void setGrupo(int grupo) { this.grupo = grupo;  }
    public void setEstado_grupo(int estado_grupo) { this.estado_grupo = estado_grupo;  }
    public void setId_cuenta(int id_cuenta) { this.id_cuenta = id_cuenta; }
    public void setTipo(int tipo) { this.tipo = tipo;  }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono;}
    public void setCiudad(String ciudad) { this.ciudad = ciudad;}
    public void setF_inicio(String f_inicio) { this.f_inicio = f_inicio;}
    public void setF_union(String f_union) { this.f_inicio = f_union;}
    public void setAportes(int aportes) {this.aportes = aportes; }






    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.Ci);
        dest.writeString(this.nombres);
        dest.writeString(this.apellidos);
        dest.writeString(this.nombre_usuario);
        dest.writeString(this.correo);
        dest.writeString(this.password);
        dest.writeString(this.direccion);
        dest.writeString(this.telefono);
        dest.writeString(this.ciudad);
        dest.writeValue(this.fondos);
        dest.writeInt(this.grupo);
        dest.writeInt(this.estado_grupo);
        dest.writeInt(this.id_cuenta);
        dest.writeInt(this.tipo);
        dest.writeInt(this.aportes);
        dest.writeSerializable(this.f_inicio);
        dest.writeSerializable(this.f_union);
    }

    protected Logear_usuario(Parcel in) {
        this.id = in.readInt();
        this.Ci = in.readString();
        this.nombres = in.readString();
        this.apellidos = in.readString();
        this.nombre_usuario = in.readString();
        this.correo = in.readString();
        this.password = in.readString();
        this.direccion = in.readString();
        this.telefono = in.readString();
        this.ciudad = in.readString();
        this.fondos = (Double) in.readValue(Double.class.getClassLoader());
        this.grupo = in.readInt();
        this.estado_grupo = in.readInt();
        this.id_cuenta = in.readInt();
        this.tipo = in.readInt();
        this.aportes = in.readInt();
        this.f_inicio = in.readString();
        this.f_union = in.readString();
    }

    public static final Parcelable.Creator<Logear_usuario> CREATOR = new Parcelable.Creator<Logear_usuario>() {
        @Override
        public Logear_usuario createFromParcel(Parcel source) {
            return new Logear_usuario(source);
        }

        @Override
        public Logear_usuario[] newArray(int size) {
            return new Logear_usuario[size];
        }
    };
    public void setNombre(String usuario_us) {
    }

    public void setContraseña(String contrasena_us) {
    }
}
