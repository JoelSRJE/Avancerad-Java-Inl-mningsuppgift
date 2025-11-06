package commands;

import models.Transaction;
import services.IAccountService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DeleteTransactionCommand extends Command {

    public DeleteTransactionCommand(IAccountService accountService, Scanner scanner) {
        super("Delete Transaction", accountService, scanner);
    }

    @Override
    public void execute() {
        try {
            List<Transaction> transactions = accountService.getAllTransactions(accountID);

            System.out.println("\n=== All Transactions: (Account: " + accountService.getAccount(accountID).getAccountName() + (") ==="));
            System.out.println("----------------------------");


            if (transactions.isEmpty()) {
                System.out.println("\n=== Delete Transactions (Account: " + accountService.getAccount(accountID).getAccountName() + ") ===");
                System.out.println("-------------------------------\n");
                System.out.println("No transactions to delete!");
                System.out.println("\n-------------------------------\n");
                return;
            }

            System.out.println("\n=== Previous Transactions ===");
            System.out.println("-------------------------------\n");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }

            System.out.println("\n-------------------------------\n");

            System.out.println("=== Delete Transaction ===");
            System.out.println("----------------------------");
            System.out.print("ID: ");
            String id = scanner.nextLine();

            try {
                UUID transactionId = UUID.fromString(id);
                accountService.removeTransaction(accountID, transactionId);

                System.out.println("\n-------------------------------\n");
                System.out.println("Transaction deleted successfully! ");
                System.out.println("\n-------------------------------\n");
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid Transaction ID!");
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}