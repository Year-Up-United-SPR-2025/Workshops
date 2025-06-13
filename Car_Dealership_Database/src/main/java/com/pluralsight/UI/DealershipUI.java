package com.pluralsight.UI;

import com.pluralsight.Dao.*;
import com.pluralsight.Models.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DealershipUI {

    // DAO instances for database operations
    private VehicleDao vehicleDao;
    private SalesDao salesDao;
    private LeaseDao leaseDao;
    private DealershipDao dealershipDao;

    // Current dealership context
    private Dealership currentDealership;

    // Scanner for user input
    private Scanner scanner;

    public DealershipUI() {

        String username = "user_1";
        String password = "password1234";
        String connectionString = "jdbc:mysql://localhost:3306/cardealership";

        // Initialize DAO objects
        this.vehicleDao = new VehicleDao(connectionString, username, password);
        this.salesDao = new SalesDao(connectionString, username, password);
        this.leaseDao = new LeaseDao(connectionString, username, password);
        this.dealershipDao = new DealershipDao(connectionString, username, password);

        this.scanner = new Scanner(System.in);

        this.currentDealership = dealershipDao.findById(1);
        if (this.currentDealership == null) {
            System.out.println("Error: Could not load dealership from database!");
            System.exit(1);
        }
    }

    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Welcome to " + currentDealership.getName());
            System.out.println(currentDealership.getAddress());
            System.out.println(currentDealership.getPhone());
            System.out.println("=".repeat(60));

            System.out.println("Please select from the following choices:");
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/lease a vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter Selected Option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellLeaseVehicleRequest();
                    break;
                case 99:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for visiting " + currentDealership.getName() + "!");
        scanner.close();
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: $");
        double minPrice = getDoubleInput();
        System.out.print("Enter maximum price: $");
        double maxPrice = getDoubleInput();

        List<Vehicle> vehicles = vehicleDao.findByPriceRange(minPrice, maxPrice);
        displayVehicles(vehicles, "Vehicles in price range $" + minPrice + " - $" + maxPrice);
    }

    private void displayVehicles(List<Vehicle> vehicles, String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }


    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();

        List<Vehicle> vehicles = vehicleDao.findByMakeModel(make, model);
        displayVehicles(vehicles, "Vehicles: " + make + " " + model);
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = getIntInput();
        System.out.print("Enter maximum year: ");
        int maxYear = getIntInput();

        List<Vehicle> vehicles = vehicleDao.findByYearRange(minYear, maxYear);
        displayVehicles(vehicles, "Vehicles from " + minYear + " to " + maxYear);
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();

        List<Vehicle> vehicles = vehicleDao.findByColor(color);
        displayVehicles(vehicles, "Vehicles in " + color);
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int minMileage = getIntInput();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = getIntInput();

        List<Vehicle> vehicles = vehicleDao.findByMileageRange(minMileage, maxMileage);
        displayVehicles(vehicles, "Vehicles with " + minMileage + " - " + maxMileage + " miles");
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.nextLine().trim();

        List<Vehicle> vehicles = vehicleDao.findByType(type);
        displayVehicles(vehicles, type + " vehicles");
    }

    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = vehicleDao.getVehiclesByDealership(currentDealership.getId());
        displayVehicles(vehicles, "All vehicles in inventory");
    }

    private void processAddVehicleRequest() {
        System.out.println("\nAdd New Vehicle");
        System.out.println("-".repeat(30));

        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine().trim();

        // Check if VIN already exists
        Vehicle existingVehicle = vehicleDao.findByVin(vin);
        if (existingVehicle != null) {
            System.out.println("Error: A vehicle with VIN " + vin + " already exists!");
            return;
        }

        System.out.print("Enter year: ");
        int year = getIntInput();

        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();

        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();

        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.nextLine().trim();

        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();

        System.out.print("Enter odometer reading: ");
        int odometer = getIntInput();

        System.out.print("Enter price: $");
        double price = getDoubleInput();

        // Create new vehicle
        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        newVehicle.setDealershipId(currentDealership.getId());

        // Add to database
        boolean success = vehicleDao.addVehicle(newVehicle);

        if (success) {
            System.out.println("Vehicle added successfully!");
            System.out.println("Vehicle ID: " + newVehicle.getId());
        } else {
            System.out.println("Error: Failed to add vehicle to database.");
        }
    }

    private void processRemoveVehicleRequest() {
        System.out.println("\nRemove Vehicle");
        System.out.println("-".repeat(30));

        System.out.print("Enter VIN of vehicle to remove: ");
        String vin = scanner.nextLine().trim();

        // Check if vehicle exists
        Vehicle vehicle = vehicleDao.findByVin(vin);
        if (vehicle == null) {
            System.out.println("Error: No vehicle found with VIN " + vin);
            return;
        }

        // Display vehicle details for confirmation
        System.out.println("Vehicle to remove:");
        System.out.println(vehicle);

        System.out.print("Are you sure you want to remove this vehicle? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            boolean success = vehicleDao.removeVehicle(vin);

            if (success) {
                System.out.println("Vehicle removed successfully!");
            } else {
                System.out.println("Error: Failed to remove vehicle from database.");
            }
        } else {
            System.out.println("Vehicle removal cancelled.");
        }
    }

    private void processSellLeaseVehicleRequest() {
        System.out.println("\nSell/Lease Vehicle");
        System.out.println("-".repeat(30));

        System.out.print("Enter VIN of vehicle to sell/lease: ");
        String vin = scanner.nextLine().trim();

        // Find the vehicle
        Vehicle vehicle = vehicleDao.findByVin(vin);
        if (vehicle == null) {
            System.out.println("Error: No vehicle found with VIN " + vin);
            return;
        }

        if (vehicle.isSold()) {
            System.out.println("Error: This vehicle has already been sold!");
            return;
        }

        // Display vehicle details
        System.out.println("\nVehicle Details:");
        System.out.println(vehicle);

        // Get contract type
        System.out.println("\nContract Options:");
        System.out.println("1 - Sale");
        System.out.println("2 - Lease");
        System.out.print("Choose contract type: ");

        int contractType = getIntInput();

        switch (contractType) {
            case 1:
                processSaleContract(vehicle);
                break;
            case 2:
                processLeaseContract(vehicle);
                break;
            default:
                System.out.println("Invalid choice. Transaction cancelled.");
                return;
        }
    }

    private void processSaleContract(Vehicle vehicle) {
        System.out.println("\nSales Contract");
        System.out.println("-".repeat(30));

        // Get customer information
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Customer email: ");
        String customerEmail = scanner.nextLine().trim();

        // Get financing option
        System.out.print("Finance the vehicle? (yes/no): ");
        String financeChoice = scanner.nextLine().trim().toLowerCase();
        int financeOption = financeChoice.equals("yes") || financeChoice.equals("y") ? 1 : 0;

        // Calculate fees
        double salesTax = vehicle.getPrice() * 0.05; // 5% sales tax
        double recordingFee = 100.00;
        double processingFee = (vehicle.getPrice() < 10000) ? 295.00 : 495.00;

        // Create sales contract
        String contractDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        SalesContract contract = new SalesContract(contractDate, customerName, customerEmail,
                vehicle, salesTax, recordingFee, processingFee, financeOption);

        // Display contract details
        displaySalesContractDetails(contract);

        // Confirm the sale
        System.out.print("\nConfirm this sale? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            // Save contract to database
            boolean contractSaved = salesDao.saveSalesContract(contract);

            if (contractSaved) {
                vehicle.setSold(true);
                System.out.println("Sale completed successfully!");
                System.out.println("Contract ID: " + contract.getContractId());
            } else {
                System.out.println("Error: Failed to save sales contract.");
            }
        } else {
            System.out.println("Sale cancelled.");
        }
    }


    private void processLeaseContract(Vehicle vehicle) {
        System.out.println("\nLease Contract");
        System.out.println("-".repeat(30));

        // Get customer information
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Customer email: ");
        String customerEmail = scanner.nextLine().trim();

        // Get lease start and end dates
        System.out.print("Enter lease start date (YYYY-MM-DD): ");
        String leaseStart = scanner.nextLine().trim();

        System.out.print("Enter lease end date (YYYY-MM-DD): ");
        String leaseEnd = scanner.nextLine().trim();

        // Calculate lease values
        double expectedEndingValue = vehicle.getPrice() * 0.50; // 50% of original price
        double leaseFee = vehicle.getPrice() * 0.07; // 7% lease fee

        // Create lease contract
        String contractDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        LeaseContract contract = new LeaseContract(contractDate, leaseStart, leaseEnd,
                customerName, customerEmail, vehicle, expectedEndingValue, leaseFee);

        // Display contract details
        displayLeaseContractDetails(contract);

        // Confirm the lease
        System.out.print("\nConfirm this lease? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            // Save contract to database
            boolean contractSaved = leaseDao.saveLeaseContract(contract);

            if (contractSaved) {
                // Mark vehicle as sold (leased)
                vehicle.setSold(true);
                System.out.println("Lease completed successfully!");
                System.out.println("Contract ID: " + contract.getId());
            } else {
                System.out.println("Error: Failed to save lease contract.");
            }
        } else {
            System.out.println("Lease cancelled.");
        }
    }



    private void displaySalesContractDetails(SalesContract contract) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SALES CONTRACT DETAILS");
        System.out.println("=".repeat(50));
        System.out.println("Date: " + contract.getSaleDate());
        System.out.println("Customer: " + contract.getCustomerName());
        System.out.println("Email: " + contract.getCustomerEmail());
        System.out.println();
        System.out.println("Vehicle: " + contract.getVehicleSold().getYear() + " " +
                contract.getVehicleSold().getMake() + " " + contract.getVehicleSold().getModel());
        System.out.println("VIN: " + contract.getVehicleSold().getVin());
        System.out.println();
        System.out.printf("Vehicle Price:     $%10.2f%n", contract.getVehicleSold().getPrice());
        System.out.printf("Sales Tax (5%%):    $%10.2f%n", contract.getSalesTaxAmount());
        System.out.printf("Recording Fee:     $%10.2f%n", contract.getRecordingFee());
        System.out.printf("Processing Fee:    $%10.2f%n", contract.getProcessingFee());
        System.out.println("-".repeat(30));
        System.out.printf("TOTAL PRICE:       $%10.2f%n", contract.getTotalPrice());

        if (contract.isFinanceOption()) {
            System.out.printf("Monthly Payment:   $%10.2f%n", contract.getMonthlyPayment());
        } else {
            System.out.println("Payment Method: CASH");
        }
    }

    private void displayLeaseContractDetails(LeaseContract contract) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LEASE CONTRACT DETAILS");
        System.out.println("=".repeat(50));
        System.out.println("Date: " + contract.getDateOfContract());
        System.out.println("Customer: " + contract.getCustomerName());
        System.out.println("Email: " + contract.getCustomerEmail());
        System.out.println();
        System.out.println("Vehicle: " + contract.getVehicleSold().getYear() + " " +
                contract.getVehicleSold().getMake() + " " + contract.getVehicleSold().getModel());
        System.out.println("VIN: " + contract.getVehicleSold().getVin());
        System.out.println();
        System.out.printf("Vehicle Price:        $%10.2f%n", contract.getVehicleSold().getPrice());
        System.out.printf("Expected Ending Value: $%10.2f%n", contract.getExpectedEndingValue());
        System.out.printf("Lease Fee (7%%):       $%10.2f%n", contract.getLeaseFee());
        System.out.println("-".repeat(35));
        System.out.printf("TOTAL LEASE PRICE:    $%10.2f%n", contract.getTotalPrice());
        System.out.printf("Monthly Payment:      $%10.2f%n", contract.getMonthlyPayment());
        System.out.println("Lease Term: 36 months");
    }

    private int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}