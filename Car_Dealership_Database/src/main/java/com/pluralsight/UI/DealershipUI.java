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
            System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: Could not load dealership from database!" + ColorCodes.RESET);
            System.exit(1);
        }
    }

    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n" + ColorCodes.BRIGHT_CYAN + "=".repeat(60) + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_YELLOW + ColorCodes.BOLD + "🚗 Welcome to " + currentDealership.getName() + " 🚗" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_WHITE + "📍 " + currentDealership.getAddress() + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_WHITE + "📞 " + currentDealership.getPhone() + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_CYAN + "=".repeat(60) + ColorCodes.RESET);

            System.out.println(ColorCodes.BRIGHT_GREEN + ColorCodes.BOLD + "\n🔧 Please select from the following choices:" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "💰 1 - Find vehicles within a price range" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "🏷️  2 - Find vehicles by make / model" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "📅 3 - Find vehicles by year range" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "🎨 4 - Find vehicles by color" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "🛣️  5 - Find vehicles by mileage range" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "🚙 6 - Find vehicles by type (car, truck, SUV, van)" + ColorCodes.RESET);
            System.out.println(ColorCodes.YELLOW + "📋 7 - List ALL vehicles" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_GREEN + "➕ 8 - Add a vehicle" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_RED + "➖ 9 - Remove a vehicle" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_BLUE + "🤝 10 - Sell/lease a vehicle" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_RED + "🚪 99 - Quit" + ColorCodes.RESET);
            System.out.print(ColorCodes.BRIGHT_WHITE + ColorCodes.BOLD + "👉 Enter Selected Option: " + ColorCodes.RESET);

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
                    System.out.println(ColorCodes.BRIGHT_RED + "❌ Invalid choice. Please try again." + ColorCodes.RESET);
            }
        }

        System.out.println(ColorCodes.BRIGHT_GREEN + "🙏 Thank you for visiting " + currentDealership.getName() + "! 👋" + ColorCodes.RESET);
        scanner.close();
    }

    private void processGetByPriceRequest() {
        System.out.print(ColorCodes.BRIGHT_CYAN + "💰 Enter minimum price: $" + ColorCodes.RESET);
        double minPrice = getDoubleInput();
        System.out.print(ColorCodes.BRIGHT_CYAN + "💰 Enter maximum price: $" + ColorCodes.RESET);
        double maxPrice = getDoubleInput();

        List<Vehicle> vehicles = vehicleDao.findByPriceRange(minPrice, maxPrice);
        displayVehicles(vehicles, "💰 Vehicles in price range $" + minPrice + " - $" + maxPrice);
    }

    private void displayVehicles(List<Vehicle> vehicles, String title) {
        System.out.println("\n" + ColorCodes.BRIGHT_BLUE + "=".repeat(50) + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_YELLOW + ColorCodes.BOLD + title + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_BLUE + "=".repeat(50) + ColorCodes.RESET);

        if (vehicles.isEmpty()) {
            System.out.println(ColorCodes.RED + "🚫 No vehicles found." + ColorCodes.RESET);
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(ColorCodes.BRIGHT_WHITE + "🚗 " + vehicle + ColorCodes.RESET);
            }
        }
    }

    private void processGetByMakeModelRequest() {
        System.out.print(ColorCodes.BRIGHT_CYAN + "🏷️ Enter make: " + ColorCodes.RESET);
        String make = scanner.nextLine().trim();
        System.out.print(ColorCodes.BRIGHT_CYAN + "🏷️ Enter model: " + ColorCodes.RESET);
        String model = scanner.nextLine().trim();

        List<Vehicle> vehicles = vehicleDao.findByMakeModel(make, model);
        displayVehicles(vehicles, "🏷️ Vehicles: " + make + " " + model);
    }

    private void processGetByYearRequest() {
        System.out.print(ColorCodes.BRIGHT_CYAN + "📅 Enter minimum year: " + ColorCodes.RESET);
        int minYear = getIntInput();
        System.out.print(ColorCodes.BRIGHT_CYAN + "📅 Enter maximum year: " + ColorCodes.RESET);
        int maxYear = getIntInput();

        List<Vehicle> vehicles = vehicleDao.findByYearRange(minYear, maxYear);
        displayVehicles(vehicles, "📅 Vehicles from " + minYear + " to " + maxYear);
    }

    private void processGetByColorRequest() {
        System.out.print(ColorCodes.BRIGHT_CYAN + "🎨 Enter color: " + ColorCodes.RESET);
        String color = scanner.nextLine().trim();

        List<Vehicle> vehicles = vehicleDao.findByColor(color);
        displayVehicles(vehicles, "🎨 Vehicles in " + color);
    }

    private void processGetByMileageRequest() {
        System.out.print(ColorCodes.BRIGHT_CYAN + "🛣️ Enter minimum mileage: " + ColorCodes.RESET);
        int minMileage = getIntInput();
        System.out.print(ColorCodes.BRIGHT_CYAN + "🛣️ Enter maximum mileage: " + ColorCodes.RESET);
        int maxMileage = getIntInput();

        List<Vehicle> vehicles = vehicleDao.findByMileageRange(minMileage, maxMileage);
        displayVehicles(vehicles, "🛣️ Vehicles with " + minMileage + " - " + maxMileage + " miles");
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print(ColorCodes.BRIGHT_CYAN + "🚙 Enter vehicle type (car, truck, SUV, van): " + ColorCodes.RESET);
        String type = scanner.nextLine().trim();

        List<Vehicle> vehicles = vehicleDao.findByType(type);
        String emoji = getVehicleTypeEmoji(type);
        displayVehicles(vehicles, emoji + " " + type + " vehicles");
    }

    private String getVehicleTypeEmoji(String type) {
        switch (type.toLowerCase()) {
            case "car": return "🚗";
            case "truck": return "🚚";
            case "suv": return "🚙";
            case "van": return "🚐";
            default: return "🚗";
        }
    }

    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = vehicleDao.getVehiclesByDealership(currentDealership.getId());
        displayVehicles(vehicles, "📋 All vehicles in inventory");
    }

    private void processAddVehicleRequest() {
        System.out.println(ColorCodes.BRIGHT_GREEN + ColorCodes.BOLD + "\n➕ Add New Vehicle" + ColorCodes.RESET);
        System.out.println(ColorCodes.GREEN + "-".repeat(30) + ColorCodes.RESET);

        System.out.print(ColorCodes.BRIGHT_CYAN + "🆔 Enter VIN: " + ColorCodes.RESET);
        String vin = scanner.nextLine().trim();

        // Check if VIN already exists
        Vehicle existingVehicle = vehicleDao.findByVin(vin);
        if (existingVehicle != null) {
            System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: A vehicle with VIN " + vin + " already exists!" + ColorCodes.RESET);
            return;
        }

        System.out.print(ColorCodes.BRIGHT_CYAN + "📅 Enter year: " + ColorCodes.RESET);
        int year = getIntInput();

        System.out.print(ColorCodes.BRIGHT_CYAN + "🏭 Enter make: " + ColorCodes.RESET);
        String make = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "🏷️ Enter model: " + ColorCodes.RESET);
        String model = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "🚙 Enter vehicle type (car, truck, SUV, van): " + ColorCodes.RESET);
        String vehicleType = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "🎨 Enter color: " + ColorCodes.RESET);
        String color = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "🛣️ Enter odometer reading: " + ColorCodes.RESET);
        int odometer = getIntInput();

        System.out.print(ColorCodes.BRIGHT_CYAN + "💰 Enter price: $" + ColorCodes.RESET);
        double price = getDoubleInput();

        // Create new vehicle
        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        newVehicle.setDealershipId(currentDealership.getId());

        // Add to database
        boolean success = vehicleDao.addVehicle(newVehicle);

        if (success) {
            System.out.println(ColorCodes.BRIGHT_GREEN + "✅ Vehicle added successfully!" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_YELLOW + "🆔 Vehicle ID: " + newVehicle.getId() + ColorCodes.RESET);
        } else {
            System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: Failed to add vehicle to database." + ColorCodes.RESET);
        }
    }

    private void processRemoveVehicleRequest() {
        System.out.println(ColorCodes.BRIGHT_RED + ColorCodes.BOLD + "\n➖ Remove Vehicle" + ColorCodes.RESET);
        System.out.println(ColorCodes.RED + "-".repeat(30) + ColorCodes.RESET);

        System.out.print(ColorCodes.BRIGHT_CYAN + "🆔 Enter VIN of vehicle to remove: " + ColorCodes.RESET);
        String vin = scanner.nextLine().trim();

        // Check if vehicle exists
        Vehicle vehicle = vehicleDao.findByVin(vin);
        if (vehicle == null) {
            System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: No vehicle found with VIN " + vin + ColorCodes.RESET);
            return;
        }

        // Display vehicle details for confirmation
        System.out.println(ColorCodes.YELLOW + "🚗 Vehicle to remove:" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + vehicle + ColorCodes.RESET);

        System.out.print(ColorCodes.BRIGHT_RED + "⚠️ Are you sure you want to remove this vehicle? (yes/no): " + ColorCodes.RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            boolean success = vehicleDao.removeVehicle(vin);

            if (success) {
                System.out.println(ColorCodes.BRIGHT_GREEN + "✅ Vehicle removed successfully!" + ColorCodes.RESET);
            } else {
                System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: Failed to remove vehicle from database." + ColorCodes.RESET);
            }
        } else {
            System.out.println(ColorCodes.YELLOW + "🚫 Vehicle removal cancelled." + ColorCodes.RESET);
        }
    }

    private void processSellLeaseVehicleRequest() {
        System.out.println(ColorCodes.BRIGHT_BLUE + ColorCodes.BOLD + "\n🤝 Sell/Lease Vehicle" + ColorCodes.RESET);
        System.out.println(ColorCodes.BLUE + "-".repeat(30) + ColorCodes.RESET);

        System.out.print(ColorCodes.BRIGHT_CYAN + "🆔 Enter VIN of vehicle to sell/lease: " + ColorCodes.RESET);
        String vin = scanner.nextLine().trim();

        // Find the vehicle
        Vehicle vehicle = vehicleDao.findByVin(vin);
        if (vehicle == null) {
            System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: No vehicle found with VIN " + vin + ColorCodes.RESET);
            return;
        }

        if (vehicle.isSold()) {
            System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: This vehicle has already been sold!" + ColorCodes.RESET);
            return;
        }

        // Display vehicle details
        System.out.println(ColorCodes.BRIGHT_YELLOW + "\n🚗 Vehicle Details:" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE + vehicle + ColorCodes.RESET);

        // Get contract type
        System.out.println(ColorCodes.BRIGHT_GREEN + "\n📋 Contract Options:" + ColorCodes.RESET);
        System.out.println(ColorCodes.GREEN + "💸 1 - Sale" + ColorCodes.RESET);
        System.out.println(ColorCodes.GREEN + "📝 2 - Lease" + ColorCodes.RESET);
        System.out.print(ColorCodes.BRIGHT_WHITE + "👉 Choose contract type: " + ColorCodes.RESET);

        int contractType = getIntInput();

        switch (contractType) {
            case 1:
                processSaleContract(vehicle);
                break;
            case 2:
                processLeaseContract(vehicle);
                break;
            default:
                System.out.println(ColorCodes.BRIGHT_RED + "❌ Invalid choice. Transaction cancelled." + ColorCodes.RESET);
                return;
        }
    }

    private void processSaleContract(Vehicle vehicle) {
        System.out.println(ColorCodes.BRIGHT_GREEN + ColorCodes.BOLD + "\n💸 Sales Contract" + ColorCodes.RESET);
        System.out.println(ColorCodes.GREEN + "-".repeat(30) + ColorCodes.RESET);

        // Get customer information
        System.out.print(ColorCodes.BRIGHT_CYAN + "👤 Customer name: " + ColorCodes.RESET);
        String customerName = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "📧 Customer email: " + ColorCodes.RESET);
        String customerEmail = scanner.nextLine().trim();

        // Get financing option
        System.out.print(ColorCodes.BRIGHT_CYAN + "💳 Finance the vehicle? (yes/no): " + ColorCodes.RESET);
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
        System.out.print(ColorCodes.BRIGHT_GREEN + "\n✅ Confirm this sale? (yes/no): " + ColorCodes.RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            // Save contract to database
            boolean contractSaved = salesDao.saveSalesContract(contract);

            if (contractSaved) {
                vehicle.setSold(true);
                System.out.println(ColorCodes.BRIGHT_GREEN + "🎉 Sale completed successfully!" + ColorCodes.RESET);
                System.out.println(ColorCodes.BRIGHT_YELLOW + "📄 Contract ID: " + contract.getContractId() + ColorCodes.RESET);
            } else {
                System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: Failed to save sales contract." + ColorCodes.RESET);
            }
        } else {
            System.out.println(ColorCodes.YELLOW + "🚫 Sale cancelled." + ColorCodes.RESET);
        }
    }

    private void processLeaseContract(Vehicle vehicle) {
        System.out.println(ColorCodes.BRIGHT_BLUE + ColorCodes.BOLD + "\n📝 Lease Contract" + ColorCodes.RESET);
        System.out.println(ColorCodes.BLUE + "-".repeat(30) + ColorCodes.RESET);

        // Get customer information
        System.out.print(ColorCodes.BRIGHT_CYAN + "👤 Customer name: " + ColorCodes.RESET);
        String customerName = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "📧 Customer email: " + ColorCodes.RESET);
        String customerEmail = scanner.nextLine().trim();

        // Get lease start and end dates
        System.out.print(ColorCodes.BRIGHT_CYAN + "📅 Enter lease start date (YYYY-MM-DD): " + ColorCodes.RESET);
        String leaseStart = scanner.nextLine().trim();

        System.out.print(ColorCodes.BRIGHT_CYAN + "📅 Enter lease end date (YYYY-MM-DD): " + ColorCodes.RESET);
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
        System.out.print(ColorCodes.BRIGHT_BLUE + "\n✅ Confirm this lease? (yes/no): " + ColorCodes.RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            // Save contract to database
            boolean contractSaved = leaseDao.saveLeaseContract(contract);

            if (contractSaved) {
                // Mark vehicle as sold (leased)
                vehicle.setSold(true);
                System.out.println(ColorCodes.BRIGHT_GREEN + "🎉 Lease completed successfully!" + ColorCodes.RESET);
                System.out.println(ColorCodes.BRIGHT_YELLOW + "📄 Contract ID: " + contract.getId() + ColorCodes.RESET);
            } else {
                System.out.println(ColorCodes.BRIGHT_RED + "❌ Error: Failed to save lease contract." + ColorCodes.RESET);
            }
        } else {
            System.out.println(ColorCodes.YELLOW + "🚫 Lease cancelled." + ColorCodes.RESET);
        }
    }

    private void displaySalesContractDetails(SalesContract contract) {
        System.out.println("\n" + ColorCodes.BRIGHT_GREEN + "=".repeat(50) + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + ColorCodes.BOLD + "💸 SALES CONTRACT DETAILS" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "=".repeat(50) + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "📅 Date: " + contract.getSaleDate() + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "👤 Customer: " + contract.getCustomerName() + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "📧 Email: " + contract.getCustomerEmail() + ColorCodes.RESET);
        System.out.println();
        System.out.println(ColorCodes.BRIGHT_YELLOW + "🚗 Vehicle: " + contract.getVehicleSold().getYear() + " " +
                contract.getVehicleSold().getMake() + " " + contract.getVehicleSold().getModel() + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_YELLOW + "🆔 VIN: " + contract.getVehicleSold().getVin() + ColorCodes.RESET);
        System.out.println();
        System.out.printf(ColorCodes.CYAN + "💰 Vehicle Price:     $%10.2f%n" + ColorCodes.RESET, contract.getVehicleSold().getPrice());
        System.out.printf(ColorCodes.CYAN + "🏛️  Sales Tax (5%%):    $%10.2f%n" + ColorCodes.RESET, contract.getSalesTaxAmount());
        System.out.printf(ColorCodes.CYAN + "📋 Recording Fee:     $%10.2f%n" + ColorCodes.RESET, contract.getRecordingFee());
        System.out.printf(ColorCodes.CYAN + "⚙️  Processing Fee:    $%10.2f%n" + ColorCodes.RESET, contract.getProcessingFee());
        System.out.println(ColorCodes.BRIGHT_WHITE + "-".repeat(30) + ColorCodes.RESET);
        System.out.printf(ColorCodes.BRIGHT_GREEN + ColorCodes.BOLD + "💵 TOTAL PRICE:       $%10.2f%n" + ColorCodes.RESET, contract.getTotalPrice());

        if (contract.isFinanceOption()) {
            System.out.printf(ColorCodes.BRIGHT_BLUE + "💳 Monthly Payment:   $%10.2f%n" + ColorCodes.RESET, contract.getMonthlyPayment());
        } else {
            System.out.println(ColorCodes.BRIGHT_GREEN + "💰 Payment Method: CASH" + ColorCodes.RESET);
        }
    }

    private void displayLeaseContractDetails(LeaseContract contract) {
        System.out.println("\n" + ColorCodes.BRIGHT_BLUE + "=".repeat(50) + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_BLUE + ColorCodes.BOLD + "📝 LEASE CONTRACT DETAILS" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_BLUE + "=".repeat(50) + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "📅 Date: " + contract.getDateOfContract() + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "👤 Customer: " + contract.getCustomerName() + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "📧 Email: " + contract.getCustomerEmail() + ColorCodes.RESET);
        System.out.println();
        System.out.println(ColorCodes.BRIGHT_YELLOW + "🚗 Vehicle: " + contract.getVehicleSold().getYear() + " " +
                contract.getVehicleSold().getMake() + " " + contract.getVehicleSold().getModel() + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_YELLOW + "🆔 VIN: " + contract.getVehicleSold().getVin() + ColorCodes.RESET);
        System.out.println();
        System.out.printf(ColorCodes.CYAN + "💰 Vehicle Price:        $%10.2f%n" + ColorCodes.RESET, contract.getVehicleSold().getPrice());
        System.out.printf(ColorCodes.CYAN + "📈 Expected Ending Value: $%10.2f%n" + ColorCodes.RESET, contract.getExpectedEndingValue());
        System.out.printf(ColorCodes.CYAN + "💸 Lease Fee (7%%):       $%10.2f%n" + ColorCodes.RESET, contract.getLeaseFee());
        System.out.println(ColorCodes.SNOW + "-".repeat(35) + ColorCodes.RESET);
        System.out.printf(ColorCodes.BRIGHT_BLUE + ColorCodes.BOLD + "💵 TOTAL LEASE PRICE:    $%10.2f%n" + ColorCodes.RESET, contract.getTotalPrice());
        System.out.printf(ColorCodes.BRIGHT_GREEN + "💳 Monthly Payment:      $%10.2f%n" + ColorCodes.RESET, contract.getMonthlyPayment());
        System.out.println(ColorCodes.BRIGHT_PURPLE + "⏰ Lease Term: 36 months" + ColorCodes.RESET);
    }

    private int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print(ColorCodes.BRIGHT_RED + "❌ Invalid input. Please enter a number: " + ColorCodes.RESET);
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print(ColorCodes.BRIGHT_RED + "❌ Invalid input. Please enter a valid number: " + ColorCodes.RESET);
            }
        }
    }
}