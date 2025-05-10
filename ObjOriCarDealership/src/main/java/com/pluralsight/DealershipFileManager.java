package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class DealershipFileManager {

    private static final String FILE_NAME = "inventory.csv";

    /**
     * Reads the dealership data from a pipe-delimited file and returns a Dealership object.
     */
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;

            // Read first line as dealership info
            if ((line = reader.readLine()) != null) {
                String[] header = line.split("\\|");
                if (header.length == 3) {
                    String name = header[0];
                    String address = header[1];
                    String phone = header[2];
                    dealership = new Dealership(name, address, phone);
                } else {
                    System.out.println("The line entered needs to be corrected: " + line);
                    return new Dealership("Unknown", "Unknown", "Unknown");
                }
            }

            // Now read vehicle lines
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length != 7) {
                    System.out.println("Skipping wrong formatted line: " + line);
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
                    System.out.println("Skipping line due to number format error: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory.csv: " + e.getMessage());
        }

        return dealership != null ? dealership : new Dealership("Unknown", "Unknown", "Unknown");
    }

    /**
     * Saves a Dealership object and its vehicle inventory to the csv file.
     */
    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Write dealership info
            writer.println(dealership.getName());
            writer.println(dealership.getAddress());
            writer.println(dealership.getPhoneNumber());

            // Write vehicle info
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(),
                        v.getOdometer(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error saving dealership to file: " + e.getMessage());
        }
    }
}

