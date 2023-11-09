// @author TongZhengHong
package chessmaster.game;

import java.util.Arrays;

import chessmaster.exceptions.ChessMasterException;
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

        assert from != null && to != null : "Coordinates in Move should not be null!";
        assert piece != null && !piece.isEmptyPiece() : "Chess piece in Move should not be null or empty!";
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }

    public ChessPiece getPieceMoved() {
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

    //@@author onx001
    /**
     * Checks if the move is valid by checking if the to coordinate is in the
     * possibleCoordinates 2d array
     * @param board
     * @return
     */
    public boolean isValid(ChessBoard board) {
        Coordinate[][] coordinates = piece.getPseudoCoordinates(board);
        for (Coordinate[] direction : coordinates) {
            for (Coordinate coor : direction) {
                if (coor.equals(to)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidWithCheck(ChessBoard board) {
        if (!isValid(board)) {
            return false;
        }

        if (isLeftCastling() || isRightCastling()) {
            if (board.isChecked(this.getPieceMoved().getColor())) {
                return false;
            }
        }

        ChessBoard boardCopy = board.clone();
        ChessPiece pieceCopy = boardCopy.getPieceAtCoor(from);
        Move moveCopy = new Move(from, to, pieceCopy);
        try {
            boardCopy.executeMove(moveCopy);
        } catch (ChessMasterException e) {
            return false;
        }

        return !boardCopy.isChecked(this.getPieceMoved().getColor());
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

    public String toFileString() {
        return from + " " + to;
    }

    // @author TongZhengHong
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Move) {
            final Move other = (Move) obj;
            return from.equals(other.getFrom()) && to.equals(other.getTo()) && piece.equals(other.getPieceMoved());
        }
        return false;
    }
}
