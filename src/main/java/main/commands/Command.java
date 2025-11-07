package main.commands;

import main.services.IAccountService;

import java.util.Scanner;
import java.util.UUID;

public abstract class Command {
    protected String name;
    protected IAccountService accountService;
    protected Scanner scanner;
    protected UUID accountID;

    public Command(String name, IAccountService accountService, Scanner scanner) {
        this.name = name;
        this.accountService = accountService;
        this.scanner = scanner;
    }

    // Every command will extend from this Command
    public abstract void execute();

    public String getName() {
        return name;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }
}
