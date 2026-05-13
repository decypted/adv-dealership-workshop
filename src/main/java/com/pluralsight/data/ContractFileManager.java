package com.pluralsight.data;

import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Contract.LeaseContract;
import com.pluralsight.model.Contract.SalesContract;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;

public class ContractFileManager {
    private final String contractFileName;
    private final Dealership dealership;

    public ContractFileManager(String contractFile, Dealership dealership) {
        this.contractFileName = contractFile;
        this.dealership = dealership;
    }

    public Contract loadContract(){
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
            Vehicle vehicle = dealership.getVehicleByVin(vin).get(0);
            int year = Integer.parseInt(parts[5]);

            String make = parts[6];
            String model = parts[7];
            String vehicleType = parts[8];
            String color = parts[9];
            int odometer = Integer.parseInt(parts[10]);
            double vehiclePrice = Double.parseDouble(parts[11]);

            if (contractType.equalsIgnoreCase("SALE"))
            {
                double salesTax = Double.parseDouble(parts[12]);
                double recordingFee = Double.parseDouble(parts[13]);
                double processingFee = Double.parseDouble(parts[14]);
                double totalPrice = Double.parseDouble(parts[15]);
                boolean isFinancing = parts[16].equalsIgnoreCase("YES");
                double monthlyPayment = Double.parseDouble(parts[17]);

                return new SalesContract(
                    date,
                    customerName,
                    customerEmail,
                    vehicle,
                    totalPrice,
                    monthlyPayment,
                    salesTax,
                    processingFee,
                    isFinancing,
                    monthlyPayment
                );
            }
            if (contractType.equalsIgnoreCase("LEASE"))
            {
                double expectedEndingValue = Double.parseDouble(parts[12]);
                double leastFee = Double.parseDouble(parts[13]);
                double totalPrice =Double.parseDouble(parts[14]);
                double monthlyPayment =Double.parseDouble(parts[15]);

                return new LeaseContract(
                        date,
                        customerName,
                        customerEmail,
                        vehicle,
                        totalPrice,
                        monthlyPayment,
                        expectedEndingValue,
                        leastFee,
                        monthlyPayment
                );
            }
       }
   } catch (Exception e) {
           throw new RuntimeException(e);
   } return null; }
}
