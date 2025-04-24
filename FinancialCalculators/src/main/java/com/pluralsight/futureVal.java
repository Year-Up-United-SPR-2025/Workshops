package com.pluralsight;
import java.util.Scanner;

public class futureVal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter the deposit amount: $");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate : ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter the number of years until maturity: ");
        int years = scanner.nextInt();

        // Convert annual interest rate to decimal
        double AnnIRate = annualInterestRate / 100;

        // Number of days in a year for compounding
        int daysPerYear = 365;
        int totalDays = daysPerYear * years;

        // Compound interest formula: FV = P × (1 + r/365)^(365 × t)
        double futureValue = principal * Math.pow(1 + (AnnIRate / daysPerYear), totalDays);

        // Total interest earned
        double interestEarned = futureValue - principal;

        // Output results
        System.out.printf("Future Value: $%.2f%n", futureValue);
        System.out.printf("Total Interest Earned: $%.2f%n", interestEarned);
    }
}