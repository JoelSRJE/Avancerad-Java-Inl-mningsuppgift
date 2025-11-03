import commands.*;
import services.ITerminalCommandService;
import services.ITransactionService;
import services.TerminalCommandService;
import services.TransactionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Passing this scanner down through the commands, don't have to call it in everyone.
        // Less risk of leaks etc.
        Scanner scanner = new Scanner(System.in);

        // Instances.
        ITransactionService transactionService = new TransactionService();
        ITerminalCommandService terminalCommandService = new TerminalCommandService();

        // Register the commands.
        terminalCommandService.registerCommand(new AddTransactionCommand(transactionService, scanner));
        terminalCommandService.registerCommand(new DeleteTransactionCommand(transactionService, scanner));
        terminalCommandService.registerCommand(new ShowBalanceCommand(transactionService, scanner));
        terminalCommandService.registerCommand(new DisplayExpensesCommand(transactionService, scanner));
        terminalCommandService.registerCommand(new DisplayIncomesCommand(transactionService, scanner));
        terminalCommandService.registerCommand(new ShowAllTransactionsCommand(transactionService, scanner));

        // Application starts.
        if (terminalCommandService instanceof TerminalCommandService service) {
            service.onStart(scanner);
        }
    }
}
