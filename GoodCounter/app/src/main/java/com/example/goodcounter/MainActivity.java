package com.example.goodcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.goodcounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        viewModel=new ViewModelProvider(this).get(MyViewModel.class);

        mainBinding.setMyviewmodel(viewModel);

        viewModel.getValue().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer counter) {
                mainBinding.text.setText(""+counter);
            }
        });





    }
}