package com.example.myapplication;


import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.tasks.networkanalysis.Route;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteParameters;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteResult;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
import com.esri.arcgisruntime.tasks.networkanalysis.Stop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private RouteTask routeTask;
    private GraphicsOverlay graphicsOverlay;
    private List<Stop> routeStops = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ArcGISMap with a Basemap instance
        ArcGISMap map = new ArcGISMap(Basemap.createTopographic());

        // Create a GraphicsOverlay to display the route
        graphicsOverlay = new GraphicsOverlay();
        mapView = findViewById(R.id.arcgisMapView);
        mapView.setMap(map);
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        // Initialize the RouteTask with a known good routing service URL
        routeTask = new RouteTask(this, "https://sampleserver6.arcgisonline.com/arcgis/rest/services/NetworkAnalysis/SanDiego/NAServer/Route");

        // Set up the map and routing
        setUpMap();

        // Add hardcoded start and end points
        addRouteStops();
    }

    //https://route-api.arcgis.com/arcgis/rest/services/World/Route/NAServer/Route_World/solve?startTime=now
    private void setUpMap() {
        // Set an initial viewpoint
        Viewpoint viewpoint = new Viewpoint(28.6139,77.2090, 100000);
        mapView.setViewpoint(viewpoint);

        // Set up the map click listener
        mapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                // Get the clicked point on the map
                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
                Point mapPoint = mapView.screenToLocation(screenPoint);

                // Add a stop to the route
                //addRouteStop(mapPoint);

                return true;
            }
        });
    }

    private void addRouteStops() {
        // Hardcoded start and end points
        Point startPoint = new Point(77.2090, 28.6139); // Replace with your start point coordinates
        Point endPoint = new Point(75.8573, 30.9000);   // Replace with your end point coordinates

        // Add stops to the route
        Stop startStop = new Stop(startPoint);
        Stop endStop = new Stop(endPoint);

        // Add the stops to the list of route stops
        routeStops.add(startStop);
        routeStops.add(endStop);

        // Solve the route
        solveRoute();
    }

    private void solveRoute() {
        if (routeStops.size() >= 2) {
            try {
                // If there are at least two stops, create RouteParameters and solve the route
                RouteParameters routeParameters = routeTask.createDefaultParametersAsync().get();

                // Set the stops for the route
                routeParameters.setStops(routeStops);

                // Customize route parameters as needed
                routeParameters.setReturnDirections(true);

                // Solve the route asynchronously
                ListenableFuture<RouteResult> routeResultFuture = routeTask.solveRouteAsync(routeParameters);
                routeResultFuture.addDoneListener(() -> {
                    try {
                        // Get the route result
                        RouteResult routeResult = routeResultFuture.get();

                        // Display the route on the map
                        displayRoute(routeResult.getRoutes().get(0));
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            // Inform the user that at least two stops are required for a route
            Toast.makeText(this, "Add at least two stops for a route", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayRoute(Route route) {
        // Clear previous graphics
        graphicsOverlay.getGraphics().clear();

        // Create symbols for the route
        SimpleLineSymbol routeSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0000FF, 5.0f);

        // Create a graphic for the route
        Graphic routeGraphic = new Graphic(route.getRouteGeometry(), routeSymbol);

        // Add the graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(routeGraphic);
    }
}




