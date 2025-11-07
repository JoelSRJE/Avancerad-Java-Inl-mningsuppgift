package main.repositories;

import main.models.Account;
import main.models.Transaction;

import java.util.List;

public interface IApplicationRepository {
    void saveAccounts(Account account);
    List<Account> loadAccounts();

    void saveTransactions(String accountName, List<Transaction> transactions);
    List<Transaction> loadTransactions(String accountName);

    void deleteAccount(String accountName);
}
