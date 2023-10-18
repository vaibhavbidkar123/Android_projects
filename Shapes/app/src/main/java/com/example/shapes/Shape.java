package com.example.shapes;

import android.widget.ImageView;
import android.widget.TextView;

public class Shape {
    private int imageResource; // Resource ID of the image
    private String shapeName;

    public Shape(int imageResource, String shapeName) {
        this.imageResource = imageResource;
        this.shapeName = shapeName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }
}
