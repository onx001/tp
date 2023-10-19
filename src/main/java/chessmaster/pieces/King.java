package chessmaster.pieces;

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
    public Coordinate[][] getAvailableCoordinates(ChessTile[][] board) {
        Coordinate[][] result = new Coordinate[DIRECTIONS.length][0];

        for (int dir = 0; dir < DIRECTIONS.length; dir++) {
            int offsetX = DIRECTIONS[dir][0];
            int offsetY = DIRECTIONS[dir][1];
            ChessPiece destPiece = null;

            if (position.isOffsetWithinBoard(offsetX, offsetY)){
                destPiece = board[position.getY() + offsetY][position.getX() + offsetX].getChessPiece();
            } 

            if (position.isOffsetWithinBoard(offsetX, offsetY) && hasMoved && dir<8 &&
                    (destPiece == null || destPiece.getColour() != this.color)) {
                result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
            } else if (position.isOffsetWithinBoard(offsetX, offsetY) && !hasMoved && dir>=8){
                if (dir == 8){
                    if (board[position.getY()][position.getX()-1].getChessPiece() == null &&
                            board[position.getY()][position.getX()-2].getChessPiece() == null &&
                            board[position.getY()][position.getX()-3].getChessPiece() == null &&
                            board[position.getY()][position.getX()-4].getChessPiece() != null &&
                            board[position.getY()][position.getX()-4].getChessPiece().hasMoved == false){
                        result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                        this.setIsLeftCastling(true);
                    }
                } else if (dir == 9){
                    if (board[position.getY()][position.getX()+1].getChessPiece() == null &&
                            board[position.getY()][position.getX()+2].getChessPiece() == null &&
                            board[position.getY()][position.getX()+3].getChessPiece() != null &&
                            board[position.getY()][position.getX()+3].getChessPiece().hasMoved == false){
                        result[dir] = new Coordinate[] { position.addOffsetToCoordinate(offsetX, offsetY) };
                        this.setIsRightCastling(true);
                    }
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
