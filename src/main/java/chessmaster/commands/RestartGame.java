package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class RestartGame extends Command {

    public static final String RESTART_COMAMND_STRING = "restart";

    private static final String[] RESTART_STRINGS = {
        "Do you want to restart your game?",
    };


    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(RESTART_STRINGS);
    }

}

