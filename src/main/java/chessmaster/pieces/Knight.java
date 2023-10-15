package chessmaster.pieces;

public class Knight extends ChessPiece {
    public static final String KNIGHT_WHITE = "n"; // ♘
    public static final String KNIGHT_BLACK = "N"; // ♞

    public Knight(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? KNIGHT_BLACK : KNIGHT_WHITE;
    }
}
