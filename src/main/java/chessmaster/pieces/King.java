package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;

public class King extends ChessPiece {
    public static final String KING_WHITE = "k"; // ♔
    public static final String KING_BLACK = "K"; // ♚

    public static final int[][] DIRECTIONS = {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, CASTLE_LEFT, CASTLE_RIGHT
    };

    public King(int row, int col, int color) {
        super(row, col, color);
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

            if (DIRECTIONS[dir] == CASTLE_LEFT) {
                Coordinate pos1 = position.addOffsetToCoordinate(-1, 0);
                Coordinate pos2 = position.addOffsetToCoordinate(-2, 0);
                Coordinate pos3 = position.addOffsetToCoordinate(-3, 0);
                Coordinate rookPos = position.addOffsetToCoordinate(-4, 0);

                boolean hasRookMoved = board.getPieceAtCoor(rookPos).hasMoved;
                boolean isSidesEmpty = board.getPieceAtCoor(pos1).isEmptyPiece() 
                    && board.getPieceAtCoor(pos2).isEmptyPiece() 
                    && board.getPieceAtCoor(pos3).isEmptyPiece();

                if (isSidesEmpty && !hasRookMoved && !hasMoved) {
                    result[dir] = new Coordinate[]{ newCoor };
                }

            } else if (DIRECTIONS[dir] == CASTLE_RIGHT) {
                Coordinate pos1 = position.addOffsetToCoordinate(+1, 0);
                Coordinate pos2 = position.addOffsetToCoordinate(+2, 0);
                Coordinate rookPos = position.addOffsetToCoordinate(+3, 0);

                boolean hasRookMoved = board.getPieceAtCoor(rookPos).hasMoved;
                boolean isSidesEmpty = board.getPieceAtCoor(pos1).isEmptyPiece() 
                    && board.getPieceAtCoor(pos2).isEmptyPiece();

                if (isSidesEmpty && !hasRookMoved  && !hasMoved) {
                    result[dir] = new Coordinate[]{ newCoor };
                }
                
            } else { // Normal or capture move
                if (destPiece.isEmptyPiece() || isOpponent(destPiece)) {
                    result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? KING_BLACK : KING_WHITE;
    }

}
