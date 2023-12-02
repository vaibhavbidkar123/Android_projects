package com.example.ambulanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    private UserRepository userRepository;
    private EditText editTextName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabase db = userDatabase.getInstance(getApplicationContext());
        userRepository = new UserRepository(db.userDao());

        editTextName = findViewById(R.id.NameEditText);
        editTextPassword = findViewById(R.id.PasswordEditText);
        editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Button registerButton = findViewById(R.id.LoginButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String name = editTextName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Check if name and password are not empty
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name and password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new user
                Users user = new Users();
                user.name = name;
                user.password = password;

                // Use AsyncTask to perform database operation in the background
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        // Register the user
                        userRepository.register(user);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        // Show a success message
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }.execute();
            }
        });
    }
}