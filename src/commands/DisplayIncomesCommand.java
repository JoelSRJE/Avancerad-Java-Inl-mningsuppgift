package commands;

import models.Transaction;
import services.IAccountService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DisplayIncomesCommand extends Command {

    public DisplayIncomesCommand(IAccountService accountService, Scanner scanner) {
        super("Display Incomes", accountService, scanner);
    }

    @Override
    public void execute() {


        try {
            System.out.println("\n=== Display Incomes (Account: " + accountService.getAccount(accountID).getAccountName() + ") ===");
            System.out.println("----------------------------\n");

            List<Transaction> transactions = accountService.getAllTransactions(accountID);
            List<Transaction> incomes = transactions.stream()
                    .filter(transaction -> transaction.isType())
                    .toList();

            for (Transaction income : incomes) {
                System.out.println(income);
            }

            System.out.println("\nTotal Incomes: " + accountService.getTotalIncomes(accountID) + " (" + incomes.size() + "x)");

            System.out.println("\n----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}