package chessmaster.pieces;

public class Bishop extends ChessPiece {
    public static final String BISHOP_WHITE = "b"; // ♗
    public static final String BISHOP_BLACK = "B"; // ♝

    public Bishop(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? BISHOP_BLACK : BISHOP_WHITE;
    }
}
