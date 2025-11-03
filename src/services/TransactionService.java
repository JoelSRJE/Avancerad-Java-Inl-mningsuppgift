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
        return 0;
    }

    @Override
    public double getAllIncomes() throws Exception {
        return 0;
    }

    @Override
    public double getAllExpenses() throws Exception {
        return 0;
    }

    @Override
    public List<Transaction> displayExpenses() throws Exception {
        return List.of();
    }

    @Override
    public List<Transaction> displayIncomes() throws Exception {
        return List.of();
    }

    @Override
    public List<Transaction> showAllTransactions() throws Exception {
        // Returns the ArrayList of transactions.
        return transactions;
    }
}
