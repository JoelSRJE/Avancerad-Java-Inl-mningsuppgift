package commands;

import services.IAccountService;

import java.util.Scanner;
import java.util.UUID;

public class ShowBalanceCommand extends Command {

    private final UUID accountID;

    public ShowBalanceCommand(IAccountService accountService, Scanner scanner, UUID accountID) {
        super("Show Balance", accountService, scanner);
        this.accountID = accountID;
    }

    @Override
    public void execute() {
        try {
            System.out.println("\n=== Show Balance (Account: " + accountService.getAccount(accountID).getAccountName() + ") ===");
            System.out.println("----------------------------");


            System.out.println("Total Balance: " + accountService.getBalance(accountID) + "\n"
                    + "All Incomes: " + accountService.getTotalIncomes(accountID) + "\n"
                    + "All Expenses: " + accountService.getTotalExpenses(accountID));
            System.out.println("----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
