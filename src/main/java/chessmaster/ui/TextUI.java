package chessmaster.ui;

import java.util.Scanner;

import chessmaster.game.ChessBoard;

public class TextUI {

    public static final String CHESS_BOARD_DIVIDER = "_".repeat(5 * ChessBoard.SIZE + 1);

    /**
     * Format of a comment input line. Comment lines are silently consumed when
     * reading user input.
     */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    Scanner scanner;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * 
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = scanner.nextLine().trim();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = scanner.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or
     * is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        boolean isCommentLine = rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
        return rawInputLine.trim().isEmpty() || isCommentLine;
    }

    public void printChessDivider() {

    }

    public void printChessBoardRow() {

    }

}
