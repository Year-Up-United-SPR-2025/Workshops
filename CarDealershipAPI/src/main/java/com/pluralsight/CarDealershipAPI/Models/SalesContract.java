package com.pluralsight.CarDealershipAPI.Models;

public class SalesContract {
    private int contractId;
    private String saleDate;
    private String customerName;
    private String customerEmail;
    private String vehicleVin;
    private double totalPrice;
    private double monthlyPayment;
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private int financeOption;
    private double salePrice;

    // Constructor
    public SalesContract(int contractId, String saleDate, String customerName, String customerEmail,
                         String vehicleVin, double totalPrice, double monthlyPayment,
                         double salesTaxAmount, double recordingFee, double processingFee,
                         int financeOption, double salePrice) {
        this.contractId = contractId;
        this.saleDate = saleDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleVin = vehicleVin;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.financeOption = financeOption;
        this.salePrice = salePrice;
    }

    public SalesContract() {

    }

    // Getters and Setters
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

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
