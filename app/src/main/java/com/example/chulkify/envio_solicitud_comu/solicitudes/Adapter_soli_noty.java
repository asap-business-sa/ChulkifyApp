package com.example.chulkify.envio_solicitud_comu.solicitudes;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chulkify.notificaciones.notificaciones_1;

public class Adapter_soli_noty extends FragmentPagerAdapter {

    public Adapter_soli_noty(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Fragment_soli();
        }else if (position==1){
            return new notificaciones_1();
        }
        return null;
    }

    @Override
    public int getCount() {

        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Solicitudes";
        }else if (position==1){
            return "Notificaciones";
        }
        return null;
    }
}
