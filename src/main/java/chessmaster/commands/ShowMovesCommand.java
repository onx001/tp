package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;

public class ShowMovesCommand extends Command{
    public static final String SHOW_MOVE_COMMAND_STRING = "moves";
    private static final String SHOW_MOVE_MESSAGE = "These are the moves available for the piece at %s.";
    private String userInput;
    private ChessPiece piece;

    public ShowMovesCommand(String userInput){
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ChessBoard board) throws ChessMasterException {
        Coordinate coord = Coordinate.parseAlgebraicCoor(userInput);
        piece = board.getPieceAtCoor(coord);
        String displayString = String.format(SHOW_MOVE_MESSAGE, piece.getPosition().toString());
        return new CommandResult(displayString);
    }

    public ChessPiece getPiece() {
        return this.piece;
    }
}
