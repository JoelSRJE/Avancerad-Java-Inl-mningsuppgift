package commands;

import models.Transaction;
import services.ITransactionService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DeleteTransactionCommand extends Command {

    public DeleteTransactionCommand(ITransactionService transactionService, Scanner scanner) {
        super("Delete Transaction", transactionService, scanner);
    }

    @Override
    public void execute() {

    }
}
