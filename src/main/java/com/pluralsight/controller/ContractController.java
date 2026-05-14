package com.pluralsight.controller;

import com.pluralsight.data.ContractFileManager;
import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Dealership;

public class ContractController {
    Dealership dealership;
    private final ContractFileManager contractFileManager;

    public ContractController(Dealership dealership){
        this.dealership = dealership;
        String path = "files/inventory.csv";
        this.contractFileManager = new ContractFileManager(path, dealership);
    }

    public Contract loadContract(){
        Contract m = contractFileManager.loadContract();
       if(m != null){
           return m;
       } else {
           System.out.println("Error Debug");
       }
       return null;
    }
}
