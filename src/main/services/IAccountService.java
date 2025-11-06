package main.services;

import main.models.Account;
import main.models.Transaction;

import java.util.List;
import java.util.UUID;

public interface IAccountService {

    // Account functions
    Account createAccount(String accountName);
    Account getAccount(UUID accountID) throws Exception;
    List<Account> getAllAccounts();
    void deleteAccount(UUID accountID) throws Exception;

    // Transaction functions
    void addTransaction(UUID accountID, Transaction transaction) throws Exception;
    void removeTransaction(UUID accountID, UUID transactionID) throws Exception;
    double getBalance(UUID accountID) throws Exception;
    double getTotalIncomes(UUID accountID) throws Exception;
    double getTotalExpenses(UUID accountID) throws Exception;
    List<Transaction> getAllTransactions(UUID accountID) throws Exception;
    List<Transaction> getIncomes(UUID accountID) throws Exception;
    List<Transaction> getExpenses(UUID accountID) throws Exception;
}
