package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Simple Expense model with title, description and amount.
 */
public class Expense {
    private String title;
    private String description;
    private BigDecimal amount;

    /**
     * Constructs an Expense with the given title, description and amount.
     *
     * @param title       the expense title
     * @param description the expense description
     * @param amount      the expense amount
     */
    public Expense(String title, String description, BigDecimal amount) {
        this.title = Objects.requireNonNull(title, "title cannot be null");
        if (this.title.isEmpty()) throw new IllegalArgumentException("title cannot be empty");

        this.description = Objects.requireNonNull(description, "description cannot be null");
        if (this.description.isEmpty()) throw new IllegalArgumentException("description cannot be empty");

        Objects.requireNonNull(amount, "amount cannot be null");
        BigDecimal scaled = amount.setScale(2, RoundingMode.HALF_EVEN);
        if (scaled.signum() < 0) throw new IllegalArgumentException("amount cannot be negative");
        this.amount = scaled;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expense{title='" + title + "', description='" + description + "', amount=" + amount + '}';
    }
}
