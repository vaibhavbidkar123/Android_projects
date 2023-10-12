package com.example.guess_the_number;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int counter=0;
  //  EditText guess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Random number= new Random();
            int real_number=number.nextInt(100);
            String num=""+real_number;

        TextView t1=findViewById(R.id.textView4)    ;
        Button btn=findViewById(R.id.button);
        TextView t=findViewById(R.id.textView);
        EditText guess=findViewById(R.id.entry);

        t1.setText(""+real_number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        counter++;
                        if((guess.getText().toString()).equals(num)){
                        Intent i= new Intent(getApplicationContext(),Activity2.class);
                        i.putExtra("actual",num);
                        i.putExtra("tries",""+counter);
                        startActivity(i);
                        counter=0;
                        }else{
                            guess.setText("");
                            t.setText("Try Again");

                        }
            }
        });
    }
}





