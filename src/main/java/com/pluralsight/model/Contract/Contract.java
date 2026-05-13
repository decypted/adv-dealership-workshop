package com.pluralsight.model.Contract;

public abstract class Contract {
    String date;
    String customerName;
    String customerEmail;
    String vehicleSold;
    double totalPrice;
    double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, String vehicleSold, double totalPrice, double monthlyPayment) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

}


