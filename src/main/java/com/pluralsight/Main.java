package com.pluralsight;

import com.pluralsight.data.DealershipInventoryFileManager;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.UserInterface;
import com.pluralsight.model.Vehicle;

import java.util.ArrayList;

public class Main {
    private final static DealershipInventoryFileManager dealershipInventoryFileManager = new DealershipInventoryFileManager("files/inventory.csv");
    private static final ArrayList<Vehicle> vehicleInventory = dealershipInventoryFileManager.loadDealership().getInventory();
    private static final Dealership dealership = dealershipInventoryFileManager.loadDealership();
     static void main(String[] args) {

         UserInterface ui = new UserInterface(dealership);
         ui.display();

     }



}

