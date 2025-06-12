package com.pluralsight.Contracts;

import com.pluralsight.Data.Vehicle;

public class SalesContract extends Contract {

    // Constants for fees and tax rates
    private static final double SALES_TAX_RATE = 0.05;           // 5% sales tax
    private static final double RECORDING_FEE = 100.00;          // Flat $100 fee

    // If the customer wants to finance the vehicle
    private boolean finance;


     //Constructor for a SalesContract
    public SalesContract(String sale, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(sale, customerName, customerEmail, vehicleSold); // Call constructor in Contract
        this.finance = finance;
    }

    // Getter for financing status
    public boolean isFinance() {
        return finance;
    }

    // Setter for financing status
    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    // Calculates the sales tax amount (5% of vehicle price)
    public double getSalesTaxAmount() {
        return getVehicleSold().getPrice() * SALES_TAX_RATE;
    }

    // Returns the flat $100 recording fee
    public double getRecordingFee() {
        return RECORDING_FEE;
    }

    // Calculates the processing fee based on vehicle price
    // $295 if under $10,000; otherwise, $495
    public double getProcessingFee() {
        return getVehicleSold().getPrice() < 10000 ? 295.00 : 495.00;
    }

    // Overrides the abstract method in Contract
    // Computes total price = base + tax + recording + processing
    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getSalesTaxAmount() + getRecordingFee() + getProcessingFee();
    }

    // Overrides the abstract method in Contract
    // Returns monthly payment if financed, otherwise 0.0
    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0; // No financing = no monthly payment

        double totalPrice = getTotalPrice();
        double interestRate;
        int months;

        // Determine interest rate and loan duration based on vehicle price
        if (getVehicleSold().getPrice() >= 10000) {
            interestRate = 0.0425; // 4.25% annual
            months = 48;           // 4 years
        } else {
            interestRate = 0.0525; // 5.25% annual
            months = 24;           // 2 years
        }

        // Monthly interest rate
        double monthlyRate = interestRate / 12;

        // Use loan amortization formula:
        // M = P * (r(1+r)^n) / ((1+r)^n - 1)
        return totalPrice * (monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
    }
}
