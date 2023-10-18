package com.example.playerslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Players> playerslist;

    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        playerslist=new ArrayList<>();

        Players p1=new Players(R.drawable.gill,"Shubhman Gill","Batsman");
        Players p2=new Players(R.drawable.rohit,"Rohit Sharma","Batsman");
        Players p3=new Players(R.drawable.kohli,"Virat Kohli","Batsman");
        Players p4=new Players(R.drawable.iyer,"Shyreyas Iyer","Batsman");
        Players p5=new Players(R.drawable.rahul,"KL Rahul","Wicket-Keeper");
        Players p6=new Players(R.drawable.pandya,"Hardik Pandya","All Rounder");
        Players p7=new Players(R.drawable.jadeja,"Ravindra Jadeja","All Rounder");
        Players p8=new Players(R.drawable.shardul,"Shardul Thakur","All Rounder");
        Players p9=new Players(R.drawable.kuldeep,"Kuldeep Yadav","Bowler");
        Players p10=new Players(R.drawable.siraj,"Md Siraj","Bowler");
        Players p11=new Players(R.drawable.bumrah,"Jasprit Bumrah","Bowler");

        playerslist.add(p1);
        playerslist.add(p2);
        playerslist.add(p3);
        playerslist.add(p4);
        playerslist.add(p5);
        playerslist.add(p6);
        playerslist.add(p7);
        playerslist.add(p8);
        playerslist.add(p9);
        playerslist.add(p10);
        playerslist.add(p11);




        myAdapter = new MyAdapter(playerslist);
        recyclerView.setAdapter(myAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




//        myAdapter.setClickListener(this);








    }
}