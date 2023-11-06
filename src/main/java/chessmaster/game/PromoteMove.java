package chessmaster.game;

import chessmaster.pieces.ChessPiece;

public class PromoteMove extends Move {
    public PromoteMove (Coordinate coord, ChessPiece piece) {
        super(coord, coord, piece);
    }

    /**
     * Converts the move to a format to be saved in the .txt file.
     * Only called after the piece has been promoted to
     *
     * @return
     */
    @Override
    public String toFileString() {
        return "p " + this.getFrom() + " " + this.getPiece().toString();
    }
}
