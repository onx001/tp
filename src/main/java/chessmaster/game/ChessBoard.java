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
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String chessPieceString = EMPTY_CHESSBOARD_STRING[i][j];
                ChessPiece initialPiece = Parser.parseChessPiece(chessPieceString, i, j);
                board[i][j] = new ChessTile(initialPiece);
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
