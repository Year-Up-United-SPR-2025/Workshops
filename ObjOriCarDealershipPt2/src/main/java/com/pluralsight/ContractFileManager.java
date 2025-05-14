package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractFileManager {

    //File that the contracts end up
    private static final String ContractsFile = "Contract.csv";

    //Save a contract (sales / lease) to the Contracts File
    public void saveContract(Contract contract) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ContractsFile, true))) {
            Vehicle v = contract.getVehicleSold();
            StringBuilder sb = new StringBuilder();

            //Appends Sale Or Lease contract information
            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;

                sb.append("SALE").append("|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(v.getVin()).append("|")
                        .append(v.getYear()).append("|")
                        .append(v.getMake()).append("|")
                        .append(v.getModel()).append("|")
                        .append(v.getVehicleType()).append("|")
                        .append(v.getColor()).append("|")
                        .append(v.getOdometer()).append("|")
                        .append(String.format("%.2f", v.getPrice())).append("|")
                        .append(String.format("%.2f", sc.getSalesTaxAmount())).append("|")
                        .append(String.format("%.2f", sc.getRecordingFee())).append("|")
                        .append(String.format("%.2f", sc.getProcessingFee())).append("|")
                        .append(String.format("%.2f", sc.getTotalPrice())).append("|")
                        .append(sc.isFinance() ? "YES" : "NO").append("|")
                        .append(String.format("%.2f", sc.getMonthlyPayment()));

            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;

                sb.append("LEASE").append("|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(v.getVin()).append("|")
                        .append(v.getYear()).append("|")
                        .append(v.getMake()).append("|")
                        .append(v.getModel()).append("|")
                        .append(v.getVehicleType()).append("|")
                        .append(v.getColor()).append("|")
                        .append(v.getOdometer()).append("|")
                        .append(String.format("%.2f", v.getPrice())).append("|")
                        .append(String.format("%.2f", lc.getExpectedEndingValue())).append("|")
                        .append(String.format("%.2f", lc.getLeaseFee())).append("|")
                        .append(String.format("%.2f", lc.getTotalPrice())).append("|")
                        .append(String.format("%.2f", lc.getMonthlyPayment()));
            }

            // Write to file
            writer.println(sb.toString());

        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}