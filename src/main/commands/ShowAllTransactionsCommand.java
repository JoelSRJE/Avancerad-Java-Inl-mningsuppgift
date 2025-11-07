package main.commands;

import main.models.Transaction;
import services.IAccountService;

import java.util.List;
import java.util.Scanner;

public class ShowAllTransactionsCommand extends Command {

    public ShowAllTransactionsCommand(IAccountService accountService, Scanner scanner) {
        super("Show All Transactions", accountService, scanner);
    }

    @Override
    public void execute() {
        try {
            System.out.println("\n=== All Transactions (Account: " + accountService.getAccount(accountID).getAccountName() + ") ===");
            System.out.println("----------------------------\n");

            List<Transaction> transactions = accountService.getAllTransactions(accountID);

            if (transactions.isEmpty()) {
                System.out.println("No transactions found!");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }

            System.out.println("\n----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
