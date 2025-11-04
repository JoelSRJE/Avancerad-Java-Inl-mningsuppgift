package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private final UUID accountID;
    private String accountName;
    List<Transaction> transactions;
    private double balance;
    private double totalIncomes;
    private double totalExpenses;

    // Constructor to create a new account
    public Account(String accountName) {
        this.accountID = UUID.randomUUID();
        this.accountName = accountName;
        this.transactions = new ArrayList<>();
    }

    // Constructor for loading in an existing account
    public Account(UUID accountID, String accountName, List<Transaction> transactions) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.transactions = transactions != null ? transactions : new ArrayList<>();
    }

    // Getters & Setters
    public UUID getAccountID() {
        return accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    // Functions
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public boolean removeTransaction(UUID transactionID) {
        return transactions.removeIf(transaction -> transaction.getId().equals(transactionID));
    }

    public double calculateBalance() {
        return transactions.stream()
                .mapToDouble(transaction -> transaction.isType() ? transaction.getAmount() : -transaction.getAmount())
                .sum();
    }

    public double calculateTotalIncomes() {
        return transactions.stream()
                .filter(transaction -> transaction.isType())
                .mapToDouble(transaction -> transaction.getAmount())
                .sum();
    }

    public double calculateTotalExpenses() {
        return transactions.stream()
                .filter(transaction -> !transaction.isType())
                .mapToDouble(transaction -> transaction.getAmount())
                .sum();
    }

    @Override
    public String toString() {
        return "| Account | ID: " + accountID +
                " | Name: " + accountName +
                " | Transactions: " + transactions.size() +
                " | Balance: " + calculateBalance() + " |";
    }
}
