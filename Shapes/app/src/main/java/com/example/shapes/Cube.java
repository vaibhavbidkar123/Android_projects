package com.example.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cube extends AppCompatActivity {

    String value;
    int side;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube);
        EditText e=findViewById(R.id.edittext);
        Button a=findViewById(R.id.area);
        Button v=findViewById(R.id.volume);
        TextView r=findViewById(R.id.result);




        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=e.getText().toString();
                side=Integer.parseInt(value);
                r.setText("Surface Area : "+6*side*side);

            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=e.getText().toString();
                side=Integer.parseInt(value);
                r.setText("Volume : "+side*side*side);
            }
        });
    }
}