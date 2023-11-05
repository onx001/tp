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

    //@@author TriciaBK
    private String filePathString;
    private File storageFile;
    private int blackPieceNum;
    private int whitePieceNum;
    private boolean blackKingPresent;
    private boolean whiteKingPresent;
    private Scanner fileScanner;

    public Storage(String filePath) {
        filePathString = filePath;
        storageFile = new File(filePath);
        assert !filePathString.isEmpty() && filePath != null : "File path cannot be empty or null";
    }

    //@@author TongZhengHong
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
            throw new ChessMasterException("Fatal: Error creating file: " + filePathString);
        }
    }

    //@@author TriciaBK
    /**
     * Saves the state of the ChessBoard to a file. Writes the player's color to the
     * first line
     * and subsequently chess pieces in a 8 x 8 format.
     *
     * @param board       The ChessBoard to save.
     * @throws ChessMasterException If there is an error saving the board to a file.
     */
    public void saveBoard(ChessBoard board, Color currentColor) throws ChessMasterException {
        createChessMasterFile();

        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            fileWriter.write(board.getPlayerColor().name());
            fileWriter.write(System.lineSeparator());

            fileWriter.write(String.valueOf(board.getDifficulty()));
            fileWriter.write(System.lineSeparator());

            fileWriter.write(currentColor.name());
            fileWriter.write(System.lineSeparator());

            for (int row = 0; row < ChessBoard.SIZE; row++) {
                for (int col = 0; col < ChessBoard.SIZE; col++) {
                    ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                    fileWriter.write(piece.toString());
                }
                fileWriter.write(System.lineSeparator());
            }

            for (int row = 0; row < ChessBoard.SIZE; row++) {
                for (int col = 0; col < ChessBoard.SIZE; col++) {
                    ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                    String hasMovedString = piece.getHasMoved() ? "1" : "0";
                    fileWriter.write(hasMovedString);
                }
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new SaveBoardException();
        }
    }

    //@@author TongZhengHong
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

    //@@author TriciaBK
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

        blackPieceNum = 0;
        whitePieceNum = 0;
        blackKingPresent = false;
        whiteKingPresent = false;

        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        // Skip first three lines
        for (int i = 0; i < 3; i++) {
            if (fileScanner.hasNext()) {
                fileScanner.nextLine();
            }
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
                //@@author onx001
                if (!this.isPieceValid(initialPiece)) {
                    fileScanner.close();
                    throw new LoadBoardException();
                }
                //@@author TriciaBK
                boardTiles[rowIndex][col] = new ChessTile(initialPiece);
            }
            rowIndex++;
        }

        boolean hasBothKings = blackKingPresent && whiteKingPresent;
        if (!hasBothKings) {
            fileScanner.close();
            throw new LoadBoardException();
        }

        rowIndex = 0;
        while (rowIndex < ChessBoard.SIZE && fileScanner.hasNext()) {
            String chessRowLine = fileScanner.nextLine();
            if (chessRowLine.length() != ChessBoard.SIZE) {
                fileScanner.close();
                throw new LoadBoardException();
            }

            for (int col = 0; col < ChessBoard.SIZE; col++) {
                boolean hasMoved = Character.getNumericValue(chessRowLine.charAt(col)) > 0;
                if (hasMoved) {
                    boardTiles[rowIndex][col].getChessPiece().setHasMoved();
                }
            }

            rowIndex++;
        }

        fileScanner.close();
        return boardTiles;
    }

    //@@author onx001
    private boolean isPieceValid (ChessPiece initialPiece) {
        if (initialPiece.isBlackKing()) {
            if (blackKingPresent) {
                return false;
            } else {
                blackKingPresent = true;
                blackPieceNum++;
            }
        } else if (initialPiece.isWhiteKing()) {
            if (whiteKingPresent) {
                return false;
            } else {
                whiteKingPresent = true;
                whitePieceNum++;
            }
        } else if (initialPiece.isBlack()) {
            if (blackPieceNum >= ChessBoard.MAX_PIECES) {
                return false;
            } else {
                blackPieceNum++;
            }
        } else if (initialPiece.isWhite()) {
            if (whitePieceNum >= ChessBoard.MAX_PIECES) {
                return false;
            } else {
                whitePieceNum++;
            }
        }

        return true;
    }

    //@@author TongZhengHong
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

    //@@author onx001
    /**
     * Loads the difficulty from a file.
     * Expects the difficulty information on the second line of text file.
     * @return The difficulty as an integer.
     */
    public int loadDifficulty() throws ChessMasterException {
        createChessMasterFile();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        // Skip player color first line
        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
        }

        if (fileScanner.hasNext()) {
            try {
                String difficultyLine = fileScanner.nextLine();
                int difficulty = Parser.parseDifficulty(difficultyLine);

                fileScanner.close();
                if (difficulty < 1 || difficulty > 3) {
                    throw new LoadBoardException();
                }
                return difficulty;
            } catch (NumberFormatException e) {
                throw new LoadBoardException();
            }
        }

        fileScanner.close();
        throw new LoadBoardException();
    }

    //@@author TriciaBK
    /**
     * Loads the current turn player's
     * @return The difficulty as an integer.
     */
    public Color loadCurrentColor() throws ChessMasterException {
        createChessMasterFile();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
        }

        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
        }

        if (fileScanner.hasNext()) {
            String currentColorString = fileScanner.nextLine();
            Color color = Parser.parsePlayerColor(currentColorString);
            fileScanner.close();
            return color;
        }

        fileScanner.close();
        throw new LoadBoardException();
    }

}
