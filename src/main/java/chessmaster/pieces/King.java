package chessmaster.pieces;

import chessmaster.exceptions.NullPieceException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
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
            ChessPiece destPiece = null;


            try{
                if (position.isOffsetWithinBoard(offsetX, offsetY)){
                    destPiece = board.getPieceAtCoor(position.addOffsetToCoordinate(offsetX, offsetY));
                }

                if (position.isOffsetWithinBoard(offsetX, offsetY) && hasMoved && dir<8 &&
                        (destPiece.getType().equalsIgnoreCase(EmptyPiece.EMPTY_PIECE)
                                || destPiece.getColour() != this.color)) {
                    result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                } else if (position.isOffsetWithinBoard(offsetX, offsetY) && !hasMoved && dir>=8){

                    if (dir == 8) {
                        Coordinate pos1 = position.addOffsetToCoordinate(-1, 0);
                        Coordinate pos2 = position.addOffsetToCoordinate(-2, 0);
                        Coordinate pos3 = position.addOffsetToCoordinate(-3, 0);
                        Coordinate pos4 = position.addOffsetToCoordinate(-4, 0);

                        if (board.getPieceAtCoor(pos1).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                board.getPieceAtCoor(pos2).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                board.getPieceAtCoor(pos3).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                !board.getPieceAtCoor(pos4).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                !board.getPieceAtCoor(pos4).hasMoved) {
                            result[dir] = new Coordinate[]{position.addOffsetToCoordinate(offsetX, offsetY)};
                          this.setIsLeftCastling(true);
                        }
                    } else if (dir == 9) {
                        Coordinate pos1 = position.addOffsetToCoordinate(+1, 0);
                        Coordinate pos2 = position.addOffsetToCoordinate(+2, 0);
                        Coordinate pos3 = position.addOffsetToCoordinate(+3, 0);

                        if (board.getPieceAtCoor(pos1).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                board.getPieceAtCoor(pos2).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                !board.getPieceAtCoor(pos3).getType().equals(EmptyPiece.EMPTY_PIECE) &&
                                !board.getPieceAtCoor(pos3).hasMoved) {
                            result[dir] = new Coordinate[]{position.addOffsetToCoordinate(offsetX, offsetY)};
                          this.setIsRightCastling(true);
                        }

                    }
                }
            } catch (NullPieceException e){
                e.printStackTrace();
            }

            
        }

        return result;
    }

    @Override
    public String toString() {
        return color == ChessPiece.BLACK ? KING_BLACK : KING_WHITE;
    }

    @Override
   public String getType() {
        return KING_WHITE;
   }

}
