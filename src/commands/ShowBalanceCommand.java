package commands;

import services.ITransactionService;

import java.util.Scanner;

public class ShowBalanceCommand extends Command {

    public ShowBalanceCommand(ITransactionService transactionService, Scanner scanner) {
        super("Show Balance", transactionService, scanner);
    }

    @Override
    public void execute() {

    }
}
