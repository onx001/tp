package chessmaster.pieces;

public class Queen extends ChessPiece {
    public static final String QUEEN_WHITE = "q"; // ♕
    public static final String QUEEN_BLACK = "Q"; // ♛

    public Queen(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? QUEEN_BLACK : QUEEN_WHITE;
    }
}
