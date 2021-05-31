package com.example.chulkify.envio_solicitud_comu.solicitudes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.chulkify.R;
import com.google.android.material.tabs.TabLayout;

public class Soli_notifi extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes_noty);

        tabLayout= (TabLayout) findViewById(R.id.tb_layout_soli);
        viewPager= (ViewPager) findViewById(R.id.view_pager_soli);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new Adapter_soli_noty(getSupportFragmentManager()));
    }
}