package main.services;

import main.commands.Command;
import main.models.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TerminalCommandService implements ITerminalCommandService {
    // List for all the main.commands that exists
    private final List<Command> commands = new ArrayList<>();

    private UUID currentAccountID;

    // Should print on start/login
    public void onStart(Scanner scanner, IAccountService accountService) {

        while (true) {
            loginOrCreateMenu(accountService, scanner);

            while (true) {
                System.out.println("\n=== Finance Application ===");
                System.out.println("----------------------------");

                for (int i = 0; i < commands.size(); i++) {
                    Command command = commands.get(i);
                    System.out.println((i + 1) + ". " + command.getName());
                }

                System.out.println("L. - Log out / Switch Account");
                System.out.println("0. Exit Application");
                System.out.println("----------------------------");
                System.out.print("> ");

                String commandInput = scanner.nextLine();

                try {
                    if (commandInput.equalsIgnoreCase("L") || commandInput.equalsIgnoreCase("logout") || commandInput.equalsIgnoreCase("Log out")) {
                        System.out.println("Account: " + accountService.getAccount(currentAccountID).getAccountName() + " | Logging out...");
                        loginOrCreateMenu(accountService, scanner);
                        continue;
                    }
                } catch (Exception exception) {
                    System.out.println("Error: " + exception.getMessage());
                }


                // if the users wants "shortcuts" with number option
                try {
                    int numShortcut = Integer.parseInt(commandInput);

                    if (numShortcut == 0) {
                        System.out.println("Exiting application...");
                        return;
                    }

                    if (numShortcut >= 1 && numShortcut <= commands.size()) {
                        commands.get(numShortcut - 1).execute();
                    } else {
                        System.out.println("Invalid choice! 1 - " + commands.size() +
                                " is the registered main.commands! | (0 - Exit Application)");
                    }
                    continue;
                }
                catch (NumberFormatException exception) {

                }

                if (commandInput.equalsIgnoreCase("exit") || commandInput.equalsIgnoreCase("exit application")) {
                    System.out.println("Exiting application...");
                    return;
                }

                try {
                    executeCommand(commandInput);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public void registerCommand(Command command) {
        this.commands.add(command);

        // To verify that the command does get registered properly onStart.
        System.out.println("Registered command: " + command.getName());
    }

    @Override
    public void executeCommand(String commandInput) {
        // Prints the different main.commands & executes
        for (Command command : commands) {
            if (commandInput.equalsIgnoreCase(command.getName())) {
                command.execute();
                return;
            }

            System.out.println("\nInvalid Command: (" + commandInput + ")");
        }
    }

    public void loginOrCreateMenu(IAccountService accountService, Scanner scanner) {

        while (true) {
            System.out.println("\n=== Finance Application ===");
            System.out.println("----------------------------");
            System.out.println("1. Create new account");
            System.out.println("2. Select existing account");
            System.out.println("0. Exit");
            System.out.println("----------------------------");
            System.out.print("> ");

            String commandInput = scanner.nextLine();

            switch (commandInput) {
                case "0" -> {
                    System.out.println("Exiting application...");
                    System.exit(0);
                }

                case "1" -> {
                    System.out.println("\n  === Create Account ===");
                    System.out.println("----------------------------");
                    System.out.println("Account name:");
                    System.out.print("> ");
                    String accountName = scanner.nextLine();
                    UUID newAccountID = accountService.createAccount(accountName).getAccountID();
                    setCurrentAccount(newAccountID);
                    return;
                }

                case "2" -> {
                    List<Account> accounts = accountService.getAllAccounts();

                    if (accounts.isEmpty()) {
                        System.out.println("No accounts available. Please create a new account!");
                        break;
                    }

                    System.out.println("\n=== Financial Accounts ===");
                    System.out.println("----------------------------");

                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.println((i + 1) + ". " + accounts.get(i).getAccountName());
                    }

                    System.out.println("Select Account By ID: ");
                    System.out.print("> ");

                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        if (choice >= 1 && choice <= accounts.size()) {
                            UUID selectedID = accounts.get(choice - 1).getAccountID();
                            setCurrentAccount(selectedID);

                            System.out.println("Logging In | Account: " + accounts.get(choice - 1).getAccountName());
                            return;
                        } else {
                            System.out.println("Invalid choice. Try again!");
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Invalid input!");
                    }
                }

                default -> System.out.println("Invalid option, try again!");
            }
        }
    }

    public void  setCurrentAccount(UUID accountID) {
        this.currentAccountID = accountID;

        for (Command command : commands) {
            command.setAccountID(currentAccountID);
        }
    }
}
