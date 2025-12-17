// Mini Bank Ledger
// Goal: demonstrate correct usage of Java data types in a banking-like domain
//
// Rules:
// - Use BigDecimal for money (2 decimal places)
// - Use long for accountId and timestamps
// - Use char for transaction type: 'D' or 'W'
// - Reject invalid transactions

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Transaction> transactions = List.of(
                new Transaction('D', 100000000001L,
                        new BigDecimal("250.50"),
                        System.currentTimeMillis(),
                        "Salary"),

                new Transaction('W', 100000000001L,
                        new BigDecimal("50.75"),
                        System.currentTimeMillis(),
                        "Groceries"),

                new Transaction('D', 100000000002L,
                        new BigDecimal("500.00"),
                        System.currentTimeMillis(),
                        "Freelance Payment"),

                new Transaction('W', 100000000002L,
                        new BigDecimal("100.00"),
                        System.currentTimeMillis(),
                        "Utilities")
        );

        System.out.println("Transactions loaded: " + transactions.size());
    }
}