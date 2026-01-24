package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Expense> expenses = new ArrayList<>();
        int choice = -1;

        do {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Total");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine().trim();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine().trim();

                    BigDecimal amount;
                    while (true) {
                        System.out.print("Enter amount: ");
                        String amtStr = scanner.nextLine().trim();
                        try {
                            amount = new BigDecimal(amtStr); // parse from string to avoid binary rounding
                            if (amount.signum() < 0) {
                                throw new NumberFormatException("negative amount");
                            }
                            // normalize to 2 decimal places for currency
                            amount = amount.setScale(2, RoundingMode.HALF_EVEN);
                            break;
                        } catch (NumberFormatException | ArithmeticException ex) {
                            System.out.println("Invalid amount. Please enter a valid non-negative number (e.g., 12.34). Try again.");
                        }
                    }

                    try {
                        Expense expense = new Expense(title, description, amount);
                        expenses.add(expense);
                        System.out.println("Added: " + expense);
                    } catch (IllegalArgumentException | NullPointerException ex) {
                        System.out.println("Failed to add expense: " + ex.getMessage());
                    }
                    break;

                case 2:
                    BigDecimal total = BigDecimal.ZERO;
                    for (Expense e : expenses) {
                        total = total.add(e.getAmount());
                    }
                    total = total.setScale(2, RoundingMode.HALF_EVEN);
                    System.out.println("Total expenses: " + total.toPlainString());
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Please choose a valid option (1-3).");
                    break;
            }

        } while (choice != 3);

        scanner.close();
    }
}
