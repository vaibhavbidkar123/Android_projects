package com.example.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view){
        Intent i= new Intent(view.getContext(),addNewContactActivtity.class);
        context.startActivity(i);
    }
}
