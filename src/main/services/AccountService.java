package main.services;

import main.models.Account;
import main.models.Transaction;
import main.repositories.IApplicationRepository;

import java.util.*;

public class AccountService implements IAccountService {

    private final IApplicationRepository applicationRepository;

    private final Map<UUID, Account> accounts = new HashMap<>();

    public AccountService(IApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;

    }

    @Override
    public Account createAccount(String accountName) {
        Account account = new Account(accountName);
        accounts.put(account.getAccountID(), account);

        try {
            if (accounts.size() == 1) {
                for (Transaction transaction : Transaction.populateTransactionsFirstAccount()) {
                    account.addTransaction(transaction);
                }
            } else if (accounts.size() == 2) {
                for (Transaction transaction : Transaction.populateTransactionsSecondAccount()) {
                    account.addTransaction(transaction);
                }
            } else {
                return account;
            }
        } catch (Exception exception) {
            System.out.println("Error populating demo transactions for the first 2 accounts: " + exception.getMessage());
        }

        applicationRepository.saveAccounts(account);
        applicationRepository.saveTransactions(account.getAccountName(), account.getTransactions());

        return account;
    }

    @Override
    public Account getAccount(UUID accountID) throws Exception {
        Account account = accounts.get(accountID);
        if (account == null) {
            throw new Exception("Account not found");
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void deleteAccount(UUID accountID) throws Exception {
        if (accounts.remove(accountID) == null) {
            throw new Exception("Account not found");
        }
    }

    // Transactions function
    @Override
    public void addTransaction(UUID accountID, Transaction transaction) throws Exception {
        Account account = getAccount(accountID);
        account.addTransaction(transaction);

        saveAccountAndTransactions(account);
    }

    @Override
    public void removeTransaction(UUID accountID, UUID transactionID) throws Exception {
        Account account = getAccount(accountID);
        boolean removed = account.removeTransaction(transactionID);

        if (!removed) {
            throw new Exception("Transaction not found in this account!");
        }

        saveAccountAndTransactions(account);
    }

    @Override
    public double getBalance(UUID accountID) throws Exception {
        return getAccount(accountID).calculateBalance();
    }

    @Override
    public double getTotalIncomes(UUID accountID) throws Exception {
        return getAccount(accountID).calculateTotalIncomes();
    }

    @Override
    public double getTotalExpenses(UUID accountID) throws Exception {
        return getAccount(accountID).calculateTotalExpenses();
    }

    @Override
    public List<Transaction> getAllTransactions(UUID accountID) throws Exception {
        return getAccount(accountID).getTransactions();
    }

    @Override
    public List<Transaction> getIncomes(UUID accountID) throws Exception {
        List<Transaction> incomes = new ArrayList<>();

        for (Transaction transaction : getAccount(accountID).getTransactions()) {
            if (transaction.isType()) {
                incomes.add(transaction);
            }
        }

        return incomes;
    }

    @Override
    public List<Transaction> getExpenses(UUID accountID) throws Exception {
        List<Transaction> expenses = new ArrayList<>();

        for (Transaction transaction : getAccount(accountID).getTransactions()) {
            if (!transaction.isType()) {
                expenses.add(transaction);
            }
        }

        return expenses;
    }

    private void saveAccountAndTransactions(Account account) {
        applicationRepository.saveAccounts(account);
        applicationRepository.saveTransactions(account.getAccountName(), account.getTransactions());
    }

    public void loadAccountsFromRepository() {
        List<Account> loadedAccounts = applicationRepository.loadAccounts();
        for (Account account : loadedAccounts) {
            List<Transaction> transactions = applicationRepository.loadTransactions(account.getAccountName());
            for (Transaction transaction : transactions) {
                account.addTransaction(transaction);
            }
            accounts.put(account.getAccountID(), account);
        }
    }


}
