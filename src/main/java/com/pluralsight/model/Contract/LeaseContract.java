package com.pluralsight.model.Contract;

import com.pluralsight.model.Vehicle;

public class LeaseContract extends Contract {
    double expectedEndValue;
    double leastFee;
    double monthlyPayment;
    double apr;
    double loanTerm;


    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold, 0);
        this.expectedEndValue = vehicleSold.getPrice() * 0.50;
        this.leastFee = vehicleSold.getPrice() * 0.07;
        this.loanTerm = 36;
        this.apr = 0.04;
        this.monthlyPayment = getMonthlyPayment();
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

    public double getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(double loanTerm) {
        this.loanTerm = loanTerm;
    }

    public double getApr() {
        return apr;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }


    public double getTotalPrice() {
        return vehicleSold.getPrice() + getLeastFee();
    }

    public double getTotalPaymentAfterInterest() {
        return getMonthlyPayment() * loanTerm;
    }

    public double getMonthlyPayment() {
        double principle = getTotalPrice() - getExpectedEndValue();
        double apr = getApr();
        double monthlyRate = apr / 12;
        double topformula = monthlyRate * Math.pow(1 + monthlyRate, loanTerm);
        double bottomformula = Math.pow(1 + monthlyRate, loanTerm) - 1;
        return principle * (topformula / bottomformula);
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                "expectedEndValue=" + expectedEndValue +
                ", leastFee=" + leastFee +
                ", monthlyPayment=" + monthlyPayment +
                "} ";
    }
}
