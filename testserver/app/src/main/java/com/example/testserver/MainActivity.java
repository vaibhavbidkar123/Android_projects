package com.example.testserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String RASPBERRY_PI_IP = "192.168.0.105"; // Replace with your Raspberry Pi's IP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connectButton = findViewById(R.id.connectionButton);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectToRaspberryPiTask().execute();
            }
        });
    }

    private class ConnectToRaspberryPiTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://" + RASPBERRY_PI_IP + ":5000/check_connection");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    // Read the response if needed
                    // For simplicity, we are not processing the response in this example
                } finally {
                    urlConnection.disconnect();
                }

                return "Connection successful!";
            } catch (IOException e) {
                e.printStackTrace();
                return "Connection failed!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Update UI or show a Toast based on the result
            // For simplicity, we are not updating the UI in this example
        }
    }
}
