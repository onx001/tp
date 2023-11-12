package chessmaster.game.move;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;

public class MoveFactory {

    public static Move createMove(ChessBoard board, Coordinate from, Coordinate to) {
        ChessPiece pieceMoved = board.getPieceAtCoor(from);

        // Check if the move is a castling move
        if (CastleMove.checkIfLeftCastling(pieceMoved, from, to)) {
            return new CastleMove(from, to, pieceMoved, CastleSide.LEFT);
        } else if (CastleMove.checkIfRightCastling(pieceMoved, from, to)) {
            return new CastleMove(from, to, pieceMoved, CastleSide.RIGHT);
        }

        // Check if move is an en passant move
        //@@author onx001
        if (pieceMoved instanceof Pawn && board.hasEnPassant()) {
            Coordinate enPassantCoor = board.getEnPassantCoor();
            if (to.equals(enPassantCoor)) {
                ChessPiece enPassantPiece = board.getEnPassantPiece();
                return new EnPassantMove(from, to, pieceMoved, enPassantPiece);
            }
        }

        // Check if the move is a capturing move
        Move move;
        if (board.isTileOccupied(to)) {
            ChessPiece pieceCaptured = board.getPieceAtCoor(to);
            move = new Move(from, to, pieceMoved, pieceCaptured);
        } else {
            move = new Move(from, to, pieceMoved);
        }
        return move;
    }

    public static PromoteMove createPromoteMove(Coordinate coord, Pawn pawnPromoted, ChessPiece newPiece) {
        return new PromoteMove(coord, pawnPromoted, newPiece);
    }

}
