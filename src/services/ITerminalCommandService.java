package services;

import commands.Command;

public interface ITerminalCommandService {
    // Must have functions
    void registerCommand(Command command);
    void executeCommand(String commandInput);
}
