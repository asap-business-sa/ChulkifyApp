package com.example.chulkify.login.no_usados;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chulkify.transacciones_pg.reportes.Fragment_histo_ap;
import com.example.chulkify.transacciones_pg.reportes.Fragment_histo_rt;

public class Adapter_login extends FragmentPagerAdapter {


    public Adapter_login(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Fragment_histo_ap();
        }else if (position==1){
            return new Fragment_histo_rt();
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
            return "Iniciar secion";
        }else if (position==1){
            return "Registrarse";
        }
        return null;
    }

}
