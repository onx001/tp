//@@author TongZhengHong
package chessmaster.game;

import chessmaster.exceptions.ParseCoordinateException;

public class Coordinate {

    private static final String BOARD_COLUMNS = "abcdefgh";

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        assert !isCoorOutofBoard(x, y) : "Coordinates (x,y) should NOT be out of chessboard (8x8 size)!";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isCoorOutofBoard(int x, int y) {
        return (x < 0 || x >= ChessBoard.SIZE) || (y < 0 || y >= ChessBoard.SIZE);
    }

    /**
     * Checks if applying a given offset from the current position stays within the
     * bounds of the chessboard.
     *
     * @param offsetX The horizontal offset to apply.
     * @param offsetY The vertical offset to apply.
     * @return true if the resulting position is within the board boundaries;
     *         otherwise, false.
     */
    public boolean isOffsetWithinBoard(int offsetX, int offsetY) {
        int newX = x + offsetX;
        int newY = y + offsetY;

        return (newX >= 0 && newX < ChessBoard.SIZE) &&
                (newY >= 0 && newY < ChessBoard.SIZE);
    }

    /**
     * Adds the given offsets to the current coordinate and returns the new
     * coordinate.
     *
     * @param offsetX The horizontal offset to apply.
     * @param offsetY The vertical offset to apply.
     * @return A new coordinate after applying the offsets, or the current
     *         coordinate
     *         if the new position is out of the board boundaries.
     */
    public Coordinate addOffsetToCoordinate(int offsetX, int offsetY) {
        int newX = x + offsetX;
        int newY = y + offsetY;

        if (isCoorOutofBoard(newX, newY)) {
            return new Coordinate(x, y);
        }

        return new Coordinate(newX, newY);
    }

    /**
     * Parses an algebraic chess coordinate notation (e.g., "a1") and returns a
     * Coordinate object.
     *
     * @param notation The algebraic coordinate notation to parse.
     * @return A Coordinate object representing the parsed chess coordinate.
     * @throws ParseCoordinateException If the input notation is invalid or out of
     *                                  bounds.
     */
    public static Coordinate parseAlgebraicCoor(String notation) throws ParseCoordinateException {
        notation = notation.toLowerCase();
        if (notation.length() != 2) {
            throw new ParseCoordinateException();
        }

        String colString = Character.toString(notation.charAt(0));
        boolean isColValid = BOARD_COLUMNS.contains(colString);

        try {
            String rowString = String.valueOf(notation.charAt(1));
            int rowInt = Integer.parseInt(String.valueOf(rowString));

            if (rowInt < 1 || rowInt > ChessBoard.SIZE || !isColValid) {
                throw new ParseCoordinateException();
            }

            int indexX = BOARD_COLUMNS.indexOf(colString); 
            int indexY = (rowInt - 8) * -1;

            return new Coordinate(indexX, indexY);

        } catch (NumberFormatException e) {
            throw new ParseCoordinateException();
        }
    }

    public int[] calculateOffsetFrom(Coordinate otherCoordinate) {
        int offsetX = this.x - otherCoordinate.getX();
        int offsetY = this.y - otherCoordinate.getY();
        return new int[]{ offsetX, offsetY };
    }

    @Override
    public String toString() {
        return String.format("%s%d", BOARD_COLUMNS.charAt(x), (ChessBoard.SIZE - y));
    }

    //@@author onx001
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate other = (Coordinate) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }

}
