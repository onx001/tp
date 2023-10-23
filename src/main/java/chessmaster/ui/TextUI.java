package chessmaster.ui;

import java.io.PrintStream;
import java.util.Scanner;

import chessmaster.commands.CommandResult;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.Move;

public final class TextUI {

    private static final String CHESS_BOARD_TAB = " ".repeat(4);
    private static final String CHESS_BOARD_PADDING = CHESS_BOARD_TAB.repeat(3);
    public static final String CHESS_BOARD_DIVIDER = CHESS_BOARD_PADDING + CHESS_BOARD_TAB +
            "_".repeat(4 * ChessBoard.SIZE + 1);

    private static final String COLUMN_HEADER = "abcdefgh";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "_".repeat(65) + LS;
    /**
     * Format of a comment input line. Comment lines are silently consumed when
     * reading user input.
     */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream out = System.out;

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * 
     * @return user input string in LOWER case
     */
    public static String getUserInput() {
        String fullInputLine = scanner.nextLine().trim();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = scanner.nextLine();
        }

        return fullInputLine.toLowerCase();
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or
     * is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private static boolean shouldIgnore(String rawInputLine) {
        boolean isCommentLine = rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
        return rawInputLine.trim().isEmpty() || isCommentLine;
    }

    /**
     * Prints one or more lines of text, surrounded by a divider, to the user
     * console.
     *
     * @param texts The lines of text to be printed.
     */
    public static void printText(String... texts) {
        out.println(DIVIDER);

        for (String text : texts) {
            out.println(text);
        }

        out.println(DIVIDER);
    }

    public static void printWelcomeMessage() {
        printText(UiMessages.WELCOME_MESSAGE);
    }

    public static void printLoadBoardError() {
        printText(UiMessages.LOAD_BOARD_ERROR_MESSAGE);
    }

    public static void promptContinuePrevGame(boolean error) {
        if (error) {
            out.print(UiMessages.CONTINUE_PREV_GAME_ERROR_MESSAGE);
        } else {
            out.print(UiMessages.EXIST_PREV_GAME_MESSAGE);
        }
    }

    public static void promptStartingColor(boolean error) {
        if (error) {
            out.print(UiMessages.CHOOSE_PLAYER_COLOR_ERROR_MESSAGE);
        } else {
            out.print(UiMessages.CHOOSE_PLAYER_COLOR_MESSAGE);
        }
    }

    public static void printStartNewGame(String colorString) {
        String displayText = String.format(UiMessages.START_NEW_GAME_MESSAGE, colorString);
        printText(displayText);
    }

    public static void printContinuePrevGame(String colorString) {
        String displayText = String.format(UiMessages.CONTINUE_PREV_GAME_MESSAGE, colorString);
        printText(displayText);
    }

    public static void printPromotePrompt(Coordinate coord) {
        String message = String.format(UiMessages.PROMPT_PROMOTE_MESSAGE, coord.toString());
        out.println(message);
    }

    public static void printPromoteInvalidMessage() {
        out.println(UiMessages.PROMPT_PROMOTE_INVALID_MESSAGE);
    }

    public static void printCPUMove(Move cpuMove) {
        String pieceString = cpuMove.getPiece().getClass().getSimpleName();
        String displayString = String.format(UiMessages.CPU_MOVE_MESSAGE, pieceString,
                cpuMove.getFrom(), cpuMove.getTo());
        printText(displayString);
    }

    public static void printChessBoardDivider() {
        out.println(CHESS_BOARD_DIVIDER);
    }

    public static void printChessBoardHeader() {
        out.print(CHESS_BOARD_PADDING + CHESS_BOARD_TAB);
        for (int i = 0; i < COLUMN_HEADER.length(); i++) {
            char col = COLUMN_HEADER.charAt(i);
            out.printf(" (%s)", col);
        }
        out.println("");
    }

    public static void printChessBoardRow(int rowNum, String chessBoardRow) {
        out.print(CHESS_BOARD_PADDING);
        out.print(String.format("(%d) ", rowNum));
        out.print(chessBoardRow);
        out.print(ChessTile.TILE_DIVIDER);
        out.print(String.format(" (%d)", rowNum));
        out.print(System.lineSeparator() + CHESS_BOARD_DIVIDER);
        out.println("");
    }

    public static void printCommandResult(CommandResult result) {
        printText(result.getMessageStrings());
    }

    public static void printErrorMessage(Exception e) {
        printText(e.getMessage());
    }

    public static void printWinnerMessage(Color colour) {
        if (colour.isBlack()) {
            out.println("BLACK Wins!");
        } else if (colour.isWhite()) {
            out.println("WHITE Wins!");
        }
    }

}
