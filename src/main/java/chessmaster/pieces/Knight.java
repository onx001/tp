package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class Knight extends ChessPiece {
    public static final String KNIGHT_WHITE = "n"; // ♘
    public static final String KNIGHT_BLACK = "N"; // ♞

    public static final int[][] DIRECTIONS = {
        UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
        LEFT_UP_LEFT, LEFT_DOWN_LEFT, RIGHT_UP_RIGHT, RIGHT_DOWN_RIGHT,
    };
    
    protected static int points = 30;
    protected static int[][] boardWeight = 
        {{-5,-4,-3,-3,-3,-3,-4,-5},
        {-4,-2,0,0,0,0,-2,-4},
        {-3,0,1,2,2,1,0,-3},
        {-3,1,2,3,3,2,1,-3},
        {-3,0,2,3,3,2,0,-3},
        {-3,1,1,2,2,1,1,-3},
        {-4,-2,0,1,1,0,-2,-4},
        {-5,-4,-3,-3,-3,-3,-4,-5}};

    public Knight(int row, int col, Color color) {
        super(row, col, color);
        this.setPoints(points);
        this.setBoardWeight(boardWeight);
        assert color != Color.EMPTY : "Knight piece should have either black or white color";
    }

    @Override
    public String toString() {
        return color == Color.BLACK ? KNIGHT_BLACK : KNIGHT_WHITE;
    }

    @Override
    public Coordinate[] getPseudoLegalCoordinates(ChessBoard board) {
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

        return flattenArray(result);
    }

}
