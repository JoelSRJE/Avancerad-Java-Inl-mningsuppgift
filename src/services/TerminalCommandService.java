package services;

import commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalCommandService implements ITerminalCommandService {
    // List for all the commands that exists
    private final List<Command> commands = new ArrayList<>();

    // Should print on start
    public void onStart(Scanner scanner) {

        while (true) {
            System.out.println("\n=== Finance Application ===");
            System.out.println("----------------------------");

            for (int i = 0; i < commands.size(); i++) {
                Command command = commands.get(i);
                System.out.println((i + 1) + ". " + command.getName());
            }

            System.out.println("0. Exit Application");
            System.out.println("----------------------------");
            System.out.print("> ");

            String commandInput = scanner.nextLine();

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
                            " is the registered commands! | (0 - Exit Application)");
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

    @Override
    public void registerCommand(Command command) {
        this.commands.add(command);

        // To verify that the command does get registered properly onStart.
        System.out.println("Registered command: " + command.getName());
    }

    @Override
    public void executeCommand(String commandInput) {
        // Prints the different commands & executes
        for (Command command : commands) {
            if (commandInput.equalsIgnoreCase(command.getName())) {
                command.execute();
                return;
            }

            System.out.println("\nInvalid Command: (" + commandInput + ")");
        }
    }
}
