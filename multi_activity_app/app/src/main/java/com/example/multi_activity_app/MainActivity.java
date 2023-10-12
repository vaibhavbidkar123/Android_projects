package com.example.multi_activity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.open_browser);
        Button btn2=findViewById((R.id.open_new_activity));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    open_browser();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_new_activity();
            }
        });
    }

    public void open_browser(){


        Intent implicit=new Intent(Intent.ACTION_VIEW);
        implicit.setData(Uri.parse("https://www.Google.com"));
        startActivity(implicit);

    }
    public  void  open_new_activity(){

        Intent explicit=new Intent(this,Activity2.class);
        startActivity(explicit);


    }
}