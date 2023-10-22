package chessmaster.game;

public enum Color {
    WHITE, BLACK, EMPTY;

    /**
     * Get the opposite color given the player's color.
     * Used to identify the color for CPU player.
     *
     * @return The opposite color.
     */
    public Color getOppositeColour() {
        if (this == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
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
}
