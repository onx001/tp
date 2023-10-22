package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;

public class Knight extends ChessPiece {
    public static final String KNIGHT_WHITE = "n"; // ♘
    public static final String KNIGHT_BLACK = "N"; // ♞

    public static final int[][] DIRECTIONS = {
        UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
        LEFT_UP_LEFT, LEFT_DOWN_LEFT, RIGHT_UP_RIGHT, RIGHT_DOWN_RIGHT,
    };

    public Knight(int row, int col, int color) {
        super(row, col, color);
    }


    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? KNIGHT_BLACK : KNIGHT_WHITE;
    }

    @Override
    public Coordinate[][] getAvailableCoordinates(ChessBoard board) {
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];

            if (!position.isOffsetWithinBoard(offsetX, offsetY)) {
                continue; // Possible coordinate out of board
            }

            Coordinate newCoor = position.addOffsetToCoordinate(offsetX, offsetY);
            ChessPiece destPiece = board.getPieceAtCoor(newCoor);

            if (destPiece.isEmptyPiece() || isOpponent(destPiece)) {
                result[dir] = new Coordinate[]{ newCoor };
            }
        }

        return result;
    }
    
}
