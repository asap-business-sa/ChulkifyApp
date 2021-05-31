package com.example.chulkify.login.no_usados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.chulkify.R;
import com.google.android.material.tabs.TabLayout;

public class Activity_login extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        tabLayout= (TabLayout) findViewById(R.id.tb_layout_histo_tran);
        viewPager= (ViewPager) findViewById(R.id.view_pager_histo_tran);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new Adapter_login(getSupportFragmentManager()));


    }
}