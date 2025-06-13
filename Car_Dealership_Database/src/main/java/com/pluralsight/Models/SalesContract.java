package com.pluralsight.Models;

public class SalesContract {

    private int contractId;

    // Contract information
    private String saleDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private String vehicleVin; // Added vehicle VIN

    // Financial information
    private double totalPrice;
    private double monthlyPayment;
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private int financeOption;  // Stores as 1 (true) or 0 (false)
    private double salePrice; // Added sale price

    public SalesContract() {}

    public SalesContract(String saleDate, String customerName, String customerEmail,
                         Vehicle vehicleSold, String vehicleVin, double salePrice, double salesTaxAmount,
                         double recordingFee, double processingFee, int financeOption) {
        this.saleDate = saleDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.vehicleVin = vehicleVin;
        this.salePrice = salePrice;
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.financeOption = financeOption;

        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicle, double salesTax, double recordingFee, double processingFee, int financeOption) {
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
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

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public int getFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(int financeOption) {
        this.financeOption = financeOption;
    }

    public boolean isFinanceOption() {
        return financeOption == 1;
    }

    public void calculateTotalPrice() {
        if (vehicleSold != null) {
            totalPrice = salePrice + salesTaxAmount + recordingFee + processingFee;
        }
    }

    public void calculateMonthlyPayment() {
        if (financeOption == 1 && totalPrice > 0) {
            double interestRate = (salePrice >= 10000) ? 0.0425 : 0.0525;
            int months = (salePrice >= 10000) ? 48 : 24;
            double monthlyRate = interestRate / 12;

            monthlyPayment = (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        } else {
            monthlyPayment = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("Sales Contract - %s | %s | %s | $%.2f | Finance: %s",
                saleDate, customerName, vehicleVin, totalPrice, financeOption == 1 ? "Yes" : "No");
    }
}