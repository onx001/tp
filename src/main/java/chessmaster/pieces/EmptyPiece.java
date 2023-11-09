package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class EmptyPiece extends ChessPiece {

    public static final String EMPTY_PIECE = ".";

    public EmptyPiece(int row, int col) {
        super(row, col, Color.EMPTY);
    }

    @Override
    public Coordinate[] getPseudoLegalCoordinates(ChessBoard board) {
        return new Coordinate[0];
    }

    // An empty piece will never be moved
    @Override
    public boolean getHasMoved() {
        return false;
    }

    //returns the string representation of the empty piece for cloning
    @Override
    public String toString() {
        return EMPTY_PIECE;
    }
}
