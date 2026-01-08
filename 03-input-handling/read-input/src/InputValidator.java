import java.util.Scanner;

/**
 * Utility class for validating and safely reading user input.
 * This class handles common input validation scenarios to prevent crashes.
 */
public class InputValidator {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Reads and validates an integer input within a specified range.
     * Keeps prompting until valid input is received.
     *
     * @param prompt The message to display to the user
     * @param min Minimum acceptable value (inclusive)
     * @param max Maximum acceptable value (inclusive)
     * @return Valid integer within the specified range
     */
    public static int getValidInteger(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                // Check if input is empty
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty! Please try again.");
                    continue;
                }

                int value = Integer.parseInt(input);

                // Validate range
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf(" Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Reads and validates a non-empty string input.
     * Keeps prompting until valid input is received.
     *
     * @param prompt The message to display to the user
     * @return Valid non-empty string
     */
    public static String getValidString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                // Additional validation: check if name contains only letters and spaces
                if (input.matches("^[a-zA-Z\\s]+$")) {
                    return input;
                }
                System.out.println(" Name should contain only letters and spaces!");
            } else {
                System.out.println(" Input cannot be empty! Please try again.");
            }
        }
    }

    /**
     * Reads and validates a double/decimal input within a specified range.
     * Keeps prompting until valid input is received.
     *
     * @param prompt The message to display to the user
     * @param min Minimum acceptable value (inclusive)
     * @param max Maximum acceptable value (inclusive)
     * @return Valid double within the specified range
     */
    public static double getValidDouble(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty! Please try again.");
                    continue;
                }

                double value = Double.parseDouble(input);

                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf(" Please enter a number between %.2f and %.2f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Closes the scanner when the program is done.
     * Should be called at the end of the program.
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}

