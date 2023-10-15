package chessmaster.pieces;

public class Rook extends ChessPiece {
    public static final String ROOK_KEY = "r";

    public static final String ROOK_WHITE = "r"; // ♖
    public static final String ROOK_BLACK = "R"; // ♜

    public Rook(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public String toString() {
        return color == 1 ? ROOK_BLACK : ROOK_WHITE;
    }
}
