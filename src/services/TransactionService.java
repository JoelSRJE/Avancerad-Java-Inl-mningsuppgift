package services;

import models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {

    // Building the functionality with a list for starters, before transactions.txt
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) throws Exception {
        // Adds the transaction in the arraylist.
        if (transaction == null) {
            throw new Exception("Transaction cannot be null/empty!");
        }

        transactions.add(transaction);
    }

    @Override
    public Transaction deleteTransaction(UUID transactionId) throws Exception {
        List<Transaction> transactions = showAllTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                transactions.remove(transaction);
                return transaction;
            }
        }

        throw new Exception("Transaction to be deleted not found!");
    }

    @Override
    public double showBalance() throws Exception {
        double balance = 0.0;

        for (Transaction transaction : transactions) {
            if (transaction.isType()) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }

        return (balance);
    }

    @Override
    public double getAllIncomes() throws Exception {
        double incomes = 0.0;
        incomes = transactions.stream()
                .filter(Transaction::isType)
                .mapToDouble(Transaction::getAmount)
                .sum();

        return incomes;
    }

    @Override
    public double getAllExpenses() throws Exception {
        double expenses = 0.0;
        expenses = transactions.stream()
                .filter(transaction -> !transaction.isType())
                .mapToDouble(Transaction::getAmount)
                .sum();

        return expenses;
    }

    @Override
    public List<Transaction> displayExpenses() throws Exception {
        List<Transaction> expenses = new ArrayList<>();

        expenses = transactions.stream()
                .filter(transaction -> !transaction.isType())
                .toList();

        return expenses;
    }

    @Override
    public List<Transaction> displayIncomes() throws Exception {
        List<Transaction> incomes = new ArrayList<>();

        incomes = transactions.stream()
                .filter(transaction -> !transaction.isType())
                .toList();

        return incomes;
    }

    @Override
    public List<Transaction> showAllTransactions() throws Exception {
        // Returns the ArrayList of transactions.
        return transactions;
    }
}
