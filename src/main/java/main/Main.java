package main;

import main.commands.*;
import main.repositories.ApplicationRepository;
import main.repositories.IApplicationRepository;
import main.services.AccountService;
import main.services.IAccountService;
import main.services.TerminalCommandService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Passing this scanner down through the main.commands, don't have to call it in everyone.
        // Less risk of leaks etc.
        Scanner scanner = new Scanner(System.in);

        // Instances.
        IApplicationRepository applicationRepository = new ApplicationRepository();
        IAccountService accountService = new AccountService(applicationRepository);
        TerminalCommandService terminalCommandService = new TerminalCommandService();
        accountService.loadAccountsFromRepository();

        // Register the main.commands.
        terminalCommandService.registerCommand(new AddTransactionCommand(accountService, scanner));
        terminalCommandService.registerCommand(new DeleteTransactionCommand(accountService, scanner));
        terminalCommandService.registerCommand(new ShowBalanceCommand(accountService, scanner));
        terminalCommandService.registerCommand(new DisplayExpensesCommand(accountService, scanner));
        terminalCommandService.registerCommand(new DisplayIncomesCommand(accountService, scanner));
        terminalCommandService.registerCommand(new ShowAllTransactionsCommand(accountService, scanner));

        // Application starts.
        if (terminalCommandService instanceof TerminalCommandService service) {
            service.onStart(scanner, accountService);
        }
    }
}
