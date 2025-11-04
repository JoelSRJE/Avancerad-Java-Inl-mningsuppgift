import commands.*;
import services.AccountService;
import services.IAccountService;
import services.ITerminalCommandService;
import services.TerminalCommandService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Passing this scanner down through the commands, don't have to call it in everyone.
        // Less risk of leaks etc.
        Scanner scanner = new Scanner(System.in);

        // Instances.
        IAccountService accountService = new AccountService();
        TerminalCommandService terminalCommandService = new TerminalCommandService();

        UUID accountID = terminalCommandService.loginOrCreateMenu(accountService, scanner);

        // Register the commands.
        terminalCommandService.registerCommand(new AddTransactionCommand(accountService, scanner, accountID));
        terminalCommandService.registerCommand(new DeleteTransactionCommand(accountService, scanner, accountID));
        terminalCommandService.registerCommand(new ShowBalanceCommand(accountService, scanner, accountID));
        terminalCommandService.registerCommand(new DisplayExpensesCommand(accountService, scanner, accountID));
        terminalCommandService.registerCommand(new DisplayIncomesCommand(accountService, scanner,accountID));
        terminalCommandService.registerCommand(new ShowAllTransactionsCommand(accountService, scanner, accountID));

        // Application starts.
        if (terminalCommandService instanceof TerminalCommandService service) {
            service.onStart(scanner, accountService);
        }
    }
}
