package com.pluralsight.CarDealershipAPI.Models;

import java.time.LocalDate;

public class LeaseContract {
    private int leaseId;
    private String vehicleVin;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyPayment;
    private double totalPrice;
    private double salesTax;
    private double recordingFee;
    private double processingFee;

    // Constructors
    public LeaseContract() {}

    public LeaseContract(String vehicleVin, int customerId, LocalDate startDate,
                         LocalDate endDate, double monthlyPayment, double totalPrice,
                         double salesTax, double recordingFee, double processingFee) {
        this.vehicleVin = vehicleVin;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyPayment = monthlyPayment;
        this.totalPrice = totalPrice;
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
    }

    // Getters and Setters
    public int getLeaseId() { return leaseId; }
    public void setLeaseId(int leaseId) { this.leaseId = leaseId; }

    public String getVehicleVin() { return vehicleVin; }
    public void setVehicleVin(String vehicleVin) { this.vehicleVin = vehicleVin; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getSalesTax() { return salesTax; }
    public void setSalesTax(double salesTax) { this.salesTax = salesTax; }

    public double getRecordingFee() { return recordingFee; }
    public void setRecordingFee(double recordingFee) { this.recordingFee = recordingFee; }

    public double getProcessingFee() { return processingFee; }
    public void setProcessingFee(double processingFee) { this.processingFee = processingFee; }
}
