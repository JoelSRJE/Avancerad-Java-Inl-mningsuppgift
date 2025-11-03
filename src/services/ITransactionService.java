package services;

import models.Transaction;

import java.util.List;
import java.util.UUID;

public interface ITransactionService {
    // Functions for the different commands (from my previous project)

    // 1 - Add Transaction
    void addTransaction(Transaction transaction) throws Exception;

    // 2 - Delete Transaction
    Transaction deleteTransaction(UUID transactionId) throws Exception;

    // 3 - Show Balance
    double showBalance() throws Exception;

    // 3.1 - All Incomes
    double getAllIncomes() throws Exception;

    // 3.2 - All Expenses
    double getAllExpenses() throws Exception;

    // 4 - Display Expenses
    List<Transaction> displayExpenses() throws Exception;

    // 5 - Display Incomes
    List<Transaction> displayIncomes() throws Exception;

    // 6 - Show All Transactions
    List<Transaction> showAllTransactions() throws Exception;
}
