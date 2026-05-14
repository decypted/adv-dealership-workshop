package com.pluralsight.model.Contract;

import com.pluralsight.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class SalesContract extends Contract {
    double salesTax;
    double recordingFee;
    double processingFee;
    boolean isFinancing;
    double apr;


    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinancing){
        super(date, customerName, customerEmail, vehicleSold, 0);
        this.isFinancing = isFinancing;
        this.recordingFee = 100;
        this.processingFee = getProcessingFee();
        this.salesTax = 0.05;

    }

    public static List<Contract> loadAllSalesContract(List<Contract> allSalesContract){
        List<Contract> sale = new ArrayList<>();
        for(Contract contract : allSalesContract ){
            if(contract instanceof SalesContract){
                sale.add(contract);
            }
        }
        return sale;
    }

   public static void saveNewSalesContract() {

   }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public void setRecordingFee(){
        this.recordingFee = recordingFee;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee(){
        return (getVehicleSold().getPrice() < 10000 ? 295 : 495);
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public double getApr() {
        return getVehicleSold().getPrice() < 10000 ? 0.0525 : 0.0425;
    }

    public int getLoanTerm() {
        return getVehicleSold().getPrice() < 10000 ? 24 : 48;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public boolean isFinancing() {
        return isFinancing;
    }

    public void setFinancing(boolean financing) {
        isFinancing = financing;
    }




    public double getTotalPrice() {
        return (getVehicleSold().getPrice() * (salesTax + 1) + recordingFee + getProcessingFee());
    }

    public double getMonthlyPayment() {
        if(!isFinancing){
           return 0;
        }
        double principle = getTotalPrice();
        double apr = getApr();
        double term = getLoanTerm();
        double monthlyRate = apr / 12;
        double topformula = monthlyRate * Math.pow(1 + monthlyRate, term);
        double bottomformula = Math.pow(1 + monthlyRate, term) - 1;
        return principle * (topformula / bottomformula);
    }


    @Override
    public String toString() {
        return "SalesContract{" + super.toString() +
                "salesTax=" + salesTax +
                ", recordingFee=" + recordingFee +
                ", processingFee=" + processingFee +
                ", isFinancing=" + isFinancing +
                ", apr=" + apr +
                "} ";
    }
}
