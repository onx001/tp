package chessmaster.game;

import chessmaster.pieces.ChessPiece;

public class Move {
    private Coordinate from;
    private Coordinate to;
    private ChessPiece piece;

    public Move(Coordinate from, Coordinate to, ChessBoard board) {
        this.from = from;
        this.to = to;

        this.piece = board.getPieceAtCoor(from);
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }

    public ChessPiece getPiece() {
        return piece;
    }
}
