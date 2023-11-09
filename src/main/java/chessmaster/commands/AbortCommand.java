//@@author TongZhengHong
package chessmaster.commands;

import chessmaster.game.Game;

public class AbortCommand extends Command {

    public static final String ABORT_COMMAND_STRING = "abort";

    private static final String ABORT_MESSAGE = "Exiting program... Thanks for playing!";

    @Override
    public CommandResult execute(Game game) {
        return new CommandResult(ABORT_MESSAGE);
    }

    public static boolean isAbortCommand(Command command) {
        return command instanceof AbortCommand;
    }

}
