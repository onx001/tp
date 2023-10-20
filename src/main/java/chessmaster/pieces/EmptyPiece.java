package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;

public class EmptyPiece extends ChessPiece{

    public static final String EMPTY_PIECE = " ";

    public EmptyPiece(int row, int col, int color){
        super(row, col, color);
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessBoard board) {
        return new Coordinate[0][];
    }

    @Override
    public String getType(){
        return EMPTY_PIECE;
    }
}
