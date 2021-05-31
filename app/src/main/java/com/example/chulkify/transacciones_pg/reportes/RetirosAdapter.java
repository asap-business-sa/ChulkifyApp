package com.example.chulkify.transacciones_pg.reportes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chulkify.R;

import java.util.List;

public class RetirosAdapter extends RecyclerView.Adapter<RetirosAdapter.transaccionesholder>  {


    List<Transacciones> listtransacciones;
    Context context;
    Fragment_histo_rt f;

    public RetirosAdapter(List<Transacciones> listtransacciones, Context context, Fragment_histo_rt f){
        this.listtransacciones = listtransacciones;
        this.context = context;
        this.f = f;
    }



    @NonNull
    @Override
    public transaccionesholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_transaccion,parent,false);
        return new RetirosAdapter.transaccionesholder(v);


    }


    @Override
    public void onBindViewHolder(@NonNull RetirosAdapter.transaccionesholder holder, final int position) {
        holder.tipo_tran.setText(listtransacciones.get(position).getTipo_tran());
        holder.valor_tran.setText(listtransacciones.get(position).getValor_tran());
        holder.fecha_tran.setText(listtransacciones.get(position).getFecha_tran());
        holder.hora_tran.setText(listtransacciones.get(position).getHora_tran());
        /*if ((listtransacciones.get(position).getTipo_tran())=="+"){
            holder.tipo_tran.setTextColor(Integer.parseInt("@color/verde"));
        }else if ((listtransacciones.get(position).getTipo_tran())=="-"){
            holder.tipo_tran.setTextColor(Integer.parseInt("@color/rojo"));
        }

         */


    }

    @Override
    public int getItemCount() {
        return listtransacciones.size();
    }

    public  class transaccionesholder extends RecyclerView.ViewHolder{
        private TextView tipo_tran, valor_tran, fecha_tran, hora_tran;
        private CardView cardView;

        public transaccionesholder(@NonNull View itemView) {
            super(itemView);
            tipo_tran= (TextView) itemView.findViewById(R.id.txt_tipo_tran);
            valor_tran= (TextView) itemView.findViewById(R.id.txt_val_tran);
            fecha_tran= (TextView) itemView.findViewById(R.id.txt_fecha_tran);
            hora_tran= (TextView) itemView.findViewById(R.id.txt_hora_tran);




        }
    }


}

