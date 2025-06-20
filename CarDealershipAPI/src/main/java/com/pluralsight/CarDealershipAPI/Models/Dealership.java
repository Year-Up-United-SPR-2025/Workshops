package com.pluralsight.CarDealershipAPI.Models;

public class Dealership {
    private int dealershipId;
    private String name;
    private String address;
    private String phone;
    private String email;

    // Constructors
    public Dealership() {}

    public Dealership(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public int getDealershipId() { return dealershipId; }
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
