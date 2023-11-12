package chessmaster.game.move;

import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;

public class EnPassantMove extends Move {

    public EnPassantMove(Coordinate from, Coordinate to, ChessPiece pieceMoved, ChessPiece enPassantPiece) {
        // enPassantPiece is the pieceCaptured
        super(from, to, pieceMoved, enPassantPiece);
    }

}
