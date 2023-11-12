//@@author TongZhengHong
package chessmaster.game;

public enum Color {
    WHITE, BLACK, EMPTY, DRAW;

    /**
     * Get the opposite color given the player's color.
     * Used to identify the color for CPU player.
     *
     * @return The opposite color.
     */
    public Color getOppositeColour() {
        if (this == Color.WHITE) {
            return Color.BLACK;
        } else if (this == Color.BLACK) {
            return Color.WHITE;
        } else {
            return Color.EMPTY;
        }
    }

    /**
     * Checks if a given color is white. 
     *
     * @return true if the color is white; false otherwise.
     */
    public boolean isWhite() {
        return this == Color.WHITE;
    }

    /**
     * Checks if a given color is black. 
     *
     * @return true if the color is black; false otherwise.
     */
    public boolean isBlack() {
        return this == Color.BLACK;
    }

    /**
     * Checks if a given color is empty.
     *
     * @return true if the color is empty; false otherwise.
     */
    public boolean isEmpty() {
        return this == Color.EMPTY;
    }

    /**
     * Checks if a given color is draw.
     *
     * @return true if the color is draw; false otherwise.
     */
    public boolean isDraw() {
        return this == Color.DRAW;
    }
}
