package chessmaster.pieces;

public class Pawn extends ChessPiece {
    public static final String PAWN_WHITE = "p"; // ♙
    public static final String PAWN_BLACK = "P"; // ♟

    public Pawn(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? PAWN_BLACK : PAWN_WHITE;
    }
}
