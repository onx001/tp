package chessmaster.game;

import chessmaster.pieces.ChessPiece;

public class MoveFactory {

    public static Move createMove(ChessBoard board, Coordinate from, Coordinate to) {
        ChessPiece pieceMoved = board.getPieceAtCoor(from);

        Move move;
        if (board.isTileOccupied(from)) {
            ChessPiece pieceCaptured = board.getPieceAtCoor(from);
            move = new Move(from, to, pieceMoved, pieceCaptured);
        } else {
            move = new Move(from, to, pieceMoved);
        }

        return move;
    }

}
