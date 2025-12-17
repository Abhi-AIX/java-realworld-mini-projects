// Mini Bank Ledger
// Goal: demonstrate correct usage of Java data types in a banking-like domain
//
// Rules:
// - Use BigDecimal for money (2 decimal places)
// - Use long for accountId and timestamps
// - Use char for transaction type: 'D' or 'W'
// - Reject invalid transactions

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Calculate and display balances
        Map<Long, BigDecimal> balances = calculateBalances(transactions);
        displayBalances(balances);
    }

    static boolean isValidTransaction(Transaction tx) {
        if (tx.type() != 'D' && tx.type() != 'W') {
            return false;
        }
        if (tx.amount().scale() > 2 || tx.amount().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        return true;
    }

    static Map<Long, BigDecimal> calculateBalances(List<Transaction> transactions) {
        Map<Long, BigDecimal> balances = new HashMap<>();

        for (Transaction tx : transactions) {
            if (!isValidTransaction(tx)) {
                System.out.println("Invalid transaction skipped: " + tx);
                continue;
            }

            long accountId = tx.accountId();
            BigDecimal currentBalance = balances.getOrDefault(accountId, BigDecimal.ZERO);

            if (tx.type() == 'D') {
                balances.put(accountId, currentBalance.add(tx.amount()));
            } else if (tx.type() == 'W') {
                balances.put(accountId, currentBalance.subtract(tx.amount()));
            }
        }

        return balances;
    }

    static void displayBalances(Map<Long, BigDecimal> balances) {
        System.out.println("\n========== Account Balances ==========");
        balances.forEach((accountId, balance) -> {
            System.out.printf("Account ID: %d | Balance: $%.2f%n", accountId, balance);
        });
        System.out.println("=====================================\n");
    }

}