package chessmaster.commands;

import chessmaster.game.Game;

public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_STRING = 
        "Oops! It appears that the command you entered is not recognized. " + System.lineSeparator() + 
        "Please use 'help' to view a list of available commands.";

    @Override
    public CommandResult execute(Game game) {
        return new CommandResult(INVALID_COMMAND_STRING);
    }
}
