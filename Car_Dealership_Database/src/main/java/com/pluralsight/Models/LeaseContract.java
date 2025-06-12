package com.pluralsight.Models;

public class LeaseContract {

    private int id;
    private String dateOfContract;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double expectedEndingValue;
    private double leaseFee;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract() {
    }

    public LeaseContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold, double expectedEndingValue, double leaseFee) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;

        // Calculate totals
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void calculateTotalPrice() {
        if (vehicleSold != null) {
            totalPrice = (vehicleSold.getPrice() - expectedEndingValue) + leaseFee;
        }
    }

    public void calculateMonthlyPayment() {
        if (totalPrice > 0) {
            // Lease is always 36 months with 4% interest
            double monthlyRate = 0.04 / 12;
            int months = 36;

            monthlyPayment = (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        }
    }

    @Override
    public String toString() {
        return String.format("Lease Contract - %s | %s | %s | $%.2f",
                dateOfContract, customerName, vehicleSold.getVin(), totalPrice);
    }
}