package chessmaster.game;

import chessmaster.pieces.ChessPiece;

public class Move {
    private Coordinate from;
    private Coordinate to;
    private ChessPiece piece;

    public Move() {
    }

    public Move(Coordinate from, Coordinate to, ChessPiece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
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

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public void setTo(Coordinate to) {
        this.to = to;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return (from == null && to == null && piece == null);
    }

    public boolean isValid(Coordinate[][] possibleCoordinates) {
        for (Coordinate[] direction : possibleCoordinates) {
            for (Coordinate coor : direction) {
                if (coor.equals(to)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLeftCastling() {
        return piece.getIsLeftCastling();
    }

    public boolean isRightCastling() {
        return piece.getIsRightCastling();
    }
}
