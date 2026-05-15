package com.pluralsight.data;

import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Contract.LeaseContract;
import com.pluralsight.model.Contract.SalesContract;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class ContractFileManager {
    private final String contractFileName;
    private final Dealership dealership;

    public ContractFileManager(String contractFile,Dealership dealership) {
        this.contractFileName = contractFile;
        this.dealership = dealership;

    }

    public List<Contract> loadContract() {
        List<Contract> contracts = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.contractFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String contractData;

            while ((contractData = bufferedReader.readLine()) != null) {
                String[] parts = contractData.split("\\|");

                String contractType = parts[0];

                String date = parts[1];
                String customerName = parts[2];
                String customerEmail = parts[3];
                int vin = Integer.parseInt(parts[4]);
                int year = Integer.parseInt(parts[5]);
                String make = parts[6];
                String model = parts[7];
                String vehicleType = parts[8];
                String color = parts[9];
                int odometer = Integer.parseInt(parts[10]);
                double vehiclePrice = Double.parseDouble(parts[11]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, vehiclePrice);
                if (contractType.equalsIgnoreCase("SALE")) {
                    double salesTax = Double.parseDouble(parts[12]);
                    double recordingFee = Double.parseDouble(parts[13]);
                    double processingFee = Double.parseDouble(parts[14]);
                    double totalPrice = Double.parseDouble(parts[15]);
                    boolean isFinancing = parts[16].equalsIgnoreCase("YES");
                    double monthlyPayment = Double.parseDouble(parts[17]);

                    contracts.add(new SalesContract(date, customerName, customerEmail, vehicle, isFinancing  ));



                }
                if (contractType.equalsIgnoreCase("LEASE")) {
                    double expectedEndingValue = Double.parseDouble(parts[12]);
                    double leastFee = Double.parseDouble(parts[13]);
                    double totalPrice = Double.parseDouble(parts[14]);
                    double monthlyPayment = Double.parseDouble(parts[15]);

                    contracts.add(new LeaseContract(date, customerName, customerEmail, vehicle));
                }
            }
        } catch (Exception e) {
            System.out.println("Err in SALE Contract Parsing" + e.getMessage());
        }
        return contracts;
    }

    public void writeContract(Contract contract) {
        String contractInfo = "";
        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.contractFileName, true));
            if (contract instanceof SalesContract){
                 contractInfo = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f%n",
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),
                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getVehicleType(),
                        contract.getVehicleSold().getColor(),
                        contract.getVehicleSold().getOdometer(),
                        contract.getVehicleSold().getPrice(),
                        ((SalesContract) contract).getSalesTax(),
                        ((SalesContract) contract).getRecordingFee(),
                        ((SalesContract) contract).getProcessingFee(),
                        contract.getTotalPrice(),
                        ((SalesContract) contract).isFinancing() ? "YES" : "NO",
                        contract.getMonthlyPayment());
            } else if (contract instanceof LeaseContract lc) {
                contractInfo = String.format(
                        "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f%n",
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),
                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getVehicleType(),
                        contract.getVehicleSold().getColor(),
                        contract.getVehicleSold().getOdometer(),
                        contract.getTotalPrice(),
                        lc.getExpectedEndValue(),
                        lc.getLeastFee(),
                        contract.getTotalPrice(),
                        contract.getMonthlyPayment()
                );
            }

            bufferedWriter.write(contractInfo);
            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println("Err in LEASE Contract Parsing" + e.getMessage());
        }
    }

}


