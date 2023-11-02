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
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.user.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    //@@author TriciaBK
    private String filePathString;
    private File storageFile;

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
            throw new ChessMasterException("Fatal: Error creating file: " + filePathString + " Exiting ChessMaster");
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
    public void saveBoard(ChessBoard board, Player currentPlayer) throws ChessMasterException {
        createChessMasterFile();

        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            fileWriter.write(board.getPlayerColor().name());
            fileWriter.write(System.lineSeparator());

            fileWriter.write(String.valueOf(board.getDifficulty()));
            fileWriter.write(System.lineSeparator());

            if (currentPlayer.isHuman()) {
                fileWriter.write("Human");
            } else {
                fileWriter.write("CPU");
            }
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

        int blackPieces = 0;
        int whitePieces = 0;
        boolean blackKing = false;
        boolean whiteKing = false;

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(storageFile);
        } catch (FileNotFoundException e) {
            throw new LoadBoardException("Invalid file path: " + filePathString);
        }

        // Skip player colour on first line
        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
        }

        //Skip difficulty on second line
        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
        }

        //Skip current player's turn on third line
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
                //@@author onx001
                if (initialPiece.isBlackKing()) {
                    if (blackKing) {
                        fileScanner.close();
                        throw new LoadBoardException();
                    } else {
                        blackKing = true;
                        blackPieces++;
                    }
                } else if (initialPiece.isWhiteKing()) {
                    if (whiteKing) {
                        fileScanner.close();
                        throw new LoadBoardException();
                    } else {
                        whiteKing = true;
                        whitePieces++;
                    }
                } else if (initialPiece.isBlack()) {
                    if (blackPieces >= ChessBoard.MAX_PIECES) {
                        fileScanner.close();
                        throw new LoadBoardException();
                    } else {
                        blackPieces++;
                    }
                } else if (initialPiece.isWhite()) {
                    if (whitePieces >= ChessBoard.MAX_PIECES) {
                        fileScanner.close();
                        throw new LoadBoardException();
                    } else {
                        whitePieces++;
                    }
                }
                //@@author TriciaBK
                boardTiles[rowIndex][col] = new ChessTile(initialPiece);
            }
            rowIndex++;
        }

        if (!blackKing || !whiteKing) {
            fileScanner.close();
            throw new LoadBoardException();
        }

        fileScanner.close();
        return boardTiles;
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

        if (fileScanner.hasNext()) {
            fileScanner.nextLine();
        }

        if (fileScanner.hasNext()) {
            try {
                String difficultyLine = fileScanner.nextLine();
                int difficulty = Parser.parseDifficulty(difficultyLine);

                fileScanner.close();
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
    public Player loadCurrentPlayer() throws ChessMasterException {
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
            String currentPlayerString = fileScanner.nextLine();
            ChessBoard board = new ChessBoard(loadPlayerColor(), loadBoard());
            boolean isCPU = currentPlayerString.equals("CPU");
            return isCPU ? new CPU(loadPlayerColor().getOppositeColour(), board) : new Human(loadPlayerColor(), board);
        }

        fileScanner.close();
        throw new LoadBoardException();
    }

}
