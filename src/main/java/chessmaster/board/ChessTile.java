package chessmaster.board;

import chessmaster.pieces.ChessPiece;

public class ChessTile {
    private boolean isEmpty;
    private ChessPiece chessPiece;

    public ChessTile() {
        emptyChessTile();
    }

    public void emptyChessTile() {
        isEmpty = true;
        chessPiece = null;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }
}
