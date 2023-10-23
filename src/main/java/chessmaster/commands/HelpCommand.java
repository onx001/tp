package chessmaster.commands;

import chessmaster.game.ChessBoard;

public class HelpCommand extends Command {

    public static final String HELP_COMAMND_STRING = "help";

    private static final String[] HELP_STRINGS = {
            "Seems like you need some help, here are the following commands to play: "
    };

    @Override
    public CommandResult execute(ChessBoard board) {
        return new CommandResult(HELP_STRINGS);
    }

}
