package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyDhw_dv7xSxPQWCQtzg6SnfuIEHpHBB_vc";
    private static final String DIRECTIONS_ENDPOINT = "https://maps.googleapis.com/maps/api/directions/json";

    private List<LatLng> coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinates = new ArrayList<>();

        // Example usage
        new GetDirectionsTask().execute("margao ,goa ,india", "panjim goa india ");
    }

    private class GetDirectionsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... locations) {
            try {
                // Construct the API URL
                String apiUrl = DIRECTIONS_ENDPOINT +
                        "?key=" + GOOGLE_MAPS_API_KEY +
                        "&origin=" + locations[0] +
                        "&destination=" + locations[1] +
                        "&mode=driving"; // You can specify the travel mode here

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
                    JSONArray steps = jsonResponse.getJSONArray("routes")
                            .getJSONObject(0)
                            .getJSONArray("legs")
                            .getJSONObject(0)
                            .getJSONArray("steps");

                    // Log the step-by-step coordinates
                    for (int i = 0; i < steps.length(); i++) {
                        JSONObject step = steps.getJSONObject(i);
                        JSONObject startLocation = step.getJSONObject("start_location");

                        double lat = startLocation.getDouble("lat");
                        double lng = startLocation.getDouble("lng");

                        LatLng latLng = new LatLng(lat, lng);
                        coordinates.add(latLng);

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


