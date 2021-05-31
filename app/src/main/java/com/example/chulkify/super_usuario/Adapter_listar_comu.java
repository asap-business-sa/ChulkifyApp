package com.example.chulkify.super_usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chulkify.R;

import java.util.List;

public class Adapter_listar_comu extends RecyclerView.Adapter<Adapter_listar_comu.comunidadholder> {

        List<Comunidades> listComunidades;
        Context context;
        ComunidadFragment f;


public Adapter_listar_comu(List<Comunidades> listComunidades, Context context, ComunidadFragment f){
        this.listComunidades = listComunidades;
        this.context = context;
        this.f = f;
        }

@NonNull
@Override
public comunidadholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wiew_list_comu,parent,false);
        return new comunidadholder(v);


        }

@Override
public void onBindViewHolder(@NonNull Adapter_listar_comu.comunidadholder holder, final int position) {

        holder.nombre_grupo.setText(listComunidades.get(position).getNombre_comu());
        holder.codigo_union.setText(listComunidades.get(position).getCodigo_comu());
        holder.administrador.setText(listComunidades.get(position).getAdmisntrador());
        holder.ciudad.setText(listComunidades.get(position).getCiudad());

        holder.identificador=listComunidades.get(position).getId_comu();
 /*
        holder.informacion.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        f.info(listComunidades.get(position).getId_comu());
        }
        });

*/
        }

@Override
public int getItemCount() {
        return listComunidades.size();
        }



public  class comunidadholder extends RecyclerView.ViewHolder{
    private TextView nombre_grupo, codigo_union, administrador, ciudad, total_usuario;
    private Button informacion;

    private CardView cardView;
    private int identificador=5;

    public comunidadholder(@NonNull View itemView) {
        super(itemView);
        nombre_grupo = (TextView) itemView.findViewById(R.id.txt_n_comu);
        codigo_union = (TextView) itemView.findViewById(R.id.txt_c_comu);
        administrador = (TextView) itemView.findViewById(R.id.txt_admin_comu);
        ciudad = (TextView) itemView.findViewById(R.id.txt_cd_comu);

        informacion =(Button) itemView.findViewById(R.id.btn_info);


    }
}

}
