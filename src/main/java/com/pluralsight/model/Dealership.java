package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
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
            System.out.println(v);
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

    public List<Vehicle> getVehiclesByYear(int year){
        List<Vehicle> queryVehicles = new ArrayList<>();
        for(Vehicle v: this.getInventory()){
            if(v.getYear() == year){
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

    public void getAllVehicle(){
        System.out.println(this.getInventory());
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
