package com.example.contactsapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contactsapp.databinding.ActivityAddNewContactActivtityBinding;


public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactActivtityBinding binding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact_activtity);

        contacts = new Contacts();

        binding= DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_new_contact_activtity
        );


        // VIew Model
        MyViewModel myViewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);


        handler = new AddNewContactClickHandler(
                contacts,
                this,
                myViewModel
        );

        binding.setContact(contacts);
        binding.setClickHandler(handler);








    }


}