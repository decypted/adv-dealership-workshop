package com.pluralsight.data;

import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DealershipInventoryFileManager {
    private final String dealershipInventoryFileName;
    ArrayList<Vehicle> vehicle = new ArrayList<>();


    public DealershipInventoryFileManager(String dealershipFileName) {
        this.dealershipInventoryFileName = dealershipFileName;
    }

    public String getDealershipInventoryFileName() {
        return dealershipInventoryFileName;
    }

    public Dealership loadDealership() {
        try {
            FileReader fileReader = new FileReader(this.dealershipInventoryFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String dealershipData;
            while ((dealershipData = bufferedReader.readLine()) != null) {
                try {
                    String[] dealershipLine = dealershipData.split("\\|");
                    String dealershipName = dealershipLine[0];
                    String dealershipAddress = dealershipLine[1];
                    String dealershipPhone = dealershipLine[2];

                    Dealership dealership = new Dealership(dealershipName, dealershipAddress, dealershipPhone);
                    //
                    String vehicleData;
                    while ((vehicleData = bufferedReader.readLine()) != null) {
                        try {
                            String[] vehicleLine = vehicleData.split("\\|");
                            int vehicleVin = Integer.parseInt(vehicleLine[0]);
                            int vehicleYear = Integer.parseInt(vehicleLine[1]);
                            String vehicleMake = vehicleLine[2];
                            String vehicleModel = vehicleLine[3];
                            String vehicleType = vehicleLine[4];
                            String vehicleColor = vehicleLine[5];
                            int vehicleOdometer = Integer.parseInt(vehicleLine[6]);
                            double vehiclePrice = Double.parseDouble(vehicleLine[7]);
                            Vehicle v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);
                            dealership.addVehicle(v);
                        }
                        //
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    return dealership;

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

