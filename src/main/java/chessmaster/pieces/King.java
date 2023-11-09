package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;

public class King extends ChessPiece {
    public static final String KING_WHITE = "k"; // ♔
    public static final String KING_BLACK = "K"; // ♚
    public static final int POINTS = 1000;
    public static final int[][] BOARDWEIGHT = 
        {{-3,-4,-4,-5,-5,-4,-4,-3},
        {-3,-4,-4,-5,-5,-4,-4,-3},
        {-3,-4,-4,-5,-5,-4,-4,-3},
        {-3,-4,-4,-5,-5,-4,-4,-3},
        {-2,-3,-3,-4,-4,-3,-3,-2},
        {-1,-2,-2,-2,-2,-2,-2,-1},
        {2,2,0,0,0,0,2,2},
        {2,3,1,0,0,1,3,2}};
        

    public static final int[][] DIRECTIONS = {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, CASTLE_LEFT, CASTLE_RIGHT
    };

    protected static int points = 1000;

    public King(int row, int col, Color color) {
        super(row, col, color);
        this.setPoints(points);
        this.setBoardWeight(BOARDWEIGHT);
        assert color != Color.EMPTY : "King piece should have either black or white color";
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

        return flattenArray(result);
    }

    @Override
    public String toString() {
        return color == Color.BLACK ? KING_BLACK : KING_WHITE;
    }

}
