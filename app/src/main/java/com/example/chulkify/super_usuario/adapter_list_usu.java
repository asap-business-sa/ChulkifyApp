package com.example.chulkify.super_usuario;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chulkify.envio_solicitud_comu.solicitudes.Fragment_soli;
import com.example.chulkify.notificaciones.notificaciones_1;

public class adapter_list_usu  extends FragmentPagerAdapter {

    public adapter_list_usu(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new usuarios_Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {

        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Usuarios";
        }
        return null;
    }
}

