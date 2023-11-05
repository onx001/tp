package chessmaster.ui;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import chessmaster.commands.CommandResult;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Coordinate;
import chessmaster.game.Move;
import chessmaster.pieces.ChessPiece;
import chessmaster.user.Player;

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
    public String getUserInput() {
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
    private boolean shouldIgnore(String rawInputLine) {
        boolean isCommentLine = rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
        return rawInputLine.trim().isEmpty() || isCommentLine;
    }

    /**
     * Prints one or more lines of text, surrounded by a divider, to the user
     * console.
     *
     * @param texts The lines of text to be printed.
     */
    public void printText(String... texts) {
        out.println(DIVIDER);

        for (String text : texts) {
            out.println(text);
        }

        out.println(DIVIDER);
    }

    //@@author TongZhengHong
    public void printChessBoard(ChessTile[][] tiles) {
        printChessBoardHeader();
        printChessBoardDivider();

        for (int i = 0; i < tiles.length; i++) {
            ChessTile[] row = tiles[i];
            StringBuilder rowString = new StringBuilder();

            for (ChessTile tile : row) {
                rowString.append(ChessTile.TILE_DIVIDER);
                rowString.append(tile.toString());
            }

            int rowNum = 8 - i;
            printChessBoardRow(rowNum, rowString.toString());
        }
        printChessBoardHeader();
        out.println("");
    }

    public void printChessBoardWithMove(ChessTile[][] tiles, Move move) {
        printChessBoardHeader();
        printChessBoardDivider();

        for (int i = 0; i < tiles.length; i++) {
            ChessTile[] row = tiles[i];
            StringBuilder rowString = new StringBuilder();

            for (int j = 0; j < tiles.length; j++) {
                rowString.append(ChessTile.TILE_DIVIDER);

                ChessTile tile = row[j];
                Coordinate coord = new Coordinate(j, i);
                boolean isPrevMove = move.getFrom().equals(coord) || 
                    move.getTo().equals(coord);

                String pieceString = isPrevMove ? tile.toStringPrevMove() : tile.toString();
                rowString.append(pieceString);
            }
            
            int rowNum = 8 - i;
            printChessBoardRow(rowNum, rowString.toString());
        }
        printChessBoardHeader();
        out.println("");
    }

    //@@author ken-ruster
    /**
     * Prints the chessboard along with highlighted moves for a specific chess piece.
     *
     * This method displays the chessboard, emphasizing available destination squares for a
     * selected piece and marking the selected piece itself.
     *
     * @param tiles The 2D array of ChessTile objects representing the chessboard.
     * @param piece The chess piece for which moves are highlighted.
     * @param coordinates An array of coordinates representing available destination squares.
     */
    public void printChessBoardAvailableMoves(ChessTile[][] tiles, ChessPiece piece, 
        Coordinate[] coordinates) {

        printChessBoardHeader();
        printChessBoardDivider();

        for (int i = 0; i < tiles.length; i++) {
            ChessTile[] row = tiles[i];
            StringBuilder rowString = new StringBuilder();

            for (int j = 0; j < tiles.length; j++) {
                rowString.append(ChessTile.TILE_DIVIDER);

                ChessTile tile = row[j];
                Coordinate coord = new Coordinate(j, i);

                String pieceString;
                if (Arrays.asList(coordinates).contains(coord)) {
                    pieceString = tile.toStringAvailableDest();
                } else if (piece.getPosition().equals(coord)) {
                    pieceString = tile.toStringSelected();
                } else {
                    pieceString = tile.toString();
                }
                rowString.append(pieceString);
            }

            int rowNum = 8 - i;
            printChessBoardRow(rowNum, rowString.toString());
        }
        printChessBoardHeader();
        out.println("");
    }
    //@@author

    public void printWelcomeMessage() {
        printText(UiMessages.WELCOME_MESSAGE);
    }

    public void printLoadBoardError() {
        printText(UiMessages.LOAD_BOARD_ERROR_MESSAGE);
    }

    public void promptContinuePrevGame(boolean error) {
        if (error) {
            out.print(UiMessages.CONTINUE_PREV_GAME_ERROR_MESSAGE);
        } else {
            out.print(UiMessages.EXIST_PREV_GAME_MESSAGE);
        }
    }

    public void promptDifficulty(boolean error) {
        if (error) {
            out.print(UiMessages.CHOOSE_DIFFICULTY_ERROR_MESSAGE);
        } else {
            out.print(UiMessages.CHOOSE_DIFFICULTY_MESSAGE);
        }
    }

    public void promptStartingColor(boolean error) {
        if (error) {
            out.print(UiMessages.CHOOSE_PLAYER_COLOR_ERROR_MESSAGE);
        } else {
            out.print(UiMessages.CHOOSE_PLAYER_COLOR_MESSAGE);
        }
    }

    public void printStartNewGame(String colorString) {
        String displayText = String.format(UiMessages.START_NEW_GAME_MESSAGE, colorString);
        printText(displayText);
    }

    public void printContinuePrevGame(String colorString) {
        String displayText = String.format(UiMessages.CONTINUE_PREV_GAME_MESSAGE, colorString);
        printText(displayText);
    }

    public void printPromotePrompt(Coordinate coord) {
        String message = String.format(UiMessages.PROMPT_PROMOTE_MESSAGE, coord.toString());
        out.print(message);
    }

    public void printPromoteInvalidMessage() {
        out.print(UiMessages.PROMPT_PROMOTE_INVALID_MESSAGE);
    }

    public void printCPUMove(Move cpuMove) {
        String pieceString = cpuMove.getPiece().getClass().getSimpleName();
        String displayString = String.format(UiMessages.CPU_MOVE_MESSAGE, pieceString,
                cpuMove.getFrom(), cpuMove.getTo());
        printText(displayString);
    }

    public void printChessBoardDivider() {
        out.println(CHESS_BOARD_DIVIDER);
    }

    public void printChessBoardHeader() {
        out.print(CHESS_BOARD_PADDING + CHESS_BOARD_TAB);
        for (int i = 0; i < COLUMN_HEADER.length(); i++) {
            char col = COLUMN_HEADER.charAt(i);
            out.printf(" (%s)", col);
        }
        out.println("");
    }

    public void printChessBoardRow(int rowNum, String chessBoardRow) {
        out.print(CHESS_BOARD_PADDING);
        out.print(String.format("(%d) ", rowNum));
        out.print(chessBoardRow);
        out.print(ChessTile.TILE_DIVIDER);
        out.print(String.format(" (%d)", rowNum));
        out.print(System.lineSeparator() + CHESS_BOARD_DIVIDER);
        out.println("");
    }

    public void printCommandResult(CommandResult result) {
        String[] resultStrings = result.getMessageStrings();
        if (resultStrings != null && resultStrings.length > 0) {
            printText(resultStrings);
        }
    }

    public void printErrorMessage(Exception e) {
        printText(e.getMessage());
    }

    public void printEndMessage(Player winner) {
        String winningColorString = winner.getColour().name();
        if (winner.isHuman()) {
            printText(String.format(UiMessages.HUMAN_WIN_STRING, winningColorString));
        } else if (winner.isCPU()) { // Human lost
            String playerColorString = winner.getColour().getOppositeColour().name();
            printText(String.format(UiMessages.CPU_WIN_STRING, playerColorString));
        }
    }

}
