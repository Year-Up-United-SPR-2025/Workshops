package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phoneNumber;

    private ArrayList<String> inventory;

    public void GivenCars() {
        inventory = new ArrayList<>();
        inventory.add("D & B Used Cars|111 Old Benbrook Rd|817-555-5555");
        inventory.add("10112|1993|Ford|Explorer|SUV|Red|525123|995.00");
        inventory.add("37846|2001|Ford|Ranger|truck|Yellow|172544|1995.00");
        inventory.add("44901|2012|Honda|Civic|SUV|Gray|103221|6995.00");
        printGivenNames();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public void printGivenNames() {
        for (String inventory : inventory) {
            System.out.println(inventory);
        }
    }
}

