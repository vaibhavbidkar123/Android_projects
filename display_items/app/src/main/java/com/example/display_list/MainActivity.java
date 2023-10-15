package com.example.display_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of strings (example data)
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("vaibhav");
        dataList.add("sachin");
        dataList.add("ruth");

        // Initialize the ListView
        ListView listView = findViewById(R.id.listView);

        // Create and set the custom adapter
        CustomAdapter customAdapter = new CustomAdapter(this, dataList);
        listView.setAdapter(customAdapter);
    }
}