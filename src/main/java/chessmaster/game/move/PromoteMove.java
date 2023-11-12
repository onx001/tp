package chessmaster.game.move;

import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;

/**
 * Note: a promotion is technically done in two Move objects:
 * Move 1. Move the pawn from 7th to 8th row or 2nd to 1st row
 * Move 2. Promote the pawn
 * This class represents Move #2.
 */
public class PromoteMove extends Move {
    public static final String PROMOTE_MOVE_STRING = "p %s %s";

    protected ChessPiece newPiece;
    protected Pawn pawnPromoted;

    public PromoteMove(Coordinate coord, Pawn pawnPromoted, ChessPiece newPiece) {
        super(coord, coord, newPiece);
        this.pawnPromoted = pawnPromoted;
        this.newPiece = newPiece;
    }

    /**
     * Converts the move to a format to be saved in the .txt file.
     * Only called after the piece has been promoted to.
     *
     * @return String representing the move in the format of the .txt file
     */
    @Override
    public String toFileString() {
        String out = String.format(PROMOTE_MOVE_STRING, this.getTo(), this.getPieceMoved().toString());
        return out;
    }

    public ChessPiece getNewPiece() {
        return newPiece;
    }

    public Pawn getPawnPromoted() {
        return pawnPromoted;
    }
}
