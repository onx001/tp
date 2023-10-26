package chessmaster.game;

import java.util.Arrays;

import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.King;

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

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public void setTo(Coordinate to) {
        this.to = to;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    /**
     * Checks if the move is valid by checking if the to coordinate is in the
     * possibleCoordinates 2d array
     * @author onx001
     * @param possibleCoordinates
     * @return
     */
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
        if (!(piece instanceof King)) {
            return false;
        }

        int[] offset = to.calculateOffsetFrom(from);
        return Arrays.equals(offset, ChessPiece.CASTLE_LEFT);
    }

    public boolean isRightCastling() {
        if (!(piece instanceof King)) {
            return false;
        }

        int[] offset = to.calculateOffsetFrom(from);
        return Arrays.equals(offset, ChessPiece.CASTLE_RIGHT);
    }

    @Override
    public String toString() {
        return "Move [from=" + from + ", to=" + to + ", piece=" + piece + "]";
    }
}
