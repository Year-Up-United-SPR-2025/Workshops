package com.pluralsight;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Dealership dealership;
    private final Scanner scanner;

    public UserInterface() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
        scanner = new Scanner(System.in);
    }

    public void display() {
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = getInt(ColorCodes.BOLD + "Choose an Option: ");

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
                    processGetByTypeRequest();
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
                    processViewContracts();
                case 11:
                    processSatisfactionSurvey();
                    break;
                case 99:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }

        // Save dealership to file before exiting
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
        System.out.println("Thank you for visiting. Goodbye!");
    }

    // Print menu to the console
    private void printMenu() {
        System.out.println("\n" + ColorCodes.CYAN + ColorCodes.BOLD + "Vehicle Inventory Menu:" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "1 - Find vehicles within a price range" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "2 - Find vehicles by make / model" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "3 - Find vehicles by year range" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "4 - Find vehicles by color" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "5 - Find vehicles by mileage range" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_GREEN + "6 - Find vehicles by type (car, truck, SUV, van)" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_YELLOW + "7 - List ALL vehicles" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_BLUE + "8 - Add a vehicle" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_RED + "9 - Remove a vehicle" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_CYAN + "10 - View all contracts" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_PURPLE + "11 - Satisfied With Your Vehicle (Y/N)" + ColorCodes.RESET);
        System.out.println(ColorCodes.BRIGHT_WHITE + "99 - Quit" + ColorCodes.RESET);
    }

    // Request and display vehicles by price
    private void processGetByPriceRequest() {
        double min = getDouble("Minimum price: ");
        double max = getDouble("Maximum price: ");
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    // Request and display vehicles by make and model
    private void processGetByMakeModelRequest() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    // Request and display vehicles by year range
    private void processGetByYearRequest() {
        int min = getInt("Minimum year: ");
        int max = getInt("Maximum year: ");
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    // Request and display vehicles by color
    private void processGetByColorRequest() {
        System.out.print("Color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    // Request and display vehicles by mileage range
    private void processGetByMileageRequest() {
        int min = getInt("Minimum mileage: ");
        int max = getInt("Maximum mileage: ");
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    // Request and display vehicles by type
    private void processGetByTypeRequest() {
        System.out.print("Type (car, truck, SUV, van): ");
        String type = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    // Display all vehicles
    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    // Add a new vehicle to the dealership
    private void processAddVehicleRequest() {
        System.out.println("Enter details of the new vehicle:");
        int year = getInt("Year: ");
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        int mileage = getInt("Mileage: ");
        double price = getDouble("Price: ");

        Vehicle vehicle = new Vehicle(year, make, model, type, color, mileage, price);

        // Add the vehicle to the dealership
        dealership.addVehicle(vehicle);

        // Print vehicle details in the desired format
        System.out.printf("%d|%s|%s|%s|%s|%d|%.2f%n", year, make, model, type, color, mileage, price);

        System.out.println("Vehicle added successfully!");
    }

    // Remove a vehicle from the dealership
    private void processRemoveVehicleRequest() {
        System.out.println("Enter details of the vehicle to remove:");
        int year = getInt("Year: ");
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();

        List<Vehicle> matches = dealership.getVehiclesByMakeModel(make, model).stream()
                .filter(v -> v.getYear() == year)
                .toList();

        if (matches.isEmpty()) {
            System.out.println("No matching vehicles found.");
        } else {
            Vehicle toRemove = matches.get(0); // remove first match
            dealership.removeVehicle(toRemove);
            System.out.println("Vehicle removed successfully.");
        }
    }

    // Displays all contracts from the contracts.csv file
    private void processViewContracts() {
        // Print a header
        System.out.println(ColorCodes.BOLD + "Contracts:" + ColorCodes.RESET);

        try (Scanner fileScanner = new Scanner(new File("Contract.csv"))) {
            // Read the file line-by-line
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine(); // Read the next line
                String[] parts = line.split("\\|");   // Split the line by '|'

                // If it's a SALE contract
                if (parts[0].equalsIgnoreCase("SALE")) {
                    // Format and print the sale contract details
                    System.out.printf(
                            "\nSALE | Sale: %s | Customer: %s | Email: %s\n" +
                                    "Vehicle: %s %s %s (%s, %s, %,d mi, $%.2f)\n" +
                                    "Tax: $%.2f | Fees: $%.2f + $%.2f | Total: $%.2f | Finance: %s | Monthly: $%.2f\n",
                            parts[1],  // Sale
                            parts[2],  // Customer Name
                            parts[3],  // Customer Email
                            parts[5],  // Vehicle Year
                            parts[6],  // Make
                            parts[7],  // Model
                            parts[8],  // Type
                            parts[9],  // Color
                            Integer.parseInt(parts[10]), // Mileage
                            Double.parseDouble(parts[11]), // Price
                            Double.parseDouble(parts[12]), // Sales Tax
                            Double.parseDouble(parts[13]), // Recording Fee
                            Double.parseDouble(parts[14]), // Processing Fee
                            Double.parseDouble(parts[15]), // Total Price
                            parts[16],  // Financed (YES/NO)
                            Double.parseDouble(parts[17])  // Monthly Payment
                    );
                }
                // If it's a LEASE contract
                else if (parts[0].equalsIgnoreCase("LEASE")) {
                    // Format and print the lease contract details
                    System.out.printf(
                            "\nLEASE | Sale: %s | Customer: %s | Email: %s\n" +
                                    "Vehicle: %s %s %s (%s, %s, %,d mi, $%.2f)\n" +
                                    "Expected Value: $%.2f | Lease Fee: $%.2f | Total: $%.2f | Monthly: $%.2f\n",
                            parts[1],  // Sale
                            parts[2],  // Customer Name
                            parts[3],  // Customer Email
                            parts[5],  // Vehicle Year
                            parts[6],  // Make
                            parts[7],  // Model
                            parts[8],  // Type
                            parts[9],  // Color
                            Integer.parseInt(parts[10]), // Mileage
                            Double.parseDouble(parts[11]), // Price
                            Double.parseDouble(parts[12]), // Expected Ending Value
                            Double.parseDouble(parts[13]), // Lease Fee
                            Double.parseDouble(parts[14]), // Total Price
                            Double.parseDouble(parts[15])  // Monthly Payment
                    );
                } else {
                    // If the contract type is not recognized
                    System.out.println("Unknown contract type in file.");
                }
            }
        } catch (Exception e) {
            // If file not found or there's an error, print it
            System.out.println("Error reading contracts file: " + e.getMessage());
        }
    }

    // Ask the user if they're satisfied
    private void processSatisfactionSurvey() {
        System.out.print("Are you satisfied with your vehicle? (Y/N): ");
        String answer = scanner.nextLine().trim().toUpperCase();

        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("We’re glad to hear that! Thank you!");
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("We’re sorry to hear that. Please contact customer service:");
            System.out.println("585-555-2222");
        } else {
            System.out.println("Invalid input. Please enter Y or N.");
        }
    }

    // Display a list of vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            //now its under each field
            System.out.println("Year | Make | Model | Type | Color | Mileage | Price");
            System.out.println("----------------------------------------------------------");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    // Utility methods for input
    private int getInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private double getDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }
}
