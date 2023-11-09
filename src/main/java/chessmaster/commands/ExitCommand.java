//@@author TongZhengHong
package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.Game;

public class ExitCommand extends Command {

    public static final String EXIT_COMMAND_STRING = "exit";

    private static final String EXIT_MESSAGE = "Exiting program... Thanks for playing!";

    @Override
    public CommandResult execute(Game game) throws ChessMasterException {
        return new CommandResult(EXIT_MESSAGE);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

}
