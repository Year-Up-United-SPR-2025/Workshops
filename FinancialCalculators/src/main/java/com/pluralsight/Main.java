package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        while (continueCalculating) {
            System.out.println("\nChoose calculator:");
            System.out.println("1. Mortgage Calculator");
            System.out.println("2. Annuity Calculator");
            System.out.println("3. Future Value Calculator");
            System.out.print("Enter choice (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter loan amount: $");
                    double principal = scanner.nextDouble();

                    System.out.print("Enter annual interest rate: ");
                    double rate = scanner.nextDouble();

                    System.out.print("Enter loan term in years: ");
                    int years = scanner.nextInt();

                    mortgageCal.calculate(principal, rate, years);
                    break;

                case 2:
                    System.out.print("Enter monthly payout: $");
                    double monthly = scanner.nextDouble();

                    System.out.print("Enter annual interest rate: ");
                    double annuityRate = scanner.nextDouble();

                    System.out.print("Enter number of years: ");
                    int annuityYears = scanner.nextInt();

                    presentVal.calculate(monthly, annuityRate, annuityYears);
                    break;

                case 3:
                    System.out.print("Enter deposit amount: $");
                    double deposit = scanner.nextDouble();

                    System.out.print("Enter annual interest rate: ");
                    double futureRate = scanner.nextDouble();

                    System.out.print("Enter number of years: ");
                    int futureYears = scanner.nextInt();

                    futureVal.calculate(deposit, futureRate, futureYears);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.print("\nWould you like to use another calculator? (yes/no): ");
            scanner.nextLine(); // consume newline
            String again = scanner.nextLine().trim().toLowerCase();

            if (!again.equals("yes") && !again.equals("y")) {
                continueCalculating = false;
                System.out.println("Thank you for using the Finance Calculator!");
            }
        }

        scanner.close();
    }
}