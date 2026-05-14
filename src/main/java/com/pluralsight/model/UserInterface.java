package com.pluralsight.model;

import com.pluralsight.JavaHelpers.ColorCodes;
import com.pluralsight.controller.ContractController;
import com.pluralsight.model.Contract.Contract;
import com.pluralsight.model.Contract.SalesContract;
import com.pluralsight.ui.Console;
import com.pluralsight.ui.FormatHelper;


import java.time.Year;
import java.util.List;



public class UserInterface {
    Dealership dealership;
    private ContractController controler;

    public UserInterface(Dealership dealership){
        this.dealership = dealership;
        this.controler = new ContractController(dealership);
    }


    public void display(){
        boolean running = true;

        System.out.println("\t Welcome to " + dealership.getName());

        while (running) {


            String m = """
                    +-----------------------------------------+
                    |                                         |
                    |   [1]  Search by Price                  |
                    |   [2]  Search by Make / Model           |
                    |   [3]  Search by Year                   |
                    |   [4]  Search by Color                  |
                    |   [5]  Search by Mileage                |
                    |   [6]  Search by Vehicle Type           |
                    |   [7]  All Vehicles                     |
                    |   [8]  Add Vehicle                      |
                    |   [9]  Remove Vehicle                   |
                    |   [10] Sell a Vehicle                   |
                    |   [11] Lease a Vehicle                  |
                        [12] Load Contract Test
                        [13] Load Sales Contract
                    +-----------------------------------------+
                    |   [X] Quit                             |
                    +-----------------------------------------+
                    
                    Awaiting user input: >""";

            String userInput = Console.askForString(m);

            if(userInput.equalsIgnoreCase("x")){
                userInput = userInput.toUpperCase();
            }

            switch (userInput) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModel();
                case "3" -> processGetByYear();
                case "4" -> processGetByColor();
                case "5" -> processGetByMileage();
                case "6" -> processGetByType();
                case "7" -> processGetAllVehicle();
                case "8" -> processAddVehcile();
                case "9" -> processDeleteVehicle();
                case "10" -> processWriteNewSaleContract();
                case "12" -> processLoadContract();
                case "13" -> processLoadSalesContract();
                case "X" -> {
                    System.out.println("Goodbye! We hope to see you again");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }

        }
    }

    public void displaySearchResult(List<Vehicle> results, String header){
        FormatHelper.formatHelperVehicleDisplay(results, header);
        Console.promptForExit("You can exit the program at any time by using\n", "x");
    }
    public void displayContractTest(String c){
        System.out.println(c);
    }

    public void processGetByPriceRequest(){
        double minPrice = Console.askForDouble("What is the minimum price you are looking for");
        double maxPrice = Console.askForDouble("What is the max price?");
        List<Vehicle> queryResult = dealership.getVehiclesByPrice(minPrice, maxPrice);
        String header = "Vehicles priced from $%.2f to $%.2f".formatted(minPrice, maxPrice);
        displaySearchResult(queryResult, header);
    }

    public void processGetByMakeModel(){
        String make = Console.askForString("What is the make/model of the vehicle");
        List<Vehicle> queryResult = dealership.getVehiclesByMakeOrModel(make);
        displaySearchResult(queryResult, "Vehicles make that matches: " + make);
    }

    public void processGetByYear(){
        Year currentYear = Year.now();
        int pYear = Integer.parseInt(String.valueOf(currentYear));
        int minYear = Console.askForInt("What is the minimum year?", 1920, pYear);
        int maxYear = Console.askForInt("What is the maximum year?", 1920, pYear);
        List<Vehicle> queryResult = dealership.getVehiclesByYear(minYear, maxYear);
        String header = "Vehicles year ranging from %d to %d".formatted(minYear, maxYear);
        displaySearchResult(queryResult, header);
    }

    public void processGetByColor(){
        String color = Console.askForString("What color is the vehicle");
        List<Vehicle> queryResult = dealership.getVehiclesByColor(color);
        String header = "Color";
        displaySearchResult(queryResult, header);
     //   Console.promptForExit("You can exit the program at any time by using", "x");
    }

    public void processGetByMileage(){
        int minMileage = Console.askForInt("Minimum Mileage", 0 , 9999999);
        int maxMilage = Console.askForInt("Maximum Mileage", 0 , 9999999);
        List<Vehicle> queryResult = dealership.getVehiclesByMileage(minMileage, maxMilage);
        String header = "Mileage";
        displaySearchResult(queryResult, header);

    }

