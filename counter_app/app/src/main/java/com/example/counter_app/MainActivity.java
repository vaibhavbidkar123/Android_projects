package com.example.counter_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add=findViewById(R.id.add_btn);
        Button sub=findViewById(R.id.sub_btn);
        TextView number=findViewById(R.id.textView);


        number.setText(""+counter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                number.setText(""+counter);


            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter--;
                number.setText(""+counter);
            }
        });

    }
}