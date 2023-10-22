package chessmaster.storage;

import chessmaster.exceptions.ChessMasterException;
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

    private String filePathString;
    private File storageFile;

    public Storage(String filePath){
        filePathString = filePath;
        storageFile = new File(filePath);
        assert !filePathString.isEmpty() && filePath != null : "File path cannot be empty or null";
    }

    private void createChessMasterFile() throws ChessMasterException {
        // Create the necessary parent directories for new file
        if (!storageFile.exists()) {
            storageFile.getParentFile().mkdirs();
        }

        // Create file if it does not exist
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new ChessMasterException("Fatal: Error creating file: " + filePathString + " Exiting ChessMaster");
        }
    }

    /**
     * Method to save board to file
     *
     * @param board takes in current board that is in play
     */
    public void saveBoard(ChessBoard board, int playerColor) throws ChessMasterException {
        createChessMasterFile();

        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            fileWriter.write(playerColor);

            for (int row = 0; row < ChessBoard.SIZE; row++) {
                for (int col = 0; col < ChessBoard.SIZE; col++) {
                    ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                    fileWriter.write(piece.toString());
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new SaveBoardException();
        }
    }

    /**
     * Method to load board from file
     *
     */
    public ChessBoard loadBoard() throws ChessMasterException {
        createChessMasterFile();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        int playerColor = -1;
        if (fileScanner.hasNext()) {
            String colorLine = fileScanner.nextLine();
            playerColor = Parser.parsePlayerColor(colorLine);
        }

        int rowIndex = 0;
        ChessTile[][] boardTiles = new ChessTile[ChessBoard.SIZE][ChessBoard.SIZE];
        while (rowIndex < ChessBoard.SIZE && fileScanner.hasNext()) {
            String chessRowLine = fileScanner.nextLine();
            if (chessRowLine.length() != ChessBoard.SIZE) {
                fileScanner.close();
                throw new LoadBoardException();
            }

            for (int col = 0; col < ChessBoard.SIZE; col++) {
                String chessPieceString = String.valueOf(chessRowLine.charAt(col));
                ChessPiece initialPiece = Parser.parseChessPiece(chessPieceString, rowIndex, col);
                boardTiles[rowIndex][col] = new ChessTile(initialPiece);
            }
            rowIndex++;
        }

        fileScanner.close();
        return new ChessBoard(boardTiles);
    }
}

