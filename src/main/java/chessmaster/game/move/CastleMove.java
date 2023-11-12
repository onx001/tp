package chessmaster.game.move;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.King;

import java.util.Arrays;

public class CastleMove extends Move {

    protected CastleSide side;
    protected Move rookMove;

    public CastleMove(Coordinate from, Coordinate to, ChessPiece pieceMoved, CastleSide side) {
        super(from, to, pieceMoved);
        this.side = side;

    }

    public static boolean checkIfLeftCastling(ChessPiece pieceMoved, Coordinate from, Coordinate to) {
        if (!(pieceMoved instanceof King)) {
            return false;
        }

        int[] offset = to.calculateOffsetFrom(from);
        return Arrays.equals(offset, ChessPiece.CASTLE_LEFT);
    }

    public static boolean checkIfRightCastling(ChessPiece pieceMoved, Coordinate from, Coordinate to) {
        if (!(pieceMoved instanceof King)) {
            return false;
        }

        int[] offset = to.calculateOffsetFrom(from);
        return Arrays.equals(offset, ChessPiece.CASTLE_RIGHT);
    }

    @Override
    protected boolean isTryingToCastleUnderCheck(ChessBoard board) {
        if (board.isChecked(this.getPieceMoved().getColor())) {
            return true;
        }
        return false;
    }

    public CastleSide getSide() {
        return side;
    }

    public Move getRookMove() {
        return rookMove;
    }

    public void setRookMove(Move rookMove) {
        this.rookMove = rookMove;
    }
}