    public void processGetByType(){
        String type = Console.askForString("What is the make/model of the vehicle");
        List<Vehicle> queryResult = dealership.getVehiclesByType(type);
        String header = "Type";
        displaySearchResult(queryResult, header);
    }

    public void processGetAllVehicle(){
        List<Vehicle> all = dealership.getAllVehicle();
        String header = "All listed vehicles of " + dealership.getName();
        displaySearchResult(all, header);
    }

    public void processAddVehcile(){

        boolean isAddingVehicle = true;
        String choice;

        while (isAddingVehicle){
            Year currentYear = Year.now();
            int pYear = Integer.parseInt(String.valueOf(currentYear));
            int pVin;
            String vin = Console.askForString("What is the vin of the vehicle");
            try {
                pVin = Integer.parseInt(vin);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            String model = Console.askForString("What is the model of the vehicle");
            String make = Console.askForString("What is the make of the vehicle");
            int year = Console.askForInt("What is the year of the vehicle?", 1920, pYear);
            String type = Console.askForString("What type is the vehicle");
            String color = Console.askForString("What color is the vehicle");
            int odometer = Console.askForInt("What is the mileage of the vehicle?", 0, 9999999);
            double price = Console.askForDouble("What is the price of the vehicle?");

            dealership.addVehicle(
                    pVin, year, make, model, type, color, odometer, price
            );
            choice = Console.askForString("Would you like to add another one? (y or leave empty to exit): ");

         if(!choice.equalsIgnoreCase("y")){
             System.out.println("Thank you. Returning you to the main menu");
             isAddingVehicle = false;
         }
        }
    }

    public void processDeleteVehicle(){
        boolean isDeletingVehicle = true;

        while(isDeletingVehicle){
            int vin = Console.askForInt("What is the vin of the vehicle you would like to delete: ", 1, 999999999);
            List<Vehicle> vehicle = dealership.getVehicleByVin(vin);

            if(vehicle.isEmpty()){
                System.out.printf("%s%s%s", ColorCodes.RED_BACKGROUND, "No vehicle of this vin was found in our dealership inventory", ColorCodes.RESET);
            } else {
                System.out.println("Found " + vehicle);
                String option = Console.askForString("Would you like to delete this vehicle from " + dealership.getName() + "(leave empty to cancel, any input to proceed.)");

                if(option.isEmpty()){
                    System.out.println("Thank you. Returning you to the main menu");
                    isDeletingVehicle = false;
                } else {
                    String r = dealership.deleteVehicle(vin);
                    System.out.println(r);

                    String exitPrompt = Console.askForString("Would you like to delete another vehicle? (y/n): ");

                    if(exitPrompt.equalsIgnoreCase("n")){
                        isDeletingVehicle = false;
                    }
                }
            }

        }

    }

    public void processWriteNewSaleContract(){
        boolean isCreatingSaleContract = true;
        while (isCreatingSaleContract){
            int vin = Console.askForInt("What is the vin of the vehicle being sold? ", 1, 999999999);
            List<Vehicle> vehicle = dealership.getVehicleByVin(vin);

            if(vehicle.isEmpty()){
                System.out.println("Vehicle of this vin does not exist");
                return;
            }

            Vehicle v = vehicle.getFirst();
            System.out.println("Found. Vehicle" + v.getModel() + v.getPrice() );
            String date = String.valueOf(Console.askForDate("When was the vehicle sold?"));
            String customerName = Console.askForString("What is the customer name");
            String customerEmail = Console.askForString("What is the customer email");
            boolean isFinancing = Console.askForString("Financing? (y/n): ").equalsIgnoreCase("y");

            SalesContract sc = new SalesContract(date, customerName, customerEmail, v, isFinancing);
            System.out.printf("Sale Contract: Vehicle: %s %s %s \n for %s %s on %s. Price: $%,.2f ", v.getModel(), v.getMake(), v.getYear(), customerName, customerEmail, date, sc.getTotalPrice() );
            if(isFinancing){
                System.out.printf("Individual is financing. Terms: $%,.2f/mo for %s month at %.2f apr \n", sc.getMonthlyPayment(), sc.getLoanTerm(), sc.getApr() * 100);
            }
            controler.saveContract(sc);
            isCreatingSaleContract = false;




        }



    }

    public void processLoadContract(){
        String m = String.valueOf(controler.loadContract());

        displayContractTest(m);
    }

    public void processLoadSalesContract(){
      List<Contract> salesContract = controler.loadAllSalesContract();
        System.out.println(salesContract.size());
        for (Contract c : salesContract) {
            System.out.println(c);

        }

    }


}


