package chessmaster.game;

import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.EmptyPiece;
import chessmaster.pieces.Pawn;

public class ChessTile {
    public static final String TILE_DIVIDER = "|";
    private static final String EMPTY_TILE_STRING = " ";
    private static final String AVAILABLE_TILE_STRING = "x";
    private static final String BACKGROUND_RESET = "\u001B[0m";
    private static final String CAPTURABLE_BACKGROUND = "\u001B[43m";

    /** Nullable ChessPiece object. Null signifies that this tile is empty */
    private ChessPiece chessPiece;

    public ChessTile(Coordinate coor) {
        chessPiece = new EmptyPiece(coor.getX(),coor.getY());
    }

    public ChessTile(ChessPiece piece) {
        chessPiece = piece;
    }

    public boolean isEmpty() {
        return chessPiece.isEmptyPiece();
    }

    public void setTileEmpty(Coordinate coor) {
        chessPiece = new EmptyPiece(coor.getX(),coor.getY());
    }


    /**
     * Updates the ChessTile with a new ChessPiece, considering piece interactions. <BR>
     * 1. Replace the new piece on an EMPTY tile. <BR>
     * 2. Cannot capture a friendly piece; no change is made UNLESS it is for promotion. <BR>
     * 3. If new piece captures the opponent piece, mark the opponent piece as captured and replace it. <BR>
     *
     * @param newPiece The new ChessPiece to place on the tile.
     */
    public void updateTileChessPiece(ChessPiece newPiece) {
        if (chessPiece.isEmptyPiece()) {
            // Move newPiece to empty tile
            chessPiece = newPiece;
            return;
        } 

        if (newPiece.isFriendly(chessPiece)) {
            // Only update if friendly pawn piece is promoting
            if (chessPiece instanceof Pawn && newPiece.isPromotionPiece()) {
                chessPiece = newPiece;
            }
            return; // Cannot capture friendly piece
        } 
        
        if (newPiece.isOpponent(chessPiece)) {
            // Mark opponent piece as captured
            chessPiece.setIsCaptured();
            chessPiece = newPiece;
        }
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    


    @Override
    public String toString() {
        String tileContent = isEmpty() ? EMPTY_TILE_STRING : chessPiece.toString();
        return String.format("%s %s ", TILE_DIVIDER, tileContent);
    }

    public String toStringAvailable() {
        String tileContent = isEmpty() ? AVAILABLE_TILE_STRING :
                (CAPTURABLE_BACKGROUND + chessPiece.toString() + BACKGROUND_RESET);
        return String.format("%s %s ", TILE_DIVIDER, tileContent);
    }
}
