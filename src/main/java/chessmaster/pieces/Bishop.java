package chessmaster.pieces;

public class Bishop extends ChessPiece {
    public static final String BISHOP_KEY = "b";

    public static final String BISHOP_WHITE = "♗";
    public static final String BISHOP_BLACK = "♝";

    public Bishop(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public String toString() {
        return color == 1 ? BISHOP_BLACK : BISHOP_WHITE;
    }
}
