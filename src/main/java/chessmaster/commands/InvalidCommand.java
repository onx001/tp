package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_STRING = 
        "Oops! It appears that the command you entered is not recognized. " + System.lineSeparator() + 
        "Please use 'help' to view a list of available commands.";

    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(INVALID_COMMAND_STRING);
    }
    
}
