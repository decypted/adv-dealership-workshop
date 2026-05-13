package com.pluralsight.model.Contract;

public class SalesContract extends Contract {
    float salesTax;
    final double recordingFee = 100;
    double processingFee;
    boolean isFinancing;
    double financedMonthlyPayment;

    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold, double totalPrice, double monthlyPayment, float salesTax, double processingFee, boolean isFinancing, double financedMonthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.salesTax = salesTax;
        this.processingFee = processingFee;
        this.isFinancing = isFinancing;
        this.financedMonthlyPayment = financedMonthlyPayment;
    }

    public float getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(float salesTax) {
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
}
