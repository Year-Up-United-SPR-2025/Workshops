package com.pluralsight;

public class futureVal {
    public static void calculate(double principal, double annualInterestRate, int years) {
        double annRate = annualInterestRate / 100;
        int daysPerYear = 365;
        int totalDays = daysPerYear * years;

        double futureValue = principal * Math.pow(1 + (annRate / daysPerYear), totalDays);
        double interestEarned = futureValue - principal;

        System.out.printf("Future Value: $%.2f%n", futureValue);
        System.out.printf("Total Interest Earned: $%.2f%n", interestEarned);
    }
}