// @author TongZhengHong
package chessmaster.game.move;

import java.util.Arrays;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;

public class Move {
    protected Coordinate from;
    protected Coordinate to;
    protected ChessPiece pieceMoved; // if castling then pieceMoved instanceof King
    protected ChessPiece pieceCaptured;
    protected boolean hasCapturedAPiece;

    public Move(Coordinate from, Coordinate to, ChessPiece pieceMoved) {
        this.from = from;
        this.to = to;
        this.pieceMoved = pieceMoved;
        this.pieceCaptured = null;
        this.hasCapturedAPiece = false;

        assert from != null && to != null : "Coordinates in Move should not be null!";
        assert pieceMoved != null && !pieceMoved.isEmptyPiece() : "Chess piece in Move should not be null or empty!";
    }

    public Move(Coordinate from, Coordinate to, ChessPiece pieceMoved, ChessPiece pieceCaptured) {
        this(from, to, pieceMoved);
        this.pieceCaptured = pieceCaptured;
        this.hasCapturedAPiece = true;
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }

    public ChessPiece getPieceMoved() {
        return pieceMoved;
    }

    public boolean hasCapturedAPiece() {
        return this.hasCapturedAPiece;
    }

    public ChessPiece getPieceCaptured() {
        return this.pieceCaptured;
    }

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public void setTo(Coordinate to) {
        this.to = to;
    }

    public void setPieceMoved(ChessPiece pieceMoved) {
        this.pieceMoved = pieceMoved;
    }


    //@@author onx001
    /**
     * Checks if the move is valid by checking if the to coordinate is in the
     * possibleCoordinates 2d array
     * @param board
     * @return
     */
    public boolean isValid(ChessBoard board) {
        Coordinate[] coordinates = pieceMoved.getPseudoLegalCoordinates(board);
        for (Coordinate coor : coordinates) {
            if (coor.equals(to)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isTryingToCastleUnderCheck(ChessBoard board) {
        return false; // Only CastleMove objects can castle!
    }

    public boolean isValidWithCheck(ChessBoard board) {
        // Check if the current move is valid on the board
        if (!this.isValid(board)) {
            return false;
        }

        // Cannot castle while in check
        if (isTryingToCastleUnderCheck(board)) {
            return false;
        }

        // Attempt to make the move on a cloned board
        ChessBoard boardCopy = board.clone();
        ChessPiece pieceCopy = boardCopy.getPieceAtCoor(from);
        Move moveCopy = new Move(from, to, pieceCopy);
        try {
            boardCopy.executeMove(moveCopy);
        } catch (ChessMasterException e) {
            return false;
        }
        boolean stillInCheckAfterMove = boardCopy.isChecked(this.getPieceMoved().getColor());

        return !stillInCheckAfterMove;
    }

    public boolean isSkippingPawn() {
        if (!(pieceMoved instanceof Pawn)) {
            return false;
        }

        int[] offset = to.calculateOffsetFrom(from);
        return Arrays.equals(offset, ChessPiece.UP_UP) || Arrays.equals(offset, ChessPiece.DOWN_DOWN);
    }

    @Override
    public String toString() {
        return "Move [from=" + from + ", to=" + to + ", piece=" + pieceMoved + "]";
    }

    public String toFileString() {
        return from + " " + to;
    }

    // @author TongZhengHong
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Move) {
            final Move other = (Move) obj;
            boolean sameFrom = from.equals(other.getFrom());
            boolean sameTo = to.equals(other.getTo());
            boolean samePiece = pieceMoved.getPieceName().equals(other.getPieceMoved().getPieceName());
            return sameFrom && sameTo && samePiece;
        }
        return false;
    }
}
