package com.pluralsight.Models;

public abstract class Contract {

    protected int id;
    protected String dateOfContract;
    protected String customerName;
    protected String customerEmail;
    protected Vehicle vehicleSold;
    protected double totalPrice;
    protected double monthlyPayment;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDateOfContract() { return dateOfContract; }
    public void setDateOfContract(String dateOfContract) { this.dateOfContract = dateOfContract; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public Vehicle getVehicleSold() { return vehicleSold; }
    public void setVehicleSold(Vehicle vehicleSold) { this.vehicleSold = vehicleSold; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }

    public abstract void calculateTotalPrice();
    public abstract void calculateMonthlyPayment();
}