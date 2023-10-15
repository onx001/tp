package chessmaster.parser;

import chessmaster.pieces.*;

public class Parser {
    public static ChessPiece parseChessPiece(String pieceString, int row, int col) {
        switch (pieceString) {
            case Bishop.BISHOP_BLACK:
                return new Bishop(row, col, ChessPiece.BLACK);
            case Bishop.BISHOP_WHITE:
                return new Bishop(row, col, ChessPiece.WHITE);
            case King.KING_BLACK:
                return new King(row, col, ChessPiece.BLACK);
            case King.KING_WHITE:
                return new King(row, col, ChessPiece.WHITE);
            case Queen.QUEEN_BLACK:
                return new Queen(row, col, ChessPiece.BLACK);
            case Queen.QUEEN_WHITE:
                return new Queen(row, col, ChessPiece.WHITE);
            case Knight.KNIGHT_BLACK:
                return new Knight(row, col, ChessPiece.BLACK);
            case Knight.KNIGHT_WHITE:
                return new Knight(row, col, ChessPiece.WHITE);
            case Pawn.PAWN_BLACK:
                return new Pawn(row, col, ChessPiece.BLACK);
            case Pawn.PAWN_WHITE:
                return new Pawn(row, col, ChessPiece.WHITE);
            case Rook.ROOK_BLACK:
                return new Rook(row, col, ChessPiece.BLACK);
            case Rook.ROOK_WHITE:
                return new Rook(row, col, ChessPiece.WHITE);
            default:
                return null;
        }
    }
}
