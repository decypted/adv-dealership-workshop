package com.pluralsight.model;

import com.pluralsight.data.DealershipInventoryFileManager;
import com.pluralsight.ui.FormatHelper;

import java.util.ArrayList;
import java.util.List;


public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private final String dealerCsvFile;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
        this.dealerCsvFile = "files/inventory.csv";
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getPrice() >= minPrice && v.getPrice() <= maxPrice){
                queryVehicles.add(v);
            }
        }
        return queryVehicles;
    }

    public List<Vehicle> getVehiclesByMakeOrModel(String makemodel){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if((makemodel.contains(v.getMake()) || (makemodel.contains(v.getModel())))){
                queryVehicles.add(v);
            }
        }

        return queryVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getYear() >= minYear && v.getYear() <= maxYear ){
                queryVehicles.add(v);
            }
        }

        return queryVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getColor().equalsIgnoreCase(color)){
                queryVehicles.add(v);
            }
        }

        return queryVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getOdometer() >= minMileage && v.getOdometer() <= maxMileage){
                queryVehicles.add(v);
            }
        }

        return queryVehicles;
    }

    public List<Vehicle> getVehiclesByType(String type){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getVehicleType().equalsIgnoreCase(type)){
                queryVehicles.add(v);
            }
        }
        return queryVehicles;
    }

    public List<Vehicle> getVehicleByVin(int vin){
        List<Vehicle> queryVehicle = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getVin() == vin){
                queryVehicle.add(v);
            }
        }
        return queryVehicle;
    }

    public List<Vehicle> getAllVehicle(){
        return new ArrayList<>(this.getInventory());
    }



    public void addVehicle(
            int vin,
            int year,
            String make,
            String model,
            String vehicleType,
            String color,
            int odometer,
            double price
    ){

        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        this.inventory.add(v);
        DealershipInventoryFileManager dealershipInventoryFileManager = new DealershipInventoryFileManager(dealerCsvFile);

        try {
            dealershipInventoryFileManager.saveDealership(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteVehicle(int vin){
        Vehicle vToDel = null;
        for(Vehicle v : inventory){
            if(v.getVin() == vin){
                vToDel = v;
            }
        }

        if(vToDel != null){
            try {
                DealershipInventoryFileManager dealershipInventoryFileManager = new DealershipInventoryFileManager(dealerCsvFile);
                this.inventory.remove(vToDel);
                dealershipInventoryFileManager.saveDealership(this);
            } catch (Exception e) {
                return "An err occurred" + e.getMessage();
            }
        } else {
            return "No vehicle of this vin was found";
        }
        return "Vehicle was deleted";
    }


    @Override
    public String toString() {
        return "Dealership" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' + '\n' +
                "Current Inventory:" + "\n"  + inventory + "\n";
    }
}
