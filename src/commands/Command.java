package commands;

import services.ITransactionService;

import java.util.Scanner;

public abstract class Command {
    protected String name;
    protected ITransactionService transactionService;
    protected Scanner scanner;

    public Command(String name, ITransactionService transactionService, Scanner scanner) {
        this.name = name;
        this.transactionService = transactionService;
        this.scanner = scanner;
    }

    // Every command will extend from this Command
    public abstract void execute();

    public String getName() {
        return name;
    }
}
