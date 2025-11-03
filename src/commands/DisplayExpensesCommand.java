package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.List;
import java.util.Scanner;

public class DisplayExpensesCommand extends Command {

    public DisplayExpensesCommand(ITransactionService transactionService, Scanner scanner) {
        super("Display Expenses", transactionService, scanner);
    }

    @Override
    public void execute() {
        System.out.println("\n=== Display Expenses ===");
        System.out.println("----------------------------\n");

        try {
            List<Transaction> transactions = transactionService.showAllTransactions();
            List<Transaction> expenses = transactions.stream()
                    .filter(transaction -> !transaction.isType())
                    .toList();

            for (Transaction expense : expenses) {
                System.out.println(expense);
            }

            System.out.println("\nTotal Expenses: " + transactionService.getAllExpenses() + " (" + expenses.size() + "x)");

            System.out.println("\n----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
