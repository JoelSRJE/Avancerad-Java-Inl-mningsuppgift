package main.commands;

import main.models.Transaction;
import main.services.IAccountService;

import java.util.List;
import java.util.Scanner;

public class DisplayExpensesCommand extends Command {

    public DisplayExpensesCommand(IAccountService accountService, Scanner scanner) {
        super("Display Expenses", accountService, scanner);
    }

    @Override
    public void execute() {
        try {
            System.out.println("\n=== Display Expenses (Account: " + accountService.getAccount(accountID).getAccountName() + ") ===");
            System.out.println("----------------------------\n");

            List<Transaction> transactions = accountService.getAllTransactions(accountID);
            List<Transaction> expenses = transactions.stream()
                    .filter(transaction -> !transaction.isType())
                    .toList();

            for (Transaction expense : expenses) {
                System.out.println(expense);
            }

            System.out.println("\nTotal Expenses: " + accountService.getTotalExpenses(accountID) + " (" + expenses.size() + "x)");

            System.out.println("\n----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
