package com.pluralsight.controller;

import com.pluralsight.data.ContractFileManager;
import com.pluralsight.data.DealershipInventoryFileManager;
import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Contract.LeaseContract;
import com.pluralsight.model.Contract.SalesContract;
import com.pluralsight.model.Dealership;


import java.util.ArrayList;
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
        List<Contract> contracts = loadContract();
        List<Contract> salesContract = new ArrayList<>();
        for (Contract c : contracts) {
            if (c instanceof SalesContract) {
                salesContract.add(c);
            }
        }
        return salesContract;
    }

    public List<Contract> loadLeaseContract(){
        List<Contract> contracts = loadContract();
        List<Contract> leaseContract = new ArrayList<>();

        for(Contract c : contracts){
            if(c instanceof LeaseContract){
                leaseContract.add(c);
            }
        }
        return leaseContract;
    }

    public void saveContract(Contract c){
        contractFileManager.writeContract(c);
        dealership.removeVehicle(c.getVehicleSold());
        dealershipInventoryFileManager.saveDealership(dealership);


    }


}
