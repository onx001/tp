package chessmaster.pieces;

public class Knight extends ChessPiece {
    public static final String KNIGHT_KEY = "kn";

    public static final String KNIGHT_WHITE = "♘";
    public static final String KNIGHT_BLACK = "♞";

    public Knight(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public String toString() {
        return color == 1 ? KNIGHT_BLACK : KNIGHT_WHITE;
    }
}
