package services;

import models.Transaction;

import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {
    @Override
    public void addTransaction(Transaction transaction) throws Exception {
        System.out.println("Added Transaction");
    }

    @Override
    public Transaction deleteTransaction(UUID transactionId) throws Exception {
        return null;
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
        return List.of();
    }
}
