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

public class adapter_listar_usu  extends RecyclerView.Adapter<adapter_listar_usu.usuariosholder> {

    List<Usuarios> listUsuarios;
    Context context;
    usuarios_Fragment f;


    public adapter_listar_usu(List<Usuarios> listUsuarios, Context context, usuarios_Fragment f){
        this.listUsuarios = listUsuarios;
        this.context = context;
        this.f = f;
    }

    @NonNull
    @Override
    public usuariosholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wiew_list_usuarios,parent,false);
        return new usuariosholder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull usuariosholder holder, final int position) {
        holder.n_usuario.setText(listUsuarios.get(position).getUsuario_us());
        holder.nombres.setText(listUsuarios.get(position).getNombre_us());
        holder.ci_us.setText(listUsuarios.get(position).getCedula_us());
        holder.correo.setText(listUsuarios.get(position).getCorreo());
        holder.telefono.setText(listUsuarios.get(position).getTelefono());
        holder.grupo.setText(listUsuarios.get(position).getGrupo_us());
        holder.identificador=listUsuarios.get(position).getId_us();
/*
        holder.informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.info(listUsuarios.get(position).getUsuario_us());
            }
        });

 */




    }

    @Override
    public int getItemCount() {
        return listUsuarios.size();
    }



    public  class usuariosholder extends RecyclerView.ViewHolder{
        private TextView n_usuario, nombres, ci_us, correo, telefono, grupo;
        private Button informacion;

        private CardView cardView;
        private int identificador=5;

        public usuariosholder(@NonNull View itemView) {
            super(itemView);
            n_usuario = (TextView) itemView.findViewById(R.id.txt_sus_usuario);
            nombres = (TextView) itemView.findViewById(R.id.txt_sus_nom_ape);
            ci_us = (TextView) itemView.findViewById(R.id.txt_sus_ci);
            correo = (TextView) itemView.findViewById(R.id.txt_sus_correo);
            telefono = (TextView) itemView.findViewById(R.id.txt_sus_telefono);
            grupo = (TextView) itemView.findViewById(R.id.txt_sus_grupo);
            informacion=(Button) itemView.findViewById(R.id.btn_info);



        }
    }

}
