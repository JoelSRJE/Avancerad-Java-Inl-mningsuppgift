package commands;

import models.Transaction;
import services.IAccountService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DisplayExpensesCommand extends Command {

    private final UUID accountID;

    public DisplayExpensesCommand(IAccountService accountService, Scanner scanner, UUID accountID) {
        super("Display Expenses", accountService, scanner);
        this.accountID = accountID;
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
