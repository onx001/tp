package chessmaster.game;

import chessmaster.pieces.ChessPiece;

public class ChessTile {
    public static final String TILE_DIVIDER = "|";
    private static final String EMPTY_TILE_STRING = "  ";

    private ChessPiece chessPiece;

    public ChessTile() {
        chessPiece = null;
    }

    public ChessTile(ChessPiece piece) {
        chessPiece = piece;
    }

    public boolean isEmpty() {
        return chessPiece == null;
    }

    @Override
    public String toString() {
        String tileContent = isEmpty() ? EMPTY_TILE_STRING : chessPiece.toString();
        return String.format("%s %-2s ", TILE_DIVIDER, tileContent);
    }
}
