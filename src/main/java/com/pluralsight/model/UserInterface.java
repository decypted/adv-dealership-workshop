package com.pluralsight.model;

import com.pluralsight.ui.Console;
import com.pluralsight.ui.FormatHelper;

import java.lang.classfile.Interfaces;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    Dealership dealership;

    public UserInterface(Dealership dealership){
        this.dealership = dealership;
    }

    public void display(){

        System.out.println("\t Welcome to " + dealership.getName());

        String m = """
        +-----------------------------------------+
        |                                         |
        |   [1]  Search by Price                  |
        |   [2]  Search by Make / Model           |
        |   [3]  Search by Year                   |
        |   [4]  Search by Color                  |
        |   [5]  Search by Mileage                |
        |   [6]  Search by Vehicle Type           |
        |   [7]  All Vehicles                     |
        |   [8]  Add Vehicle                      |
        |   [9]  Remove Vehicle                   |
        |                                         |
        +-----------------------------------------+
        |   [X] Quit                             |
        +-----------------------------------------+
        
        Awaiting user input: >""";

        String userInput = Console.askForString(m);
        int userInputToInt;
        try {
            userInputToInt = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        switch (userInputToInt){
            case 1 -> processGetByPriceRequest();
        }

    }

    public void processGetByPriceRequest(){
        double minPrice = Console.askForDouble("What is the minimum price you are looking for");
        double maxPrice = Console.askForDouble("What is the max price?");

        List<Vehicle> queryResult = dealership.getVehiclesByPrice(minPrice, maxPrice);

        FormatHelper.formatHelperVehicleDisplay(queryResult, "The following are priced X through Y");

    }

}


