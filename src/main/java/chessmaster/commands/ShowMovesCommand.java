//@@author ken-ruster
package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.exceptions.NullPieceException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.ui.TextUI;

public class ShowMovesCommand extends Command {
    public static final String SHOW_MOVES_COMMAND_STRING = "moves";

    public static final String NO_COORDINATE_FOUND_STRING = 
        "Oops! Looks like you forgot to specify a coordinate!";
    public static final String SHOW_MOVES_FORMAT_STRING = "Format: moves [column][row]";
    public static final String SHOW_MOVES_EXAMPLE_STRING = "Example: moves a2";

    private String userInput;
    private ChessPiece piece;

    public ShowMovesCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) throws ChessMasterException {
        if (userInput.isBlank()) {
            return new CommandResult(NO_COORDINATE_FOUND_STRING, 
                SHOW_MOVES_FORMAT_STRING, SHOW_MOVES_EXAMPLE_STRING);
        }

        Coordinate coord = Coordinate.parseAlgebraicCoor(userInput);
        piece = board.getPieceAtCoor(coord);
        if (piece.isEmptyPiece()) {
            throw new NullPieceException();
        }

        Coordinate[] possibleCoordinates = piece.getLegalCoordinates(board);
        ui.printChessBoardAvailableMoves(board.getBoard(), piece, possibleCoordinates);
    
        String[] displayString = piece.getAvailableCoordinatesString(board);
        return new CommandResult(displayString);
    }

    public ChessPiece getPiece() {
        return this.piece;
    }
}
