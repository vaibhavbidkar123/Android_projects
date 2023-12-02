package com.example.jsonreader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "GeoJsonActivity";
    private MapView mapView;
    private GoogleMap googleMap;
    private Polyline polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Replace "Indian_States.json" with the actual file path in the assets folder
        String assetFilePath = "Indian_States.txt";

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Load and parse GeoJSON in a background thread
        new GeoJsonParserTask().execute(assetFilePath);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Move the camera to the first coordinate (optional)
        LatLng firstCoordinate = new LatLng(15.355694, 73.781807);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstCoordinate, 10f));
    }

    private class GeoJsonParserTask extends AsyncTask<String, Void, List<LatLng>> {

        @Override
        protected List<LatLng> doInBackground(String... params) {
            String assetFilePath = params[0];
            return parseGeoJsonFromAsset(assetFilePath);
        }

        @Override
        protected void onPostExecute(List<LatLng> coordinatesList) {
            // Handle the result, for example, log the coordinates
            for (LatLng latLng : coordinatesList) {
                Log.d("values", "Latitude: " + latLng.latitude + ", Longitude: " + latLng.longitude);
            }

            // Draw polyline on Google Map
            drawPolyline(coordinatesList);
        }
    }

    private List<LatLng> parseGeoJsonFromAsset(String assetFilePath) {
        List<LatLng> coordinatesList = new ArrayList<>();

        try {
            InputStream inputStream = getAssets().open(assetFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONObject jsonRootObject = new JSONObject(json.toString());
            JSONArray featuresArray = jsonRootObject.getJSONArray("features");

            for (int i = 0; i < featuresArray.length(); i++) {
                JSONObject featureObject = featuresArray.getJSONObject(i);
                JSONObject propertiesObject = featureObject.getJSONObject("properties");

                // Check if the feature represents Goa
                if ("Goa".equals(propertiesObject.getString("NAME_1"))) {
                    JSONObject geometryObject = featureObject.getJSONObject("geometry");

                    // Assuming Goa's borders are represented by features of type "MultiPolygon"
                    if ("MultiPolygon".equals(geometryObject.getString("type"))) {
                        JSONArray coordinatesArray = geometryObject.getJSONArray("coordinates").getJSONArray(3);

                        for (int j = 0; j < coordinatesArray.length(); j++) {
                            JSONArray polygonCoordinates = coordinatesArray.getJSONArray(j);

                            for (int k = 0; k < polygonCoordinates.length(); k++) {
                                JSONArray coordinate = polygonCoordinates.getJSONArray(k);
                                double latitude = coordinate.getDouble(1);
                                double longitude = coordinate.getDouble(0);
                                coordinatesList.add(new LatLng(latitude, longitude));
                            }
                        }
                    }
                }
            }

            reader.close();
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error reading/parsing GeoJSON from asset", e);
        }

        return coordinatesList;
    }


    private void drawPolyline(List<LatLng> coordinatesList) {
        if (googleMap == null || coordinatesList.size() < 2) {
            // Map not ready or not enough coordinates to draw a polyline
            return;
        }

        // Create PolylineOptions
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(0xFF0000FF); // Blue color
        polylineOptions.width(5);

        // Add coordinates to PolylineOptions
        for (LatLng latLng : coordinatesList) {
            polylineOptions.add(latLng);
        }

        // Add polyline to the map
        polyline = googleMap.addPolyline(polylineOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
