package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File_Reader_and_Writer {

    // Name of the product file
    private static final String FILE_NAME = "Products.csv";

    // In-memory shopping cart to hold product records
    public static List<String> cart = new ArrayList<>();

    /**
     * Save a new product to the file.
     * Format: sku|productName|productPrice|department
     */
    public static void saveProducts(String sku, String productName, double productPrice, String department) {
        String addRecord = sku + "|" + productName + "|" + productPrice + "|" + department;

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(addRecord + "\n");
            System.out.println("Product saved.");
        } catch (IOException e) {
            System.out.println(ColorCodes.RED + ColorCodes.BOLD + "Error writing to file: " + e.getMessage() + ColorCodes.RESET );
        }
    }

    /**
     * Read and display all products from the file.
     */
    public static void readProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Print Header with color
            System.out.printf(ColorCodes.BRIGHT_CYAN + "%-10s | %-35s | %-13s | %-15s\n" + ColorCodes.RESET,
                    "SKU", "Name", "Price", "Department");
            System.out.println("-----------------------------------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    System.out.printf(
                            ColorCodes.YELLOW + "%-10s" + ColorCodes.RESET + " | " +
                                    ColorCodes.GREEN + "%-35s" + ColorCodes.RESET + " | " +
                                    ColorCodes.BRIGHT_YELLOW + "%-13s" + ColorCodes.RESET + " | " +
                                    ColorCodes.PURPLE + "%-15s" + ColorCodes.RESET + "\n",
                            parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println(ColorCodes.RED + ColorCodes.BOLD +"Error reading from file: " + e.getMessage() + ColorCodes.RESET);
        }
    }

    /**
     * Add a product to the shopping cart by SKU.
     */
    public static void addToCart(String sku) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                // If SKU matches, add to cart
                if (parts.length == 4 && parts[0].equalsIgnoreCase(sku)) {
                    cart.add(line);
                    System.out.println("Added to cart: " + parts[1]);
                    return;
                }
            }
            System.out.println(ColorCodes.RED + ColorCodes.BOLD +"Product with SKU " + sku + " not found." + ColorCodes.RESET);
        } catch (IOException e) {
            System.out.println("Error reading product: " + e.getMessage());
        }
    }

    /**
     * Remove a product from the shopping cart by SKU.
     */
    public static void removeFromCart(String sku) {
        for (int i = 0; i < cart.size(); i++) {
            String[] parts = cart.get(i).split("\\|");

            if (parts.length == 4 && parts[0].equalsIgnoreCase(sku)) {
                cart.remove(i);
                System.out.println("Removed from cart: " + parts[1]);
                return;
            }
        }
        System.out.println("Product with SKU " + sku + " not found in cart.");
    }

    /**
     * Display all products currently in the cart.
     */
    public static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println(ColorCodes.RED + "Your cart is empty." + ColorCodes.RESET);
            return;
        }

        System.out.println(ColorCodes.BRIGHT_CYAN + "Your Cart:" + ColorCodes.RESET);
        System.out.printf(ColorCodes.BRIGHT_CYAN + "%-10s | %-35s | %-13s | %-15s\n" + ColorCodes.RESET,
                "SKU", "Name", "Price", "Department");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (String item : cart) {
            String[] parts = item.split("\\|");
            if (parts.length == 4) {
                System.out.printf(
                        ColorCodes.YELLOW + "%-10s" + ColorCodes.RESET + " | " +
                                ColorCodes.GREEN + "%-35s" + ColorCodes.RESET + " | " +
                                ColorCodes.BRIGHT_YELLOW + "%-13s" + ColorCodes.RESET + " | " +
                                ColorCodes.PURPLE + "%-15s" + ColorCodes.RESET + "\n",
                        parts[0], parts[1], parts[2], parts[3]);
            }
        }
    }
}
