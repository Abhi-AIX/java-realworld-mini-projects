import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Advanced Input Validator with additional validation methods
 * that are commonly used in enterprise applications.
 *
 * This is an OPTIONAL extension showing more sophisticated validation.
 */
public class AdvancedInputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    // Common regex patterns
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN =
        Pattern.compile("^\\d{10}$|^\\d{3}-\\d{3}-\\d{4}$");

    /**
     * Validates email address format
     */
    public static String getValidEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println(" Email cannot be empty!");
                continue;
            }

            if (EMAIL_PATTERN.matcher(email).matches()) {
                return email;
            }
            System.out.println(" Invalid email format! Example: user@example.com");
        }
    }

    /**
     * Validates phone number (10 digits or xxx-xxx-xxxx format)
     */
    public static String getValidPhoneNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = scanner.nextLine().trim();

            if (phone.isEmpty()) {
                System.out.println("Phone number cannot be empty!");
                continue;
            }

            if (PHONE_PATTERN.matcher(phone).matches()) {
                return phone;
            }
            System.out.println("Invalid phone format! Use: 1234567890 or 123-456-7890");
        }
    }

    /**
     * Validates Yes/No input (returns boolean)
     */
    public static boolean getYesOrNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return true;
            }
            if (input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("Please enter 'yes' or 'no'");
        }
    }

    /**
     * Validates password strength
     * Requirements: At least 8 characters, 1 uppercase, 1 lowercase, 1 digit
     */
    public static String getValidPassword(String prompt) {
        while (true) {
            System.out.print(prompt);
            String password = scanner.nextLine();

            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters!");
                continue;
            }

            if (!password.matches(".*[A-Z].*")) {
                System.out.println("Password must contain at least one uppercase letter!");
                continue;
            }

            if (!password.matches(".*[a-z].*")) {
                System.out.println("Password must contain at least one lowercase letter!");
                continue;
            }

            if (!password.matches(".*\\d.*")) {
                System.out.println("Password must contain at least one digit!");
                continue;
            }

            return password;
        }
    }

    /**
     * Validates choice from a list of options
     */
    public static int getChoice(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int choice = Integer.parseInt(scanner.nextLine().trim());

                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Validates date in MM/DD/YYYY format
     */
    public static String getValidDate(String prompt) {
        Pattern datePattern = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$");

        while (true) {
            System.out.print(prompt);
            String date = scanner.nextLine().trim();

            if (date.isEmpty()) {
                System.out.println("Date cannot be empty!");
                continue;
            }

            if (datePattern.matcher(date).matches()) {
                return date;
            }
            System.out.println("Invalid date format! Use MM/DD/YYYY (e.g., 01/15/2024)");
        }
    }

    /**
     * Validates percentage (0-100)
     */
    public static double getValidPercentage(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double percentage = Double.parseDouble(scanner.nextLine().trim());

                if (percentage >= 0 && percentage <= 100) {
                    return percentage;
                }
                System.out.println("Percentage must be between 0 and 100!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Example usage demonstrating all validation methods
     */
    public static void main(String[] args) {
        System.out.println("=== ADVANCED INPUT VALIDATION DEMO ===\n");

        String email = getValidEmail("Enter your email: ");
        String phone = getValidPhoneNumber("Enter your phone number: ");
        boolean subscribe = getYesOrNo("Subscribe to newsletter?");
        String password = getValidPassword("Create a password (min 8 chars, 1 upper, 1 lower, 1 digit): ");
        String dob = getValidDate("Enter your date of birth (MM/DD/YYYY): ");
        double score = getValidPercentage("Enter your test score (0-100): ");

        System.out.println("\n✅ ALL INPUTS VALIDATED SUCCESSFULLY!");
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Newsletter: " + (subscribe ? "Yes" : "No"));
        System.out.println("Password: " + "*".repeat(password.length()) + " (hidden)");
        System.out.println("Date of Birth: " + dob);
        System.out.println("Score: " + score + "%");

        scanner.close();
    }
}

