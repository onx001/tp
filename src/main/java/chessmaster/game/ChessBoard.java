package chessmaster.game;

import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.ui.TextUI;

public class ChessBoard {

    public static final int SIZE = 8;

    private ChessTile[][] board = new ChessTile[SIZE][SIZE];

    private static final String[][] EMPTY_CHESSBOARD_STRING = {
            { "r", "kn", "b", "q", "k", "b", "kn", "r" },
            { "p", "p", "p", "p", "p", "p", "p", "p" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "P", "P", "P", "P", "P", "P", "P", "P" },
            { "R", "Kn", "B", "Q", "K", "B", "Kn", "R" },
    };

    public ChessBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String key = EMPTY_CHESSBOARD_STRING[i][j].toLowerCase();
                int color = i > 4 ? ChessPiece.BLACK : ChessPiece.WHITE;

                ChessPiece initialPiece = Parser.parseChessPiece(key, i, j, color);
                board[i][j] = new ChessTile(initialPiece);
            }
        }
    }

    public void showChessBoard(TextUI ui) {
        System.out.println(TextUI.CHESS_BOARD_DIVIDER);
        for (ChessTile[] row : board) {
            for (ChessTile tile : row) {
                System.out.print(tile.toString());
            }
            System.out.print(ChessTile.TILE_DIVIDER);
            System.out.println(System.lineSeparator() + TextUI.CHESS_BOARD_DIVIDER);
        }
    }
}
