package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btnGetDirections;
    private TextView tvDirectionsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetDirections = findViewById(R.id.btnGetDirections);
        tvDirectionsResult = findViewById(R.id.tvDirectionsResult);

        btnGetDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetDirectionsTask().execute();
            }
        });
    }

    private String buildDirectionsUrl(double startLatitude, double startLongitude,
                                      double endLatitude, double endLongitude,
                                      List<LatLng> waypoints) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("maps.googleapis.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("directions")
                .appendPath("json")
                .appendQueryParameter("origin", "Goa")
                .appendQueryParameter("destination", "mumbai");

        // Adding waypoints
        if (waypoints != null && waypoints.size() > 0) {
            StringBuilder waypointsBuilder = new StringBuilder();
            for (LatLng waypoint : waypoints) {
                waypointsBuilder.append(waypoint.latitude)
                        .append(",")
                        .append(waypoint.longitude)
                        .append("|");
            }
            waypointsBuilder.setLength(waypointsBuilder.length() - 1); // Remove the last '|'
            builder.appendQueryParameter("waypoints", waypointsBuilder.toString());
        }

        builder.appendQueryParameter("mode", "driving")
                .appendQueryParameter("key", "AIzaSyDhw_dv7xSxPQWCQtzg6SnfuIEHpHBB_vc"); // Replace with your actual API key

        return builder.build().toString();
    }


    private class GetDirectionsTask extends AsyncTask<Void, Void, List<LatLng>> {

        @Override
        protected List<LatLng> doInBackground(Void... voids) {
            List<LatLng> startLocationsList = new ArrayList<>();

            try {
                // Replace these coordinates with your actual start and end coordinates
                double startLatitude = 15.4643;
                double startLongitude = 73.8584;
                double endLatitude = 15.2950;
                double endLongitude =  73.9536;

                // Replace this with your actual waypoints if needed
                List<LatLng> waypoints = null;

                String directionsUrl = buildDirectionsUrl(
                        startLatitude, startLongitude, endLatitude, endLongitude, waypoints);

                // Make the HTTP request
                URL url = new URL(directionsUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = urlConnection.getInputStream();
                    Scanner scanner = new Scanner(in);
                    scanner.useDelimiter("\\A");
                    String result = scanner.hasNext() ? scanner.next() : null;

                    if (result != null) {
                        try {
                            JSONObject jsonResponse = new JSONObject(result);

                            // Check if the "routes" array exists
                            if (jsonResponse.has("routes")) {
                                JSONArray routesArray = jsonResponse.getJSONArray("routes");

                                // Loop through each route
                                for (int i = 0; i < routesArray.length(); i++) {
                                    JSONObject route = routesArray.getJSONObject(i);

                                    // Check if the "legs" array exists
                                    if (route.has("legs")) {
                                        JSONArray legsArray = route.getJSONArray("legs");

                                        // Loop through each leg
                                        for (int j = 0; j < legsArray.length(); j++) {
                                            JSONObject leg = legsArray.getJSONObject(j);

                                            // Check if the "steps" array exists
                                            if (leg.has("steps")) {
                                                JSONArray stepsArray = leg.getJSONArray("steps");

                                                // Loop through each step
                                                for (int k = 0; k < stepsArray.length(); k++) {
                                                    JSONObject step = stepsArray.getJSONObject(k);

                                                    // Extract start location latitude and longitude
                                                    JSONObject startLocation = step.getJSONObject("start_location");
                                                    double startLat = startLocation.getDouble("lat");
                                                    double startLng = startLocation.getDouble("lng");

                                                    // Add the LatLng to the list
                                                    startLocationsList.add(new LatLng(startLat, startLng));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return startLocationsList;
        }

        @Override
        protected void onPostExecute(List<LatLng> startLocationsList) {
            // Log the list of start locations
            for (int i = 0; i < startLocationsList.size(); i++) {
                LatLng latLng = startLocationsList.get(i);
                Log.d("StartLocation", "Step " + i + ": " + latLng.latitude + ", " + latLng.longitude);
            }

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putParcelableArrayListExtra("myObjectList", new ArrayList<>(startLocationsList));
            startActivity(intent);

        }
    }


}

