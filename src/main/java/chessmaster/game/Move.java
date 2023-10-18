package chessmaster.game;

import chessmaster.pieces.ChessPiece;

public class Move {
    private Coordinate from;
    private Coordinate to;
    private ChessPiece piece;

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
}
