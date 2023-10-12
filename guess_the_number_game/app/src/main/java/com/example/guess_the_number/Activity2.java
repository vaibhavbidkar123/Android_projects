package com.example.guess_the_number;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button share=findViewById(R.id.button2);
        TextView text=findViewById(R.id.textView2);

        String msg="yess its :"+getIntent().getStringExtra("actual")+"\nYou Took "+getIntent().getStringExtra("tries")+" tries";
        text.setText(msg);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               share(msg);
            }
        });

    }
    public void share(String msg){

        Intent i=new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(Intent.createChooser(i, "Share text via..."));

    }
}