package chessmaster.game.move;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;

public class MoveFactory {

    public static Move createMove(ChessBoard board, Coordinate from, Coordinate to) {
        ChessPiece pieceMoved = board.getPieceAtCoor(from);

        // Check if the move is a castling move
        if (CastleMove.checkIfLeftCastling(pieceMoved, from, to)) {
            return new CastleMove(from, to, pieceMoved, CastleSide.LEFT);
        } else if (CastleMove.checkIfRightCastling(pieceMoved, from, to)) {
            return new CastleMove(from, to, pieceMoved, CastleSide.RIGHT);
        }

        Move move;
        // Check if the move is a capturing move
        if (board.isTileOccupied(to)) {
            ChessPiece pieceCaptured = board.getPieceAtCoor(to);
            move = new Move(from, to, pieceMoved, pieceCaptured);
        } else {
            move = new Move(from, to, pieceMoved);
        }

        return move;
    }

    public static PromoteMove createPromoteMove(Coordinate coord, ChessPiece newPiece) {
        return new PromoteMove(coord, newPiece);
    }

}
