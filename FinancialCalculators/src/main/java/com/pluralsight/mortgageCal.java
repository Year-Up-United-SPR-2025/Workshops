package com.pluralsight;

public class mortgageCal {
    public static void calculate(double principal, double annualInterestRate, int loanYears) {
        double intRate = annualInterestRate / 100;
        int numPayments = loanYears * 12;
        double monRate = intRate / 12;

        double monthlyPayment = principal * (monRate * Math.pow(1 + monRate, numPayments)) /
                (Math.pow(1 + monRate, numPayments) - 1);

        double totalInterest = (monthlyPayment * numPayments) - principal;

        System.out.printf("Monthly Payment: $%.2f%n", monthlyPayment);
        System.out.printf("Total Interest Paid: $%.2f%n", totalInterest);
    }
}