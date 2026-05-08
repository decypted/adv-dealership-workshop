package com.pluralsight.ui;

import com.pluralsight.JavaHelpers.ColorCodes;
import com.pluralsight.model.Vehicle;

import java.awt.*;
import java.util.List;

public class FormatHelper {

    public static void formatHelperVehicleDisplay(List<Vehicle> vehicles, String header){
        System.out.println(header);
        formatHelperVehicleDisplay(vehicles);
    }
    public static void formatHelperVehicleDisplay(Vehicle v){
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %,12d  %s$%,12.2f%s%n",
                v.getVin(),
                v.getMake(),
                v.getModel(),
                v.getColor(),
                v.getYear(),
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

        if(vehicles.isEmpty()){
            System.out.println("No vehicles found matching your query. Try broadening your search.");
        } else {
            formatHelperHeader();
            System.out.printf("Found %d %s%s%s\n", vehicles.size(), resultColor,  r, ColorCodes.RESET);
            for (Vehicle v : vehicles) {
                formatHelperVehicleDisplay(v);
            }
        }
    }

    public static void formatHelperHeader(){
        System.out.println("-".repeat(120));
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %12s %12s\n",
                "VIN",
                "MAKE",
                "MODEL",
                "COLOR",
                "YEAR",
                "MILEAGE",
                "PRICE"
                );
        System.out.println("-".repeat(120));
    }
}
