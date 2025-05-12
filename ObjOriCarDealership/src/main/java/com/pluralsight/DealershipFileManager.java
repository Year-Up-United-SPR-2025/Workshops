package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    private static final String FILE_NAME = "inventory.csv";

    /**
     * Reads the dealership data from a pipe-delimited file and returns a Dealership object.
     */
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String name = reader.readLine();
            String address = reader.readLine();
            String phone = reader.readLine();

            if (name == null || address == null || phone == null) {
                System.out.println("Error: Missing dealership information in file.");
                return null;
            }

            dealership = new Dealership(name, address, phone);

            String vehicleLine;
            while ((vehicleLine = reader.readLine()) != null) {
                String[] parts = vehicleLine.split("\\|");

                if (parts.length != 7) {
                    //depending on the theme your using the colors will be hard to make out
                    System.out.println(ColorCodes.RED_BACKGROUND + ColorCodes.BOLD + ColorCodes.BLACK + "Skipping wrongly formatted line: " + vehicleLine + ColorCodes.RESET);
                    continue;
                }

                try {
                    int year = Integer.parseInt(parts[0]);
                    String make = parts[1];
                    String model = parts[2];
                    String type = parts[3];
                    String color = parts[4];
                    int mileage = Integer.parseInt(parts[5]);
                    double price = Double.parseDouble(parts[6]);

                    Vehicle vehicle = new Vehicle(year, make, model, type, color, mileage, price);
                    dealership.addVehicle(vehicle);
                } catch (NumberFormatException e) {
                    //depending on the theme your using the colors will be hard to make out
                    System.out.println(ColorCodes.BLUE_BACKGROUND + ColorCodes.BOLD + ColorCodes.BLACK + "Skipping line due to number format error: " + vehicleLine + ColorCodes.RESET);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading " + FILE_NAME + ": " + e.getMessage());
        }

        return dealership;
    }

    /**
     * Saves a Dealership object and its vehicle inventory to the CSV file.
     */
    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(dealership.getName());
            writer.println(dealership.getAddress());
            writer.println(dealership.getPhoneNumber());

            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error saving dealership to file: " + e.getMessage());
        }
    }
}
