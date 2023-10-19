package chessmaster.storage;

import chessmaster.exceptions.LoadBoardException;
import chessmaster.exceptions.SaveBoardException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Coordinate;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;

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
                    try {
                        ChessPiece piece = board.getPieceAtCoor(new Coordinate(row, col));
                        fileWriter.write(piece.toString());
                    } catch (Exception e) {
                        fileWriter.write(" ");
                    }
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
                        String pieceString = tileRow.substring(col, col + 1);
                        if (pieceString.equals(" ")) {
                            boardTiles[row][col] = new ChessTile(new Coordinate(col, row));
                        } else {
                            ChessPiece piece = Parser.parseChessPiece(pieceString, row + 1, col + 1);
                            boardTiles[row][col] = new ChessTile(piece);
                        }
                    }
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new LoadBoardException();
        }
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                chessBoard.setTile(row, col, boardTiles[row][col]);
            }
        }
        return chessBoard;
    }
}

