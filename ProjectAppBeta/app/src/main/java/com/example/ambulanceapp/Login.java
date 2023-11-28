package com.example.ambulanceapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// LoginActivity.java
public class Login extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextName = findViewById(R.id.NameEditText);
        editTextPassword = findViewById(R.id.PasswordEditText);

        userRepository = new UserRepository(userDatabase.getInstance(getApplicationContext()).userDao());

        Button loginButton;
        loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void login() {
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Use AsyncTask to perform database operation in the background
        new AsyncTask<Void, Void, Users>() {
            @Override
            protected Users doInBackground(Void... voids) {
                // Retrieve the user from the database based on the entered credentials
                return userRepository.login(name, password);
            }

            @Override
            protected void onPostExecute(Users user) {
                super.onPostExecute(user);

                // Check if login was successful
                if((name.equals("admin")|| name.equals("Admin")) && password.equals("1234")){

                    // Navigate to the next activity (assuming NextActivity.class is the name of your next activity)
                    Intent intent = new Intent(Login.this, MainActivity2.class);
                    startActivity(intent);
                }else if (user != null) {
                    // Login successful
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // You can add code here to navigate to the main activity or perform other actions
                } else {
                    // Login failed
                    Toast.makeText(Login.this, "Login failed. Please retry.", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
