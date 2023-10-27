package chessmaster.storage;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.exceptions.LoadBoardException;
import chessmaster.exceptions.SaveBoardException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    // @author TriciaBK
    private String filePathString;
    private File storageFile;

    public Storage(String filePath) {
        filePathString = filePath;
        storageFile = new File(filePath);
        assert !filePathString.isEmpty() && filePath != null : "File path cannot be empty or null";
    }
    // @author

    /**
     * Creates a ChessMaster program file to store game state, including necessary
     * parent directories.
     *
     * @throws ChessMasterException If there is an error creating the file or parent
     *                              directories.
     */
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

    // @author TricaBK
    /**
     * Saves the state of the ChessBoard to a file. Writes the player's color to the
     * first line
     * and subsequently chess pieces in a 8 x 8 format.
     *
     * @param board       The ChessBoard to save.
     * @throws ChessMasterException If there is an error saving the board to a file.
     */
    public void saveBoard(ChessBoard board) throws ChessMasterException {
        createChessMasterFile();

        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            fileWriter.write(board.getPlayerColor().name());
            fileWriter.write(System.lineSeparator());

            for (int row = 0; row < ChessBoard.SIZE; row++) {
                for (int col = 0; col < ChessBoard.SIZE; col++) {
                    ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                    fileWriter.write(piece.toString());
                }
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new SaveBoardException();
        }
    }
    // @author

    public void resetBoard() throws ChessMasterException {
        createChessMasterFile();
        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            fileWriter.write("");
            fileWriter.close();

        } catch (IOException e) {
            throw new SaveBoardException();
        }
    }

    // @author TriciaBK
    /**
     * Loads the state of the chessboard from a file.
     * Ignores the first line player color information as it can be retrieved with
     * loadPlayerColor() method
     *
     * @return A 2D array of ChessTile objects representing the loaded chessboard.
     * @throws ChessMasterException If there is an error loading the board from the
     *                              file.
     */
    public ChessTile[][] loadBoard() throws ChessMasterException {
        createChessMasterFile();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        // Skip player color on first line
        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
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
        return boardTiles;
    }
    // @author

    /**
     * Loads the player's color from a file.
     * Expects the player color information on the first line of text file.
     *
     * @return The player's color as a Color enumeration.
     * @throws ChessMasterException If there is an error loading the player's color
     *                              from the file.
     */
    public Color loadPlayerColor() throws ChessMasterException {
        createChessMasterFile();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        if (fileScanner.hasNext()) {
            String colorLine = fileScanner.nextLine();
            Color playerColor = Parser.parsePlayerColor(colorLine);

            fileScanner.close();
            return playerColor;
        }

        fileScanner.close();
        throw new LoadBoardException();
    }
}
