//@@author TongZhengHong
package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.exceptions.InvalidMoveException;
import chessmaster.exceptions.ParseCoordinateException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.ui.TextUI;

public class MoveCommand extends Command {

    public static final String MOVE_COMAMND_STRING = "move";
    private static final String MOVE_PIECE_MESSAGE = "You moved %s from %s to %s";

    private String userInput;
    private Move move;

    public MoveCommand(String inputString) {
        this.userInput = inputString;
    }

    /**
     * Executes the command based on user input, which is expected to consist of two
     * algebraic
     * coordinate strings separated by whitespace.
     *
     * @return A CommandResult object containing the result of the command.
     * @throws ParseCoordinateException If the user input cannot be parsed into two
     *                                  coordinate objects.
     */
    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) throws ChessMasterException {
        move = Parser.parseMove(userInput, board);
        String pieceString = move.getPiece().getClass().getSimpleName();
        String displayString = String.format(MOVE_PIECE_MESSAGE, pieceString, move.getFrom(), move.getTo());
        return new CommandResult(displayString);
    }

    public Move getMove() throws InvalidMoveException {
        if (move == null) {
            throw new InvalidMoveException();
        }
        return move;
    }

}
