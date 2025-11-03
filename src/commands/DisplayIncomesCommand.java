package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.List;
import java.util.Scanner;

public class DisplayIncomesCommand extends Command {

    public DisplayIncomesCommand(ITransactionService transactionService, Scanner scanner) {
        super("Display Incomes", transactionService, scanner);
    }

    @Override
    public void execute() {
        System.out.println("\n=== Display Incomes ===");
        System.out.println("----------------------------\n");

        try {
            List<Transaction> transactions = transactionService.showAllTransactions();
            List<Transaction> incomes = transactions.stream()
                    .filter(transaction -> transaction.isType())
                    .toList();

            for (Transaction income : incomes) {
                System.out.println(income);
            }

            System.out.println("\nTotal Expenses: " + transactionService.getAllIncomes() + " (" + incomes.size() + "x)");

            System.out.println("\n----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}