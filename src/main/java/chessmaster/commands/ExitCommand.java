//@@author TongZhengHong
package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class ExitCommand extends Command {

    public static final String EXIT_COMAMND_STRING = "exit";

    private static final String EXIT_MESSAGE = "Exiting program... Thanks for playing!";

    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(EXIT_MESSAGE);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

}
