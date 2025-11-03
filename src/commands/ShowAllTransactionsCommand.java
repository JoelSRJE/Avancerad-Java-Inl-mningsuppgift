package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.List;
import java.util.Scanner;

public class ShowAllTransactionsCommand extends Command {

    public ShowAllTransactionsCommand(ITransactionService transactionService, Scanner scanner) {
        super("Show All Transactions", transactionService, scanner);
    }

    @Override
    public void execute() {
        System.out.println("\n=== All Transactions ===");
        System.out.println("----------------------------\n");

        try {
            List<Transaction> transactions = transactionService.showAllTransactions();

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }

            System.out.println("\n----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
