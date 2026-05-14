package com.pluralsight.controller;

import com.pluralsight.data.ContractFileManager;
import com.pluralsight.data.DealershipInventoryFileManager;
import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Contract.SalesContract;
import com.pluralsight.model.Dealership;


import java.util.List;

public class ContractController {
    Dealership dealership;
    private final ContractFileManager contractFileManager;
    private final DealershipInventoryFileManager dealershipInventoryFileManager;
    String path = "files/contracts.csv";
    String dealershipInventoryData = "files/inventory.csv";

    public ContractController(Dealership dealership){
        this.dealership = dealership;
        this.contractFileManager = new ContractFileManager(path, dealership);
        this.dealershipInventoryFileManager = new DealershipInventoryFileManager(dealershipInventoryData);
    }

    public List<Contract> loadContract(){
        return contractFileManager.loadContract();
    }

    public List<Contract> loadAllSalesContract(){
        List<Contract> c = contractFileManager.loadContract();
        return SalesContract.loadAllSalesContract(c);
    }

    public void saveContract(Contract c){
        contractFileManager.writeContract(c);
        dealership.removeVehicle(c.getVehicleSold());
        dealershipInventoryFileManager.saveDealership(dealership);

    }

}
