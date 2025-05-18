package com.pluralsight;

public class Product {
    private String productName;
    private double productPrice;
    private String Department;

    public Product(String productName, double productPrice, String department) {
        this.productName = productName;
        this.productPrice = productPrice;
        Department = department;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
