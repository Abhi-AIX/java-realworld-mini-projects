import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== DEMONSTRATION OF INPUT HANDLING METHODS ===\n");

        // ============================================================================
        // METHOD 1: Basic approach using nextInt() - PROBLEM: Leaves newline in buffer
        // ============================================================================
        System.out.println("--- Method 1: Basic Scanner (with newline issue fix) ---");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        //why name skipped?
        //fix by adding an extra scanner.nextLine() here
        scanner.nextLine(); // Consume the leftover newline character
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        UserInput user = new UserInput(name, age, salary);
        System.out.println("✅ User Details: " + user);
        System.out.println();

        /*
        A frequent issue occurs when using numeric methods like nextInt()
        followed by nextLine(). Numeric methods do not consume the "new line" character (\n)
        generated when the user presses Enter. This leftover character is then immediately consumed
        by the subsequent nextLine() call, causing it to skip user input. To avoid this, it is recommended
        to use nextLine() for all inputs and then parse the strings into the required numeric types
        (e.g., using Integer.parseInt()).
         */

        //what is consuming the newline character? explain it?
        //When the user inputs a number using methods like nextInt(), nextDouble(), etc.,these methods read only the numeric part of the input and leave the newline character (the "Enter" key press) in the input buffer.
        //When the nextLine() method is called after a numeric input method, it reads this leftover newline character as an empty line, causing it to skip the actual user input.
        //To prevent this, we can add an extra nextLine() call after the numeric input to consume the leftover newline character before calling nextLine() for string input.
        //Alternatively, we can use nextLine() for all inputs and then parse the numeric values from the strings.
        //best practice is to use nextLine() for all inputs and then parse the numeric values from the strings.

        // ============================================================================
        // METHOD 2: Better approach - Use nextLine() + parseInt() - Still no validation
        // ============================================================================
        System.out.println("--- Method 2: nextLine() + parseInt() (Better but no validation) ---");
        System.out.print("Enter your age: ");
        int userAge = Integer.parseInt(scanner.nextLine());
        //what is parseInt?
        //parseInt is a method in the Integer class that converts a string representation of a number into an integer.
        //why sc.nextLine() inside parseInt?
        //sc.nextLine() reads the entire line of input as a string, which is then passed to Integer.parseInt() to convert it into an integer.
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter your salary: ");
        int userSalary = Integer.parseInt(scanner.nextLine());

        UserInput user2 = new UserInput(userName, userAge, userSalary);
        System.out.println("User Details: " + user2);
        System.out.println();

        // ============================================================================
        // METHOD 3: PROFESSIONAL APPROACH - With Input Validation (Production-Ready)
        // ============================================================================
        System.out.println("--- Method 3: PROFESSIONAL with Validation (Production-Ready) ---");
        System.out.println("This method prevents crashes from invalid input!\n");

        // Using InputValidator utility class for safe, validated input
        int validatedAge = InputValidator.getValidInteger(
            "Enter your age (0-150): ",
            0,
            150
        );

        String validatedName = InputValidator.getValidString("Enter your name: ");

        int validatedSalary = InputValidator.getValidInteger(
            "Enter your salary (1000-1000000): ",
            1000,
            1000000
        );

        UserInput user3 = new UserInput(validatedName, validatedAge, validatedSalary);
        System.out.println("\nVALIDATED User Details: " + user3);

        // Clean up resources
        scanner.close();
        InputValidator.closeScanner();

        System.out.println("\n=== PROGRAM COMPLETED SUCCESSFULLY ===");
    }
}