package com.pluralsight.model.Contract;

import com.pluralsight.model.Vehicle;

public class SalesContract extends Contract {
    double salesTax;
    final double recordingFee = 100;
    double processingFee;
    boolean isFinancing;
    double financedMonthlyPayment;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, double salesTax, double processingFee, boolean isFinancing, double financedMonthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.salesTax = salesTax;
        this.processingFee = processingFee;
        this.isFinancing = isFinancing;
        this.financedMonthlyPayment = financedMonthlyPayment;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinancing() {
        return isFinancing;
    }

    public void setFinancing(boolean financing) {
        isFinancing = financing;
    }

    public double getFinancedMonthlyPayment() {
        return financedMonthlyPayment;
    }

    public void setFinancedMonthlyPayment(double financedMonthlyPayment) {
        this.financedMonthlyPayment = financedMonthlyPayment;
    }


    public double getTotalPrice() {
        return 0;
    }

    public double getMonthlyPayment() {
        return 0;
    }

    @Override
    public String toString() {
        return "SalesContract{" +
                "salesTax=" + salesTax +
                ", recordingFee=" + recordingFee +
                ", processingFee=" + processingFee +
                ", isFinancing=" + isFinancing +
                ", financedMonthlyPayment=" + financedMonthlyPayment +
                "} ";
    }
}
