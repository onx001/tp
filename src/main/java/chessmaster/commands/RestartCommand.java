package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class RestartCommand extends Command {

    public static final String RESTART_COMAMND_STRING = "restart";


    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult();
    }

    public static boolean isRestartCommand(Command command) {
        return command instanceof RestartCommand;
    }
}
