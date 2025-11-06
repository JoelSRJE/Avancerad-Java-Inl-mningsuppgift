import commands.*;
import repositories.ApplicationRepository;
import repositories.IApplicationRepository;
import services.AccountService;
import services.IAccountService;
import services.TerminalCommandService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Passing this scanner down through the commands, don't have to call it in everyone.
        // Less risk of leaks etc.
        Scanner scanner = new Scanner(System.in);

        // Instances.
        IApplicationRepository applicationRepository = new ApplicationRepository();
        IAccountService accountService = new AccountService(applicationRepository);
        TerminalCommandService terminalCommandService = new TerminalCommandService();

        // Register the commands.
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
