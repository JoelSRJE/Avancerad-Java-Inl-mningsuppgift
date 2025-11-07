package main.repositories;

import main.models.Account;
import main.models.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplicationRepository implements IApplicationRepository {
    private static final String ACCOUNTS_FOLDER = "accounts";
    private static final String TRANSACTIONS_FOLDER = "transactions";

    public ApplicationRepository() {
        new File(ACCOUNTS_FOLDER).mkdirs();
        new File(TRANSACTIONS_FOLDER).mkdirs();
    }

    private String allowedLettersInName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    @Override
    public void saveAccounts(Account account) {
        String safeName = allowedLettersInName(account.getAccountName());
        File file = new File(ACCOUNTS_FOLDER, "account_" + safeName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(account.getAccountName());
            writer.newLine();
            writer.write(account.getAccountID().toString());
            writer.newLine();
        } catch (IOException exception) {
            System.out.println("Error saving account " + exception.getMessage());
        }
    }

    @Override
    public List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        File folder = new File(ACCOUNTS_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.startsWith("account_") && name.endsWith(".txt"));

        if (files == null) {
            return accounts;
        }

        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String accountName = reader.readLine();
                String accountID = reader.readLine();

                if (accountName != null && accountID != null) {
                    Account account = new Account(UUID.fromString(accountID), accountName, null);
                    accounts.add(account);
                }
            } catch (IOException exception) {
                System.out.println("Error reading account file " + exception.getMessage());
            }
        }

        return accounts;
    }

    @Override
    public void saveTransactions(String accountName, List<Transaction> transactions) {
        String safeName = allowedLettersInName(accountName);
        File file = new File(TRANSACTIONS_FOLDER, "transactions_" + safeName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Transaction transaction : transactions) {
                String line = String.format(
                        "%s,%s,%s,%s,%s",
                        transaction.getId(),
                        transaction.getCategory(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.isType()
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Error saving transactions " + exception.getMessage());
        }
    }

    @Override
    public List<Transaction> loadTransactions(String accountName) {
        List<Transaction> transactions = new ArrayList<>();
        String safeName = allowedLettersInName(accountName);
        File file = new File(TRANSACTIONS_FOLDER, "transactions_" + safeName + ".txt");

        if (!file.exists()) {
            return transactions;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    UUID id = UUID.fromString(parts[0]);
                    String category = parts[1];
                    Double amount = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);
                    Boolean isType = Boolean.parseBoolean(parts[4]);

                    transactions.add(new Transaction(id, category, amount, date, isType));
                }
            }
        } catch (IOException exception) {
            System.out.println("Error reading transactions file " + exception.getMessage());
        }

        return transactions;
    }

    @Override
    public void deleteAccount(String accountName) {
        String safeName = allowedLettersInName(accountName);
        File accountFile = new File(ACCOUNTS_FOLDER, "account_" + safeName + ".txt");
        File transactionFile = new File(TRANSACTIONS_FOLDER, "transactions_" + safeName + ".txt");

        if (accountFile.exists()) {
            accountFile.delete();
        }

        if (transactionFile.exists()) {
            transactionFile.delete();
        }
    }
}

