package chessmaster.pieces;

public class Pawn extends ChessPiece {
    public static final String PAWN_KEY = "p";

    public static final String PAWN_WHITE = "♙";
    public static final String PAWN_BLACK = "♟";

    public Pawn(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public String toString() {
        return color == 1 ? PAWN_BLACK : PAWN_WHITE;
    }
}
