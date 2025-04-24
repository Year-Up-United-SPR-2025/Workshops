package com.pluralsight;
import java.util.Scanner;

public class mortgagecal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter the loan amount (principal): $");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate : ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter the loan term in years: ");
        int loanYears = scanner.nextInt();

        // Convert annual interest rate from percent to decimal
        double intRate = annualInterestRate / 100;

        // Calculate the number of payments (months)
        int numPayments = loanYears * 12;

        // Monthly interest rate
        double MoniRate = intRate / 12;

        // Monthly payment formula: M = P * (i*(1+i)^n) / ((1+i)^n - 1)
        double monthlyPayment = principal * (intRate * Math.pow(1 + intRate, numPayments)) / (Math.pow(1 + MoniRate, numPayments) - 1);

        // Total interest paid
        double totalInterest = (monthlyPayment * numPayments) - principal;

        // Output results
        System.out.printf("Monthly Payment: $%.2f%n", monthlyPayment);
        System.out.printf("Total Interest Paid Over Life of Loan: $%.2f%n", totalInterest);
    }
}