package chessmaster.game;

import chessmaster.exceptions.InvalidMoveException;
import chessmaster.parser.Parser;
import chessmaster.parser.MoveValidator;
import chessmaster.pieces.ChessPiece;
import chessmaster.ui.TextUI;


public class ChessBoard {

    public static final int SIZE = 8;

    private static final String[][] STARTING_CHESSBOARD_STRING = {
            {"r", "n", "b", "q", "k", "b", "n", "r"},
            {"p", "p", "p", "p", "p", "p", "p", "p"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"P", "P", "P", "P", "P", "P", "P", "P"},
            {"R", "N", "B", "Q", "K", "B", "N", "R"},
    };

    private ChessTile[][] board = new ChessTile[SIZE][SIZE];

    public ChessBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String chessPieceString = STARTING_CHESSBOARD_STRING[row][col];
                ChessPiece initialPiece = Parser.parseChessPiece(chessPieceString, row, col);
                board[row][col] = new ChessTile(initialPiece);
            }
        }
    }

    public void displayAvailableMoves(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length ; j++) {
                ChessPiece piece = board[i][j].getChessPiece();
                if (piece != null){
                    piece.displayAvailableCoordinates(this.board);
                }
            }

            
        }
    }

    public void showChessBoard(TextUI ui) {
        ui.printChessBoardHeader();
        ui.printChessBoardDivider();
        for (int i = 0; i < board.length; i++) {
            ChessTile[] row = board[i];
            StringBuilder rowString = new StringBuilder();

            for (ChessTile tile : row) {
                rowString.append(tile.toString());
            }

            int rowNum = (i - 8) * -1;
            ui.printChessBoardRow(rowNum, rowString.toString());
        }
    }


    public void movePiece(Move move) throws InvalidMoveException {
    }

    public ChessPiece getPieceAtCoor (Coordinate coor) {

        ChessTile tile = board[coor.getX()][coor.getY()];

        return tile.getChessPiece();
    }



    public void executeMove(Move move) {
        Coordinate from = move.getFrom();
        Coordinate to = move.getTo();
        ChessPiece piece = move.getPiece();

        if (piece != null) {
            if (MoveValidator.isValidMove(from, to)) {
                // Piece at new position
                board[to.getX()][to.getY()] = new ChessTile(piece);
                // Null piece in original position
                board[from.getX()][from.getY()] = new ChessTile();

                // Add the move to the move history
                //moveHistory.add(move);
            } else {
                // Edit to throw exception
                System.out.println("Move is invalid. Try again.");
            }
        } else {
            // Edit to throw exception
            System.out.println("No piece at original coordinates.");
        }
    }

}
