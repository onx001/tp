package chessmaster.game;

import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.ui.TextUI;

public class ChessBoard {

    public static final int SIZE = 8;

    private ChessTile[][] board = new ChessTile[SIZE][SIZE];

    private static final String[][] EMPTY_CHESSBOARD_STRING = {
            { "r", "n", "b", "q", "k", "b", "n", "r" },
            { "p", "p", "p", "p", "p", "p", "p", "p" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "P", "P", "P", "P", "P", "P", "P", "P" },
            { "R", "N", "B", "Q", "K", "B", "N", "R" },
    };

    public ChessBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String chessPieceString = EMPTY_CHESSBOARD_STRING[row][col];
                ChessPiece initialPiece = Parser.parseChessPiece(chessPieceString, row, col);
                board[row][col] = new ChessTile(initialPiece);
            }
        }
    }

    public void showChessBoard(TextUI ui) {
        ui.printChessBoardDivider();
        for (ChessTile[] row : board) {
            StringBuilder rowString = new StringBuilder();
            for (ChessTile tile : row) {
                rowString.append(tile.toString());
            }
            ui.printChessBoardRow(rowString.toString());
        }
    }
}
