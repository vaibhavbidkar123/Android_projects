package com.example.fragments_advanced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewpager;
    TabLayout tabLayout;
    MyViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabs);
        viewpager=findViewById(R.id.viewpager);

        adapter=new MyViewPageAdapter(getSupportFragmentManager(),getLifecycle());

        adapter.addFragment(new fragment1());
        adapter.addFragment(new fragment2());
        adapter.addFragment(new fragment3());

        viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        viewpager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,viewpager,new TabLayoutMediator.TabConfigurationStrategy(){

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("fragment "+(position+1));
            }
        }).attach();

    }
}