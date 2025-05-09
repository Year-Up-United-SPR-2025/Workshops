package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phoneNumber;

    private ArrayList<Vehicle> inventory;

    public Dealership(){
        inventory = new ArrayList<>();
    }

}

