package com.pluralsight;

import java.io.*;

public class File_Reader_and_Writer {


    private static final String FILE_NAME = "Products.csv";

    //save new products
    public static void saveProducts(String sku, String productName, double productPrice, String department) {
        // Format: sku|productName|productPrice|department
        String addRecord = sku + "|" + productName + "|" + productPrice + "|" + department;

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(addRecord + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //Read and Display products
    public static void readProducts(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;

            //Printer Header
            System.out.println("SKU        |Name       |Price        |Department");
            System.out.println("----------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                // Split the line by "|"
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    // Print all parts in a single line
                    System.out.printf("%-10s | %-8s | %-16s | %-12s ",
                            parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}

