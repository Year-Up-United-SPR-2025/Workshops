package com.pluralsight;
import java.util.Scanner;

public class presentVal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Get User Input
        System.out.println("Enter the monthly payout: $");
        double monthlyPayment = scanner.nextDouble();

        System.out.println("Enter the annual interest rate: ");
        double annualInterestRate = scanner.nextDouble();

        System.out.println("Enter the number of years annuity you have: ");
        int years = scanner.nextInt();

        //Convert Interest rate from precent to Decimal
        double IntRate = annualInterestRate / 100;

        //Monthly Interest Rate
        double iRate = IntRate / 12;

        //Total Number of Payments throughout the years
        int numYears = years * 12;

        // Present Value of Ordinary Annuity Formula
        double presentValue = monthlyPayment * (1 - Math.pow(1 + iRate, -numYears)) / iRate;

        // Output results
        System.out.printf("Present Value of the Annuity: $%.2f%n", presentValue);
    }
}
