package com.example.chulkify.super_usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.chulkify.R;
import com.example.chulkify.envio_solicitud_comu.solicitudes.Adapter_soli_noty;
import com.google.android.material.tabs.TabLayout;

public class listar_usuarios_Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        tabLayout= (TabLayout) findViewById(R.id.tb_layout_soli);
        viewPager= (ViewPager) findViewById(R.id.view_pager_soli);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new adapter_list_usu(getSupportFragmentManager()));
    }
}