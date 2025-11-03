package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private String category;
    private double amount;
    private LocalDate date;
    private boolean type;

    // Constructor to create a new transaction (without id)
    public Transaction(String category, double amount, LocalDate date, boolean type) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Constructor with id for future use.
    public Transaction(UUID id, String category, double amount, LocalDate date, boolean type) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isType() {
        return type;
    }

    // toString for printing etc.
    @Override
    public String toString() {
        return "| Transaction | ID: " + id +
                " | Type: " + (type ? "Income" : "Expense") +
                " | Category: " + category +
                " | Amount: " + amount +
                " | Date: " + date + " |";
    }

    // data to fill out transactions for future use
    public static List<Transaction> populateTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        // By month
        transactions.add(new Transaction("Salary", 25000, LocalDate.of(2025, 1, 25), true));
        transactions.add(new Transaction("Groceries", 1200, LocalDate.of(2025, 1, 27), false));
        transactions.add(new Transaction("Rent", 8000, LocalDate.of(2025, 1, 31), false));

        transactions.add(new Transaction("Freelance", 5000, LocalDate.of(2025, 2, 5), true));
        transactions.add(new Transaction("Electricity", 600, LocalDate.of(2025, 2, 10), false));
        transactions.add(new Transaction("Gift", 1500, LocalDate.of(2025, 2, 14), true));
        transactions.add(new Transaction("Dining Out", 450, LocalDate.of(2025, 2, 20), false));
        transactions.add(new Transaction("Internet", 400, LocalDate.of(2025, 2, 25), false));

        transactions.add(new Transaction("Salary", 25000, LocalDate.of(2025, 3, 25), true));
        transactions.add(new Transaction("Groceries", 1300, LocalDate.of(2025, 3, 28), false));
        transactions.add(new Transaction("Rent", 8000, LocalDate.of(2025, 3, 31), false));
        transactions.add(new Transaction("Transport", 900, LocalDate.of(2025, 3, 15), false));

        transactions.add(new Transaction("Freelance", 4200, LocalDate.of(2025, 4, 10), true));
        transactions.add(new Transaction("Electricity", 620, LocalDate.of(2025, 4, 12), false));
        transactions.add(new Transaction("Streaming Subscription", 129, LocalDate.of(2025, 4, 14), false));
        transactions.add(new Transaction("Groceries", 1100, LocalDate.of(2025, 4, 26), false));
        transactions.add(new Transaction("Gift Received", 800, LocalDate.of(2025, 4, 18), true));

        transactions.add(new Transaction("Salary", 25000, LocalDate.of(2025, 5, 25), true));
        transactions.add(new Transaction("Rent", 8000, LocalDate.of(2025, 5, 31), false));
        transactions.add(new Transaction("Gym Membership", 350, LocalDate.of(2025, 5, 5), false));
        transactions.add(new Transaction("Clothing", 950, LocalDate.of(2025, 5, 11), false));
        transactions.add(new Transaction("Dividend", 2000, LocalDate.of(2025, 5, 20), true));

        transactions.add(new Transaction("Freelance", 4800, LocalDate.of(2025, 6, 7), true));
        transactions.add(new Transaction("Vacation", 5500, LocalDate.of(2025, 6, 18), false));
        transactions.add(new Transaction("Groceries", 1250, LocalDate.of(2025, 6, 24), false));
        transactions.add(new Transaction("Insurance", 1200, LocalDate.of(2025, 6, 28), false));

        transactions.add(new Transaction("Salary", 25000, LocalDate.of(2025, 7, 25), true));
        transactions.add(new Transaction("Car Maintenance", 3000, LocalDate.of(2025, 7, 10), false));
        transactions.add(new Transaction("Electricity", 580, LocalDate.of(2025, 7, 14), false));
        transactions.add(new Transaction("Restaurant", 650, LocalDate.of(2025, 7, 22), false));

        transactions.add(new Transaction("Freelance", 6000, LocalDate.of(2025, 8, 8), true));
        transactions.add(new Transaction("Concert Tickets", 1200, LocalDate.of(2025, 8, 12), false));
        transactions.add(new Transaction("Groceries", 1350, LocalDate.of(2025, 8, 27), false));
        transactions.add(new Transaction("Rent", 8200, LocalDate.of(2025, 8, 31), false));

        return transactions;
    }
}
