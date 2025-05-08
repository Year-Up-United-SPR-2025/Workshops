package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nVehicle Inventory Menu:");
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Satisfied With Your Vehicle (Y/N)");
            System.out.println("99 - Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Finding vehicles within a price range: \n");
                    // Calls method findByPriceRange();
                    break;
                case 2:
                    System.out.println("Finding vehicles by make/model \n");
                    // Calls method findByMakeModel();
                    break;
                case 3:
                    System.out.println("Finding vehicles by year range \n");
                    // Calls method findByYearRange();
                    break;
                case 4:
                    System.out.println("Finding vehicles by color \n");
                    // Calls method findByColor();
                    break;
                case 5:
                    System.out.println("Finding vehicles by mileage range \n");
                    // Calls method findByMileageRange();
                    break;
                case 6:
                    System.out.println("Finding vehicles by type \n");
                    // Calls method findByType();
                    break;
                case 7:
                    System.out.println("Listing all vehicles \n");
                    // Calls method listAllVehicles();
                    break;
                case 8:
                    System.out.println("Adding a new vehicle \n");
                    // Calls method addVehicle();
                    break;
                case 9:
                    System.out.println("Removing a vehicle \n");
                    // Calls method removeVehicle();
                    break;
                case 10:
                    System.out.println("Are You Satisfied With Your Vehicle \n");
                    //Calls method satisfiedVehicle();
                    break;
                case 99:
                    System.out.println("GoodBye and Good Luck with your Car.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
