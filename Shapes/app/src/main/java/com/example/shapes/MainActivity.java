package com.example.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView g1;
    ArrayList<Shape> dataList ;

    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        g1=findViewById(R.id.gridview);

        dataList =new ArrayList<>();

        Shape s1=new Shape(R.drawable.cube,"Cube");
        Shape s2=new Shape(R.drawable.cylinder,"Cylinder");
        Shape s3=new Shape(R.drawable.sphere,"Sphere");


        dataList.add(s1);
        dataList.add(s2);
        dataList.add(s3);


        adapter = new MyCustomAdapter(dataList, getApplicationContext());

        g1.setAdapter(adapter);
       g1.setNumColumns(2);


        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                Intent i = new Intent(getApplicationContext(), Sphere.class);
                startActivity(i);
                }if(position==1){
                    Intent i = new Intent(getApplicationContext(), Cylinder.class);
                    startActivity(i);
                }if(position==2){
                    Intent i = new Intent(getApplicationContext(), Cube.class);
                    startActivity(i);

                }
            }
        });




    }
}