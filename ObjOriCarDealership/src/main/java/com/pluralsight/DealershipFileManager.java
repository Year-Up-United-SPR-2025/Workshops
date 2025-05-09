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

        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            // Read dealership info
            String name = scanner.nextLine();
            String address = scanner.nextLine();
            String phone = scanner.nextLine();

            dealership = new Dealership(name, address, phone);

            // Read vehicle data line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                int year = Integer.parseInt(parts[0]);
                String make = parts[1];
                String model = parts[2];
                String type = parts[3];
                String color = parts[4];
                int mileage = Integer.parseInt(parts[5]);
                double price = Double.parseDouble(parts[6]);

                Vehicle vehicle = new Vehicle(year, make, model, type, color, mileage, price);
                dealership.addVehicle(vehicle);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_NAME);
        }

        return dealership;
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
                        v.getMake(), v.getColor(),
                        v.getMake(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error saving dealership to file: " + e.getMessage());
        }
    }
}

