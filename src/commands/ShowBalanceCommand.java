package commands;

import services.ITransactionService;

import java.util.Scanner;

public class ShowBalanceCommand extends Command {

    public ShowBalanceCommand(ITransactionService transactionService, Scanner scanner) {
        super("Show Balance", transactionService, scanner);
    }

    @Override
    public void execute() {
        System.out.println("\n=== Show Balance ===");
        System.out.println("----------------------------");

        try {
            System.out.println("Total Balance: " + transactionService.showBalance() + "\n"
                    + "All Incomes: " + transactionService.getAllIncomes() + "\n"
                    + "All Expenses: " + transactionService.getAllExpenses());
            System.out.println("----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }

    }
}
