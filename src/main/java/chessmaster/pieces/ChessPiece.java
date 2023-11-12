package chessmaster.pieces;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.move.Move;

import java.util.ArrayList;

public abstract class ChessPiece {

    public static final int[] CASTLE_LEFT = {-2, 0};
    public static final int[] CASTLE_RIGHT = {2, 0};
    public static final int[] UP_UP = {0, -2}; 
    public static final int[] DOWN_DOWN = {0, 2};
    
    protected static final int[] UP_UP_LEFT = {1, -2}; 
    protected static final int[] UP_UP_RIGHT = {-1, -2}; 
    protected static final int[] DOWN_DOWN_LEFT = {1, 2}; 
    protected static final int[] DOWN_DOWN_RIGHT = {-1, 2}; 
    protected static final int[] LEFT_UP_LEFT = {2, -1}; 
    protected static final int[] LEFT_DOWN_LEFT = {2, 1}; 
    protected static final int[] RIGHT_UP_RIGHT = {-2, -1}; 
    protected static final int[] RIGHT_DOWN_RIGHT = {-2, 1}; 

    protected static final int[] UP = {0, -1}; 
    protected static final int[] DOWN = {0, 1}; 
    protected static final int[] LEFT = {1, 0};
    protected static final int[] RIGHT = {-1, 0};

    protected static final int[] UP_LEFT = {1, -1}; 
    protected static final int[] UP_RIGHT = {-1, -1}; 
    protected static final int[] DOWN_LEFT = {1, 1}; 
    protected static final int[] DOWN_RIGHT = {-1, 1};

    protected static final String NO_AVAILABLE_MOVES_STRING = 
        "There aren't any moves available for %s at %s!";
    protected static final String AVAILABLE_MOVES_STRING = 
        "Available coordinates for %s at %s: ";

    protected Color color;
    protected Coordinate position;
    protected boolean hasMoved = false;
    protected boolean isCaptured = false;
    protected boolean isEnPassant = false;
    protected int points = 0;
    

    //initialise empty boardweights of 0 for parent class to be used for the AI
    private int[][] boardWeight = 
        {{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0}};

    public ChessPiece(int row, int col, Color color) {
        this.position = new Coordinate(col, row);
        this.color = color;
    }

    //@@author ken-ruster
    public boolean isSameAs(ChessPiece other) {
        return (this.toString().equals(other.toString()));
    }
    /**
     * Gets an array of pseudo-legal coordinates for potential moves.
     *
     * This abstract method is meant to be implemented by subclasses to provide an array of pseudo-legal 
     * coordinates representing potential moves for a specific chess piece. The coordinates are based on 
     * the piece's movement rules and the current state of the chessboard.
     * 
     * @param board The current state of the chessboard.
     * @return An array of Coordinate objects, each representing a pseudo-legal move or destination for the chess piece.
     */
    public abstract Coordinate[] getPseudoLegalCoordinates(ChessBoard board);

    /**
     * Flatten a 2D array of coordinates into a 1D array.
     *
     * This method takes a 2D array of coordinates, representing various directions, 
     * and flattens it into a 1D array for easy access and processing.
     *
     * @param coordInDirections A 2D array of coordinates to be flattened.
     * @return A 1D array of coordinates, combining all coordinates from the input directions.
     */
    public Coordinate[] flattenArray(Coordinate[][] coordInDirections) {
        ArrayList<Coordinate> flattenedCoordinates = new ArrayList<>();
        for (Coordinate[] direction : coordInDirections) {
            for (Coordinate coordinate : direction) {
                flattenedCoordinates.add(coordinate);
            }
        }
        return flattenedCoordinates.toArray(new Coordinate[0]);
    }

    //@@author onx001
    /**
     * Gets an array of legal coordinates representing potential moves that adhere to game rules.
     *
     * This method calculates and provides an array of legal coordinates, which represent potential 
     * moves for the chess piece, based on its movement rules and the current state of the chessboard. 
     * These coordinates are filtered to ensure they adhere to the game rules, including preventing 
     * moves that result in the player's own king being in check.
     * 
     * @param board The current state of the chessboard.
     * @return An array of Coordinate objects, each representing a legal move or destination for the chess piece.
     */
    public Coordinate[] getLegalCoordinates(ChessBoard board) {
        Coordinate[] pseudoLegalCoordinates = getPseudoLegalCoordinates(board);
        Move[] allLegalMoves = board.getLegalMoves(this.color);

        ArrayList<Coordinate> result = new ArrayList<>();
        for (Move legalMove : allLegalMoves) {
            for (Coordinate destCoor : pseudoLegalCoordinates) {
                Move pseudoLegalMove = new Move(this.position, destCoor, this);
                if (pseudoLegalMove.equals(legalMove)) {
                    result.add(destCoor);
                }
            }
        }

        return result.toArray(new Coordinate[0]);
    }
    //@@author

