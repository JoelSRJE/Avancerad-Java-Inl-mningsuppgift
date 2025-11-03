package commands;

import services.ITransactionService;

import java.util.Scanner;

public class DisplayIncomesCommand extends Command {

    public DisplayIncomesCommand(ITransactionService transactionService, Scanner scanner) {
        super("Display Incomes", transactionService, scanner);
    }

    @Override
    public void execute() {

    }
}
