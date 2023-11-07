package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Game;

public class StepbackCommand extends Command {

    public static final String STEPBACK_COMMAND_STRING = "stepback";

    @Override
    public CommandResult execute(Game game) throws ChessMasterException {
        ChessBoard currentBoard = game.getBoard();
        ChessBoard historyBoard = currentBoard.clone();

        return new CommandResult("hello!");

    }

}