    public boolean isWhiteKing() {
        return this instanceof King && this.isWhite();
    }

    public boolean isBlackKing() {
        return this instanceof King && this.isBlack();
    }

    public void clearEnPassant() {
        this.isEnPassant = false;
    }

    public boolean isEnPassant() {
        return this.isEnPassant;
    }

    public void setEnPassant() {
        this.isEnPassant = true;
    }

    //@@author ken-ruster
    /**
     * Convert all the possible moves of the piece into a String.
     * Returns a message saying that there are no moves available if there are no legal moves the piece can make.
     *
     * @param board Board the piece is located on
     * @return A String containing the coordinates the piece is able to move to
     */
    public String[] getAvailableCoordinatesString(ChessBoard board) {
        StringBuilder out = new StringBuilder();
        Coordinate[] legalCoordinates = getLegalCoordinates(board);

        if (legalCoordinates.length == 0) {
            return new String[] {
                String.format(NO_AVAILABLE_MOVES_STRING, getPieceName(), this.position)
            };
        }

        for (Coordinate possibleCoord : legalCoordinates) {
            out.append(possibleCoord + " ");
        }

        return new String[] {
            String.format(AVAILABLE_MOVES_STRING, getPieceName(), this.position),
            out.toString()
        };
    }
    //@@author onx001

    public Coordinate getPosition() {
        return this.position;
    }

    public void updatePosition(Coordinate newCoordinate) {
        this.position = newCoordinate;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    @Override
    public String toString() {
        return "ChessPiece [color=" + color + ", position=" + position + "]";
    }

    public Color getColor() {
        return color;
    }

    public boolean getIsCaptured() {
        return this.isCaptured;
    }

    public void setIsCaptured() {
        this.isCaptured = true;
    }

    /**
     * Returns the points of the ChessPiece object. 
     * The points are calculated based on the ChessPiece's position
     * @param isUpright Whether the chess board is aligned to the player it is processed for.
     * @return The points of the ChessPiece object.
     */
    public int getPoints(boolean isUpright) {
        int boardPoints;
        if (isUpright) {
            //finds board weight points of a friendly piece
            boardPoints = boardWeight[position.getX()][position.getY()];
        } else {
            //finds board weight points of an opponent piece
            boardPoints = boardWeight[7 - position.getX()][position.getY()];
        }
        //adds the board weight points to the piece's points
        int points = this.points + boardPoints;
        return points;
    }
    //@@author

    /**
     * Checks if the ChessPiece object has the same color as a given color.
     *
     * @param color The color to compare with the ChessPiece's color.
     * @return true if the ChessPiece has the same color as the provided color; false otherwise.
     */
    public boolean isSameColorAs(Color color) {
        if (isEmptyPiece()) {
            return false;
        }
        return this.color == color;
    }

    /**
     * Checks if the ChessPiece object is WHTIE.
     *
     * @return true if the ChessPiece is white; false otherwise.
     */
    public boolean isWhite() {
        if (isEmptyPiece()) {
            return false;
        }
        return this.color == Color.WHITE;
    }

    /**
     * Checks if the ChessPiece object is BLACK.
     *
     * @return true if the ChessPiece is white; false otherwise.
     */
    public boolean isBlack() {
        if (isEmptyPiece()) {
            return false;
        }
        return this.color == Color.BLACK;
    }
    
    /**
     * Checks if the provided ChessPiece object is friendly (has the same color) as the current ChessPiece.
     *
     * @param chessPiece The ChessPiece to compare with.
     * @return true if the provided ChessPiece is friendly; false otherwise.
     */
    public boolean isFriendly(ChessPiece chessPiece) {
        if (isEmptyPiece()) {
            return false;
        }
        return chessPiece.color == this.color;
    }

    /**
     * Checks if the provided ChessPiece is an opponent (has a different color) compared to the current ChessPiece.
     *
     * @param chessPiece The ChessPiece to compare with.
     * @return true if the provided ChessPiece is an opponent; false otherwise.
     */
    public boolean isOpponent(ChessPiece chessPiece) {
        if (isEmptyPiece()) {
            return false;
        }
        return chessPiece.color != this.color;
    }

    public boolean isEmptyPiece() {
        return this instanceof EmptyPiece;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isPromotionPiece() {
        return this instanceof Queen || this instanceof Rook 
            || this instanceof Bishop || this instanceof Knight;
    }

    public String getPieceName() {
        return this.getClass().getSimpleName();
    }

    protected void setPoints(int points) {
        this.points = points;
    }

    protected void setBoardWeight(int[][] boardWeight) {
        this.boardWeight = boardWeight;
    }
}
