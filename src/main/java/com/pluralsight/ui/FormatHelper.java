package com.pluralsight.ui;

import com.pluralsight.JavaHelpers.ColorCodes;
import com.pluralsight.model.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormatHelper {

    public static void formatHelperVehicleDisplay(List<Vehicle> vehicles, String header){
        System.out.println(header);
        formatHelperVehicleDisplay(vehicles);
    }

    public static void formatHelperVehicleDisplay(Vehicle v){
        System.out.printf("%-12s %-15s %-40s %10d %s$%.2f%n%s",
                v.getMake(),
                v.getModel(),
                v.getColor(),
                v.getOdometer(),
                ColorCodes.GREEN,
                v.getPrice(),
                ColorCodes.RESET);

    }

    public static void formatHelperVehicleDisplay(List<Vehicle> vehicles) {

        String r = (vehicles.size() == 1 ? "result" : "results");
        String resultColor;
        if(vehicles.isEmpty()){
            resultColor = ColorCodes.RED;
        } else {
            resultColor = ColorCodes.GREEN;
        }
        System.out.printf("Found %d %s%s%s\n", vehicles.size(), resultColor,  r, ColorCodes.RESET);

        if(vehicles.isEmpty()){
            System.out.println("No vehicles found matching your query. Try broadening your search.");
        } else {
            for (Vehicle v : vehicles) {
                formatHelperVehicleDisplay(v);
            }
        }


    }
}
