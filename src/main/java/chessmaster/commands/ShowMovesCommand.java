// @author ken-ruster
package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;

public class ShowMovesCommand extends Command{
    public static final String SHOW_MOVE_COMMAND_STRING = "moves";
    private String userInput;
    private ChessPiece piece;

    public ShowMovesCommand(String userInput){
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ChessBoard board) throws ChessMasterException {
        Coordinate coord = Coordinate.parseAlgebraicCoor(userInput);
        piece = board.getPieceAtCoor(coord);
        board.showAvailableMoves(piece);
        String[] displayString = piece.getAvailableCoordinatesString(board);
        return new CommandResult(displayString);
    }

    public ChessPiece getPiece() {
        return this.piece;
    }
}
