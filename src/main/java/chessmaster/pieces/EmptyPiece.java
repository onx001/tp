package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class EmptyPiece extends ChessPiece{

    public static final String EMPTY_PIECE = ".";

    public EmptyPiece(int row, int col){
        super(row, col, Color.EMPTY);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessBoard board) {
        return new Coordinate[0][];
    }
    
}
