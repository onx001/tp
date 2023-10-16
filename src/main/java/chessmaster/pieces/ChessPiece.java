package chessmaster.pieces;

import chessmaster.game.Coordinate;
import chessmaster.game.ChessTile;

public abstract class ChessPiece {

    public static final int BLACK = 0;
    public static final int WHITE = 1;
    protected boolean hasMoved = false;
    
    protected static final int[] UP_UP_LEFT = {1, -2}; 
    protected static final int[] UP_UP_RIGHT = {-1, -2}; 
    protected static final int[] DOWN_DOWN_LEFT = {1, 2}; 
    protected static final int[] DOWN_DOWN_RIGHT = {-1, 2}; 
    protected static final int[] LEFT_UP_LEFT = {2, -1}; 
    protected static final int[] LEFT_DOWN_LEFT = {2, 1}; 
    protected static final int[] RIGHT_UP_RIGHT = {-2, -1}; 
    protected static final int[] RIGHT_DOWN_RIGHT = {-2, 1}; 
    protected static final int[] UP_UP = {0, -2}; 
    protected static final int[] DOWN_DOWN = {0, 2};

    protected static final int[] UP = {0, -1}; 
    protected static final int[] DOWN = {0, 1}; 
    protected static final int[] LEFT = {1, 0};
    protected static final int[] RIGHT = {-1, 0};

    protected static final int[] UP_LEFT = {1, -1}; 
    protected static final int[] UP_RIGHT = {-1, -1}; 
    protected static final int[] DOWN_LEFT = {1, 1}; 
    protected static final int[] DOWN_RIGHT = {-1, 1}; 

    protected Coordinate position;
    protected int color;
    protected Coordinate[][] availableCoordinates;

    public ChessPiece(int row, int col, int color) {
        this.position = new Coordinate(col, row);
        this.color = color;
    }

    /**
     * Returns available coordinates in multiple directions from the current position. 
     * The directions are dependent on the chess piece type. Each inner array stores the coordinates that is
     * in the direction the current chess piece can move to.
     *
     * @return A 2D array of Coordinate arrays representing available coordinates in different directions.
     */
    public abstract Coordinate[][] getAvailableCoordinates();

    /**
     * Returns the validity of the move to the destination coordinate.
     * @param destination
     * @param board
     * @return
     */
    public boolean isMoveValid(Coordinate destination, ChessTile[][] board){
        Coordinate[][] availableCoordinates = getAvailableCoordinates();
        for (Coordinate[] direction : availableCoordinates) {
            for (Coordinate possibleCoord : direction) {
                if (possibleCoord.equals(destination)) {
                    ChessPiece destPiece = board[destination.getY()][destination.getX()].getChessPiece();
                    if (destPiece == null){
                        return true;
                    } else if (destPiece.getColour() != this.color){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void displayAvailableCoordinates(ChessTile[][] board) {

        System.out.println("Available coordinates for " + this.getClass().getSimpleName() + " at " + position + ":\n");
        Coordinate[][] availableCoordinates = getAvailableCoordinates();


        for (Coordinate[] direction : availableCoordinates) {
            for (Coordinate possibleCoord : direction) {
                if (this.isMoveValid(possibleCoord, board)){
                    System.out.print(possibleCoord + " ");
                }
            }
        }
        System.out.println();
    }

    public int getColour() {
        return color == BLACK ? ChessPiece.BLACK : ChessPiece.WHITE;
    }

    @Override
    public String toString() {
        return "ChessPiece [color=" + color + ", position=" + position + "]";
    }
}
