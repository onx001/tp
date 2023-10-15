package chessmaster.pieces;

public class Queen extends ChessPiece {
    public static final String QUEEN_KEY = "q";

    public static final String QUEEN_WHITE = "q"; // ♕
    public static final String QUEEN_BLACK = "Q"; // ♛

    public Queen(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public String toString() {
        return color == 1 ? QUEEN_BLACK : QUEEN_WHITE;
    }
}
