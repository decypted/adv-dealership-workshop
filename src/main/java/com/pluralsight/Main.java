package com.pluralsight;

import com.pluralsight.data.DealershipInventoryFileManager;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.util.ArrayList;

public class Main {
    private final static DealershipInventoryFileManager dealershipInventoryFileManager = new DealershipInventoryFileManager("files/inventory.csv");
    private static final ArrayList<Vehicle> vehicleInventory = dealershipInventoryFileManager.loadAllVehicles();
    private static final Dealership dealership = dealershipInventoryFileManager.loadDealership();
     static void main(String[] args) {
         initializeDealership();
         System.out.println(dealership);
     }

     public static void initializeDealership(){
         for(Vehicle v: vehicleInventory){
            dealership.addVehicle(v);
         }
     }


}

