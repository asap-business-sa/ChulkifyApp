package com.example.chulkify.transacciones_pg.reportes;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class adapter_histo_tran extends FragmentPagerAdapter {

    public adapter_histo_tran(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Fragment_histo_ap();
        }else if (position==1){
            return new Fragment_histo_rt();
        }else if (position==2){
            return new Fragment_histo_tran();
        }
        return null;
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Aportes";
        }else if (position==1){
            return "Retiros";
        }else if(position==2){
            return "H. General";
        }
        return null;
    }
}

