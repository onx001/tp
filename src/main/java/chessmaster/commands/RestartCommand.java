package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.Game;

public class RestartCommand extends Command {

    public static final String RESTART_COMMAND_STRING = "restart";


    @Override
    public CommandResult execute(Game game) throws ChessMasterException {
        return new CommandResult();
    }

    public static boolean isRestart(Command command) {
        return command instanceof RestartCommand;
    }
}
