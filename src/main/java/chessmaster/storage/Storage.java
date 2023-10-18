package chessmaster.storage;

import chessmaster.exceptions.LoadBoardException;
import chessmaster.exceptions.ParseChessPieceException;
import chessmaster.exceptions.SaveBoardException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Bishop;
import chessmaster.pieces.King;
import chessmaster.pieces.Knight;
import chessmaster.pieces.Pawn;
import chessmaster.pieces.Queen;
import chessmaster.pieces.Rook;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Method to save board to file
     */
    public static void saveBoard(ChessBoard board) throws SaveBoardException {
        try (FileWriter fileWriter = new FileWriter("/tp/data/saved-game.txt")){
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    ChessPiece piece = board.getPieceAtCoor(new Coordinate(row, col));
                    String tile = (piece != null) ? piece.toString() : " ";
                    fileWriter.write(tile);
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            throw new SaveBoardException();
        }
    }

    public static ChessBoard loadBoard() throws LoadBoardException {
        File file = new File("/tp/data/saved-game.txt");
        ChessBoard chessBoard = new ChessBoard();
        ChessTile[][] boardTiles;

        if (!file.exists()) {
            try {
                File directory = file.getParentFile();
                if (!directory.exists() && !directory.mkdirs()) {
                    throw new LoadBoardException("Failed to create directory structure.");
                }

                if (!file.exists() && !file.createNewFile()) {
                    throw new LoadBoardException("Failed to create the file.");
                }
            } catch (IOException e) {
                throw new LoadBoardException();
            }
            return chessBoard;
        }
        try {
            Scanner fileScanner = new Scanner(file);
            boardTiles = new ChessTile[ChessBoard.SIZE][ChessBoard.SIZE];

            while (fileScanner.hasNext()) {
                for (int row = 0; row < ChessBoard.SIZE; row++) {
                    String tileRow = fileScanner.nextLine();
                    for (int col = 0; col < ChessBoard.SIZE; col++) {
                        String piece = tileRow.substring(col, col + 1);
                        if (piece.equals(" ")) {
                            boardTiles[row][col] = new ChessTile();
                        } else {
                            boardTiles[row][col] = new ChessTile(chessPieceIdentification(row + 1, col + 1, piece));
                        }
                    }
                }
            }
        } catch (FileNotFoundException | ParseChessPieceException e) {
            throw new LoadBoardException();
        }
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                chessBoard.setTile(row, col, boardTiles[row][col]);
            }
        }
        return chessBoard;
    }

    public static ChessPiece chessPieceIdentification(int row, int col, String piece) throws ParseChessPieceException {
        switch (piece) {
        case "p":
            return new Pawn(row, col, 1);
        case "r":
            return new Rook(row, col, 1);
        case "n":
            return new Knight(row, col, 1);
        case "b":
            return new Bishop(row, col, 1);
        case "q":
            return new Queen(row, col, 1);
        case "k":
            return new King(row, col, 1);

        case "P":
            return new Pawn(row, col, 0);
        case "R":
            return new Rook(row, col, 0);
        case "N":
            return new Knight(row, col, 0);
        case "B":
            return new Bishop(row, col, 0);
        case "Q":
            return new Queen(row, col, 0);
        case "K":
            return new King(row, col, 0);
        default:
            throw new ParseChessPieceException();
        }
    }
}

