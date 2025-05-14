package com.pluralsight;

public class LeaseContract extends Contract {
    // Constants for Rates and Months
    private static final double LEASE_FEE_RATE = 0.07;       // 7% lease fee
    private static final double ENDING_VALUE_RATE = 0.50;    // 50% of original price
    private static final double INTEREST_RATE = 0.04;        // 4% annual interest
    private static final int LEASE_TERM_MONTHS = 36;         // 3 years lease

    // Constructor
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);  // Call abstract superclass constructor
    }

    // Calculate the expected ending value (50% of original vehicle price)
    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * ENDING_VALUE_RATE;
    }

    // Calculate the lease fee (7% of vehicle price)
    public double getLeaseFee() {
        return getVehicleSold().getPrice() * LEASE_FEE_RATE;
    }

    // Override method to calculate total price (lease fee only)
    @Override
    public double getTotalPrice() {
        return getLeaseFee();  // Only the lease fee is considered the "total price"
    }

    // Override method to calculate monthly lease payment using the amortization formula
    @Override
    public double getMonthlyPayment() {
        double price = getVehicleSold().getPrice();
        double monthlyRate = INTEREST_RATE / 12;

        // Amortized monthly payment formula:
        // M = P * (r(1+r)^n) / ((1+r)^n - 1)
        return (price * monthlyRate * Math.pow(1 + monthlyRate, LEASE_TERM_MONTHS)) /
                (Math.pow(1 + monthlyRate, LEASE_TERM_MONTHS) - 1);
    }
}
