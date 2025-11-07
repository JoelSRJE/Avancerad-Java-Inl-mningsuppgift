package main.services;

import main.commands.Command;

public interface ITerminalCommandService {
    // Must have functions
    void registerCommand(Command command);
    void executeCommand(String commandInput);
}
