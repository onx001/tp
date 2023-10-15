package chessmaster.parser;

import chessmaster.pieces.*;

public class Parser {
    public static ChessPiece parseChessPiece(String pieceString, int row, int col, int color) {
        switch (pieceString) {
            case Bishop.BISHOP_KEY:
                return new Bishop(row, col, color);
            case King.KING_KEY:
                return new King(row, col, color);
            case Queen.QUEEN_KEY:
                return new Queen(row, col, color);
            case Knight.KNIGHT_KEY:
                return new Knight(row, col, color);
            case Pawn.PAWN_KEY:
                return new Pawn(row, col, color);
            case Rook.ROOK_KEY:
                return new Rook(row, col, color);
            default:
                return null;
        }
    }
}
