package com.example.quadraicequationsolver;

import android.view.View;

import androidx.databinding.BaseObservable;

import com.example.quadraicequationsolver.databinding.ActivityMainBinding;

import java.util.Observable;

public class MyEquation extends BaseObservable {
    String a,b,c;
    ActivityMainBinding binding ;

    public MyEquation(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public MyEquation() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void solveEquation(View view){
        int a = Integer.parseInt(getA());
        int b = Integer.parseInt(getB());
        int c = Integer.parseInt(getC());

        // Calculating the Discriminant
        double discriminant = ((b*b) - 4*(a*c));

        // Finding the Solutions (Roots)
        double x1, x2;
        if (discriminant > 0){
            // Two real and distinct roots (solutions)
            x1 = (-b + Math.sqrt(discriminant)) / (2*a);
            x2 = (-b - Math.sqrt(discriminant)) / (2*a);

            // Display the results in the textview
            binding.result.setText("X1= "+x1+ " and  X2= "+x2);
        }
        else if(discriminant < 0){
            // no real roots
            binding.result.setText("No real roots (complex roots)");
        }  else{
            // Discriminant = 0
            // One real solution (double root)
            x1 = -b / (2 *a);
            binding.result.setText("x = "+x1);
        }

    }


}
