package chessmaster.game;

import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.EmptyPiece;

public class ChessTile {
    public static final String TILE_DIVIDER = "|";
    private static final String EMPTY_TILE_STRING = " ";

    /** Nullable ChessPiece object. Null signifies that this tile is empty */
    private ChessPiece chessPiece;

    public ChessTile(Coordinate coor) {
        chessPiece = new EmptyPiece(coor.getX(),coor.getY(), ChessPiece.BLACK);
    }

    public ChessTile(ChessPiece piece) {
        chessPiece = piece;
    }

    public boolean isEmpty() {
        return chessPiece.getType().equals(EmptyPiece.EMPTY_PIECE);
    }

    public void setTileEmpty(Coordinate coor) {
        chessPiece = new EmptyPiece(coor.getX(),coor.getY(), ChessPiece.BLACK);
    }

    public void updateTileChessPiece(ChessPiece piece) {
        chessPiece = piece;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    @Override
    public String toString() {
        String tileContent = isEmpty() ? EMPTY_TILE_STRING : chessPiece.toString();
        return String.format("%s %s ", TILE_DIVIDER, tileContent);
    }
}
