package com.pluralsight.controller;

import com.pluralsight.data.ContractFileManager;
import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;
import com.pluralsight.ui.Console;

import java.util.List;

public class ContractController {
    Dealership dealership;
    private final ContractFileManager contractFileManager;
    String path = "files/contracts.csv";

    public ContractController(Dealership dealership){
        this.dealership = dealership;
        this.contractFileManager = new ContractFileManager(path, dealership);
    }

    public List<Contract> loadContract(){
       List<Contract> c = contractFileManager.loadContract();
       return c;
    }

}
