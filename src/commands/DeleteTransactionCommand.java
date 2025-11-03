package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DeleteTransactionCommand extends Command {

    public DeleteTransactionCommand(ITransactionService transactionService, Scanner scanner) {
        super("Delete Transaction", transactionService, scanner);
    }

    @Override
    public void execute() {
        try {
            List<Transaction> transactions = transactionService.showAllTransactions();

            if (transactions.isEmpty()) {
                System.out.println("\n=== Delete Transactions ===");
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
            System.out.print("ID: ");
            String id = scanner.nextLine();
            UUID realId = UUID.fromString(id);

            Transaction deleted = transactionService.deleteTransaction(realId);

            if (deleted != null) {
                System.out.println("\n-------------------------------\n");
                System.out.println("Deleted: " + deleted);
                System.out.println("\n-------------------------------\n");
            } else {
                System.out.println("\n-------------------------------\n");
                System.out.println("No transaction found with that ID!");
                System.out.println("\n-------------------------------\n");
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}