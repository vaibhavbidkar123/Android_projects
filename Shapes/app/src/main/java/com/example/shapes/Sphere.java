package com.example.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class Sphere extends AppCompatActivity {

    int radius;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphere);

        EditText e=findViewById(R.id.edittext);
        Button a=findViewById(R.id.area);
        Button v=findViewById(R.id.volume);
        TextView r=findViewById(R.id.result);




        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=e.getText().toString();
                radius=Integer.parseInt(value);
                r.setText("Area : "+((4)*(Math.PI)*radius*radius));

            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=e.getText().toString();
                radius=Integer.parseInt(value);
                r.setText("Volume : "+((4/3)*(Math.PI)*radius*radius*radius));
            }
        });

    }
}