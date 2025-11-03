package commands;

import services.ITransactionService;

import java.util.Scanner;

public class DisplayExpensesCommand extends Command {

    public DisplayExpensesCommand(ITransactionService transactionService, Scanner scanner) {
        super("Display Expenses", transactionService, scanner);
    }

    @Override
    public void execute() {

    }
}
