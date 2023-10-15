package chessmaster.pieces;

public class Rook extends ChessPiece {
    public static final String ROOK_WHITE = "r"; // ♖
    public static final String ROOK_BLACK = "R"; // ♜

    public Rook(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? ROOK_BLACK : ROOK_WHITE;
    }
}
