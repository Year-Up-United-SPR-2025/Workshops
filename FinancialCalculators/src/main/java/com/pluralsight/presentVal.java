package com.pluralsight;

public class presentVal {
    public static void calculate(double monthlyPayment, double annualInterestRate, int years) {
        double intRate = annualInterestRate / 100;
        double iRate = intRate / 12;
        int numPayments = years * 12;

        double presentValue = monthlyPayment * (1 - Math.pow(1 + iRate, -numPayments)) / iRate;

        System.out.printf("Present Value of the Annuity: $%.2f%n", presentValue);
    }
}