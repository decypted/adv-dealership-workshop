package com.pluralsight.model.Contract;

import com.pluralsight.model.Vehicle;

public class LeaseContract extends Contract {
    double expectedEndValue;
    double leastFee;
    double monthlyPayment;


    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, double expectedEndValue, double leastFee, double monthlyPayment1) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.expectedEndValue = expectedEndValue;
        this.leastFee = leastFee;
        this.monthlyPayment = monthlyPayment1;
    }

    public double getExpectedEndValue() {
        return expectedEndValue;
    }

    public void setExpectedEndValue(double expectedEndValue) {
        this.expectedEndValue = expectedEndValue;
    }

    public double getLeastFee() {
        return leastFee;
    }

    public void setLeastFee(double leastFee) {
        this.leastFee = leastFee;
    }


    public double getTotalPrice() {
        return 0;
    }

    public double getMonthlyPayment() {
        return 0;
    }
}
