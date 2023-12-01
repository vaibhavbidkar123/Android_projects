package com.example.ambulanceapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class ShowRoute extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;

    private List<LatLng> coordinates; // Your list of coordinates

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);
        Intent intent = getIntent();

        coordinates = intent.getParcelableArrayListExtra("myObjectList");

        // Initialize the MapView
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Plot the polyline on the map
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates.get(0), 10f));
        googleMap.addPolyline(new PolylineOptions().addAll(coordinates).color(Color.BLUE));

        // Add markers for source (first coordinate) and destination (last coordinate)
        addMarker(googleMap, "Source", coordinates.get(0));
        addMarker(googleMap, "Destination", coordinates.get(coordinates.size() - 1));
        addMarker(googleMap, "Divider", new LatLng(15.3757246, 73.9258352));



    }

    // Method to add a marker
    private void addMarker(GoogleMap googleMap, String title, LatLng position) {
        googleMap.addMarker(new MarkerOptions().position(position).title(title));
    }

    // Other lifecycle methods for the MapView
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
