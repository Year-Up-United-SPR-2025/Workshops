package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    // Home screen
    public static void showHomeScreen() {
        while (true) {
            System.out.println("\n=== Welcome to Coders Electronics ===");
            System.out.println("\n=== How may we Serve you Today===");
            System.out.println(ColorCodes.BRIGHT_PURPLE + "\n1. Display Products" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_PURPLE + "2. Display Cart" + ColorCodes.RESET);
            System.out.println(ColorCodes.BRIGHT_PURPLE + "3. Exit" + ColorCodes.RESET);
            System.out.print("\nChoose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showProductScreen();
                case "2" -> showCartScreen();
                case "3" -> {
                    System.out.println("Thank you for shopping!");
                    return;
                }
                default ->
                        System.out.println(ColorCodes.RED + ColorCodes.BOLD + "Invalid choice. Try again." + ColorCodes.RESET);
            }
        }
    }

    public static void showProductScreen() {
        while (true) {
            System.out.println("\n=== Product List ===");
            File_Reader_and_Writer.readProducts();

            System.out.println("\nOptions:");
            System.out.println("1. Search Products by SKU");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Go Back");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Enter SKU to search: ");
                    String skuSearch = scanner.nextLine();
                    searchProductBySku(skuSearch);
                }
                case "2" -> {
                    System.out.print("Enter SKU to add to cart: ");
                    String skuToAdd = scanner.nextLine();
                    File_Reader_and_Writer.addToCart(skuToAdd);
                }
                case "3" -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void searchProductBySku(String sku) {
        System.out.println("\n=== Search Result ===");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("Products.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4 && parts[0].equalsIgnoreCase(sku)) {
                    System.out.printf("%-10s | %-10s | %-13s | %-12s\n",
                            parts[0], parts[1], parts[2], parts[3]);
                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println(ColorCodes.RED + ColorCodes.BOLD + "Error reading file: " + e.getMessage() + ColorCodes.RESET);
        }

        if (!found) {
            System.out.println(ColorCodes.RED + ColorCodes.BOLD + "Product not found." + ColorCodes.RESET);
        }
    }

    public static void showCartScreen() {
        while (true) {
            System.out.println("\n=== Your Cart ===");
            File_Reader_and_Writer.viewCart();
            double total = calculateCartTotal();
            System.out.printf(ColorCodes.RED + "Total: $%.2f\n" + ColorCodes.RESET, total);

            System.out.println("\nOptions:");
            System.out.println("1. Remove Product from Cart");
            System.out.println("2. Check Out");
            System.out.println("3. Go Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter SKU to remove: ");
                    String skuToRemove = scanner.nextLine();
                    File_Reader_and_Writer.removeFromCart(skuToRemove);
                }
                case "2" -> {
                    System.out.println("Checkout complete. Thank you!");
                    System.exit(0);
                }
                case "3" -> {
                    return;
                }
                default -> System.out.println(ColorCodes.RED + ColorCodes.BOLD + "Invalid choice." + ColorCodes.RESET);
            }
        }
    }

    private static double calculateCartTotal() {
        double total = 0.0;
        try {
            for (String item : File_Reader_and_Writer.cart) {
                String[] parts = item.split("\\|");
                if (parts.length == 4) {
                    total += Double.parseDouble(parts[2]);
                }
            }
        } catch (Exception e) {
            System.out.println(ColorCodes.RED + ColorCodes.BOLD + "Error calculating total." + ColorCodes.RESET);
        }
        return total;
    }
}
