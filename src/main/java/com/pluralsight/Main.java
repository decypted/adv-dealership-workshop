package com.pluralsight;

import com.pluralsight.data.DealershipInventoryFileManager;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.UserInterface;
import com.pluralsight.model.Vehicle;

import java.util.ArrayList;

public class Main {
    public static DealershipInventoryFileManager dealershipInventoryFileManager = new DealershipInventoryFileManager("files/inventory.csv");
    public static ArrayList<Vehicle> vehicleInventory = dealershipInventoryFileManager.loadDealership().getInventory();
    public static  Dealership dealership = dealershipInventoryFileManager.loadDealership();
     static void main(String[] args) {

         UserInterface ui = new UserInterface(dealership);

         ui.display();
     }



}

