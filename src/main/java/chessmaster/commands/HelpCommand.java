package chessmaster.commands;

import chessmaster.game.ChessBoard;

public class HelpCommand extends Command {

    public static final String HELP_COMAMND_STRING = "help";

    private static final String[] HELP_STRINGS = { 
        "Seems like you need some help, here are the following commands to play:",
        "Move piece - Input coordinate of piece, followed by coordinate to move to",
        "   Format: [column][row] [column][row]",
        "   E.g. a2 a3",
        "Show board - Shows the current state of the chess board",
        "   Format: show",
        "Show available moves - Lists all the available moves for a piece at a coordinate",
        "   Format: moves [column][row]",
        "   E.g. moves a2",
        "Abort game - Exit programme",
        "   Format: abort",
        "Obtain rules - Obtain a quick refresher on the rules of chess",
        "   Format: rules"
    };

    @Override
    public CommandResult execute(ChessBoard board) {
        return new CommandResult(HELP_STRINGS);
    }

}
