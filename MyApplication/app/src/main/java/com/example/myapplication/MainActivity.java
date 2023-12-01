package com.example.myapplication;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MAPQUEST_API_KEY = "Eifx0ApejtxEp9aDEQMa1C6l6lKz2K73";
    private static final String DIRECTIONS_ENDPOINT = "https://www.mapquestapi.com/directions/v2/route";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example usage
        new GetDirectionsTask().execute("New York, NY", "Los Angeles, CA");
    }

    private class GetDirectionsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... locations) {
            try {
                // Construct the API URL
                String apiUrl = DIRECTIONS_ENDPOINT +
                        "?key=" + MAPQUEST_API_KEY +
                        "&from=" + locations[0] +
                        "&to=" + locations[1];

                // Make the HTTP request
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }

                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null) {
                try {
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(result);
                    JSONArray steps = jsonResponse.getJSONObject("route")
                            .getJSONArray("legs")
                            .getJSONObject(0)
                            .getJSONArray("maneuvers");

                    // Log the step-by-step coordinates
                    for (int i = 0; i < steps.length(); i++) {
                        JSONObject step = steps.getJSONObject(i);
                        JSONObject startLocation = step.getJSONObject("startPoint");

                        double lat = startLocation.getDouble("lat");
                        double lng = startLocation.getDouble("lng");

                        Log.d("Directions", "Step " + (i + 1) + ": Lat=" + lat + ", Lng=" + lng);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("Directions", "Failed to get directions");
            }
        }
    }
}




