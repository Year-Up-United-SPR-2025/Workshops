package com.pluralsight.CarDealershipAPI.Models;

public class LeaseContract {

    private int id;
    private String dateOfContract;
    private String leaseStart;
    private String leaseEnd;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double expectedEndingValue;
    private double leaseFee;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract() {
    }

    public LeaseContract(String dateOfContract, String leaseStart, String leaseEnd,
                         String customerName, String customerEmail,
                         Vehicle vehicleSold, double expectedEndingValue, double leaseFee) {
        this.dateOfContract = dateOfContract;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;

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

    public String getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(String leaseStart) {
        this.leaseStart = leaseStart;
    }

    public String getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(String leaseEnd) {
        this.leaseEnd = leaseEnd;
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
            double monthlyRate = 0.04 / 12;
            int months = 36;

            monthlyPayment = (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        }
    }

    @Override
    public String toString() {
        return String.format("Lease Contract - %s | %s | %s | Start: %s | End: %s | $%.2f",
                dateOfContract, customerName, vehicleSold.getVin(), leaseStart, leaseEnd, totalPrice);
    }
}