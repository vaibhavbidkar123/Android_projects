package com.example.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cylinder extends AppCompatActivity {

    int radius;
    String value1,value2;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder);


        EditText e=findViewById(R.id.edittext);
        EditText e1=findViewById(R.id.edittext2);
        Button a=findViewById(R.id.area);
        Button v=findViewById(R.id.volume);
        TextView r=findViewById(R.id.result);




        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1=e.getText().toString();
                value2=e1.getText().toString();
                radius=Integer.parseInt(value1);
                height=Integer.parseInt(value2);
                r.setText("Area : "+( (2*Math.PI*radius*height) + (2*Math.PI*radius*radius)) );

            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1=e.getText().toString();
                value2=e1.getText().toString();
                radius=Integer.parseInt(value1);
                height=Integer.parseInt(value2);
                r.setText("Volume : "+(Math.PI*radius*radius*height));
            }
        });

    }
}