package commands;

import services.ITransactionService;

import java.util.Scanner;

public class ShowAllTransactionsCommand extends Command {

    public ShowAllTransactionsCommand(ITransactionService transactionService, Scanner scanner) {
        super("Show All Transactions", transactionService, scanner);
    }

    @Override
    public void execute() {

    }
}
