package com.example.chulkify.envio_solicitud_comu.solicitudes;

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

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.solicitudesholder> {

    List<Solicitudes> listSolicitudes;
    Context context;
    Fragment_soli f;


    public SolicitudesAdapter(List<Solicitudes> listSolicitudes, Context context, Fragment_soli f){
        this.listSolicitudes = listSolicitudes;
        this.context = context;
        this.f = f;
    }

    @NonNull
    @Override
    public solicitudesholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_solicitudes,parent,false);
        return new SolicitudesAdapter.solicitudesholder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull solicitudesholder holder, final int position) {
        holder.nonbre_us.setText(listSolicitudes.get(position).getNombre_us());
        holder.apellido_us.setText(listSolicitudes.get(position).getApellido_us());
        holder.nombre_lis.setText(listSolicitudes.get(position).getUsuario_us());
        holder.fecha_crea.setText(listSolicitudes.get(position).getFecha_crea_notif());
        holder.identificador=listSolicitudes.get(position).getId_notif();
        holder.plazo_prestar.setText(listSolicitudes.get(position).getPlazo_p());
        holder.valor_prestar.setText(listSolicitudes.get(position).getValor_p());
        holder.tipo_solicitud.setText(listSolicitudes.get(position).getTipo_s());
        holder.msj1.setText(listSolicitudes.get(position).getMsj1());
        holder.msj2.setText(listSolicitudes.get(position).getMsj2());

        holder.cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.cancelarSolicitud(listSolicitudes.get(position).getId_notif(),listSolicitudes.get(position).getUsuario_us());
            }
        });

        holder.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.aceptarSolicitud(listSolicitudes.get(position).getId_notif(),listSolicitudes.get(position).getUsuario_us() );
            }
        });




    }

    @Override
    public int getItemCount() {
        return listSolicitudes.size();
    }



    public  class solicitudesholder extends RecyclerView.ViewHolder{
        private TextView nombre_lis,fecha_crea,nonbre_us,apellido_us, tipo_solicitud, valor_prestar, plazo_prestar, msj1, msj2;
        private Button aceptar;
        private Button cancelar;
        private CardView cardView;
        private int identificador=5;

        public solicitudesholder(@NonNull View itemView) {
            super(itemView);
            nonbre_us = (TextView) itemView.findViewById(R.id.txt_n_nombre_soli);
            apellido_us = (TextView) itemView.findViewById(R.id.txt_a_nombre_soli);
            nombre_lis = (TextView) itemView.findViewById(R.id.txt_nombre_soli);
            fecha_crea = (TextView) itemView.findViewById(R.id.txt_n_fecha_soli);
            tipo_solicitud = (TextView) itemView.findViewById(R.id.txt_tipo_solicitud);
            valor_prestar = (TextView) itemView.findViewById(R.id.txt_v_prestar);
            plazo_prestar = (TextView) itemView.findViewById(R.id.txt_p_prestar);
            aceptar =(Button) itemView.findViewById(R.id.btn_acep);
            cancelar=(Button) itemView.findViewById(R.id.btn_rech);
            msj1 =(TextView) itemView.findViewById(R.id.txt_v_pre);
            msj2 =(TextView) itemView.findViewById(R.id.txt_v_pre2);


        }
    }

}
